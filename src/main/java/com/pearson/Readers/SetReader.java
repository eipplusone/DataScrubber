package com.pearson.Readers;

import com.pearson.Database.DatabaseSettings;
import com.pearson.Database.MySQL.MySQLTable;
import com.pearson.Database.SQL.Database;
import com.pearson.Interface.Interfaces.XMLInterface;
import com.pearson.Interface.RuleNode;
import com.pearson.Interface.Windows.ProgressWindow;
import com.pearson.Utilities.Constants;
import com.pearson.Utilities.StackTrace;
import noNamespace.MaskingSetDocument;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.RulesDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.sql.SQLException;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ruslan Kiselev
 *         Date: 7/15/13
 *         Time: 2:59 PM
 *         Project Name: DataScrubber
 *         <p/>
 *         Set Reader is responsible for running the set of rules specified by the user.
 */
public class SetReader extends SwingWorker<Integer, String> {

    private static final int TIMER_PERIOD = 5000;
    public static Logger logger = LoggerFactory.getLogger(SetReader.class.getName());

    ProgressWindow progressWindow;

    private static Set<String> tablesOccupied;
    MaskingSetDocument setDocument = null;
    Database database;
    Timer timer;
    private int numberOfThreadsAllowed = 50;
    String setName;
    ExecutorService executor;
    private ArrayList<Rule> rulesRunning;
    private Set<String> autoIncrementColumns;

    public SetReader(final MaskingSetDocument setDocument, Database database, final String name) {
        this.setDocument = setDocument;
        this.database = database;
        this.setName = name;
        rulesRunning = new ArrayList<Rule>();

        File configFile = new File(Constants.CONFIG_FILE);
        XMLInterface.checkConfigFile(configFile);
        autoIncrementColumns = new HashSet<String>();

        progressWindow = new ProgressWindow(this);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                progressWindow.setLabelName(name);
                progressWindow.setVisible(true);
            }
        });
    }

    /**
     * Returns a Tree(RuleNode) composed of rules inside the rules set. The first level of rules are children of root
     * the root itself is masking set object(i.e. not a rule)
     */
    public static RuleNode getRulesTree(MaskingSetDocument setDocument) {

        MaskingSetDocument.MaskingSet maskingSet = setDocument.getMaskingSet();
        RulesDocument.Rules rules = maskingSet.getRules();

        Rule rootRule = RulesDocument.Factory.newInstance().addNewRules().addNewRule();

        // a dummy root rule; it is not displayed inside main window
        rootRule.setId("Root");
        rootRule.setTarget("Root");
        rootRule.setRuleType(RuleType.SHUFFLE);
        rootRule.addNewShuffle();

        // root is not displayed; it only creates number of columns TODO make it more generic
        RuleNode root = new RuleNode(rootRule);

        for (Rule rule : rules.getRuleArray()) {
            appendNode(rule, root);
        }
        return root;
    }

    @Override
    protected void process(List<String> chunks) {
        progressWindow.updateLogText(chunks.toArray(new String[chunks.size()]));
    }

    /**
     * A recursive method-helpers that initialises tree with the information from masking set
     *
     * @param rule Rule from the first level of masking set
     * @param root Pointer to the root of the tree
     */
    private static void appendNode(Rule rule, RuleNode root) {
        RuleNode ruleNode = new RuleNode(rule);
        root.add(ruleNode);

        if (rule.getDependencies() == null) { // if root is leaf
            return;
        }
        for (Rule childRule : rule.getDependencies().getRuleArray()) {
            appendNode(childRule, ruleNode);
        }
    }

    private LinkedList<Rule> getChildren(RuleNode root) {
        LinkedList<Rule> rulesToReturn = new LinkedList();
        // Breadth-first search
        Enumeration it = root.children();

        while (it.hasMoreElements()) {
            // add all first level rules
            RuleNode firstLevelRule = (RuleNode) it.nextElement();
            rulesToReturn.add(firstLevelRule.getRule());
        }

        return rulesToReturn;
    }

    /**
     * Should be running on a separate thread using SwingWorker API. Enables interactivity for the application
     *
     * @return 0 if successful
     * @throws Exception
     */
    @Override
    public Integer doInBackground() throws Exception {
        RuleNode root = getRulesTree(this.setDocument);
        publish("Partitioned the set");
        createAutoIncrementColumns();

        // get the list of all rules that yet to be run in the order they suppose to run
        LinkedList<Rule> rulesToRun = getChildren(root);
        executor = Executors.newFixedThreadPool(numberOfThreadsAllowed);

        DatabaseSettings databaseSettings = database.getDatabaseSettings();
        try {
            if (databaseSettings.isTriggersExist()) {
                databaseSettings.disableTriggers();
                executeThreads(rulesToRun);
                databaseSettings.enableTriggers();
            }
            // if database has no triggers
            else {
                executeThreads(rulesToRun);
            }
        } catch (MySQLException sqlexc) {
            publish("Rule " + sqlexc.getRuleID() + " has exited with an SQL exception: " + sqlexc);
            logger.error(sqlexc + System.lineSeparator() + StackTrace.getStringFromStackTrace(sqlexc));
        } catch (Exception exc) {
            publish("Something terrible happened; check the logs");
            logger.error(exc + System.lineSeparator() + StackTrace.getStringFromStackTrace(exc));
        }

        return 0;
    }

    private void cleanUp() {
        if (rulesRunning.isEmpty()) try {
            cleanUpAutoIncrementColumns();
            database.cleanUp();
        } catch (SQLException e) {
            logger.debug("Weren't able to clean up after database usage");
            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
        }
    }

    /**
     * Deletes all the autoincrement columns we have created so far
     */
    private void cleanUpAutoIncrementColumns() throws SQLException {
        for (String target : autoIncrementColumns) {
            MySQLTable mySQLTable = null;
            try {
                mySQLTable = database.getTable(target);
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
            } catch (SQLException e) {
                logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            }
            logger.debug(target + ": Deleting autoincrement column");
            try {
                mySQLTable.deleteAutoIncrementColumn();
            } catch (SQLException e) {
                logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            }

            mySQLTable.cleanResourses();
        }
    }

    /**
     * Creates autoincrement columns for tables within masking set that don't have one.
     */
    private void createAutoIncrementColumns() {
        Set<String> targets = XMLInterface.getAllTargets(setDocument);

        for (String target : targets) {
            MySQLTable mySQLTable = null;
            try {
                mySQLTable = database.getTable(target);
            } catch (SQLException e) {
                logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            }

            if (mySQLTable.getAutoIncrementColumn() == null) {
                try {
                    mySQLTable.addAutoIncrementColumn();
                    logger.debug("Added autoincrement column to " + target);
                    autoIncrementColumns.add(target);
                } catch (SQLException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                }
                logger.debug("Creating autoincrement column for " + target);
            }

        }
    }

    private void executeThreads(List<Rule> list) {

        tablesOccupied = Collections.synchronizedSet(new HashSet<String>());
        for (Rule rule : list) {

            Future<Rule> future;
            if (!rule.getDisabled()) {
                publish("Rule " + rule.getId() + " starts running");
                logger.info("Rule " + rule.getId() + " starts running");
                rulesRunning.add(rule);
                executor.submit(new RuleReader(rule, database, this));
            }
        }

        com.pearson.Utilities.LoggerUtils.printRuleArray(rulesRunning);
    }

    public boolean addTarget(String target) {
        synchronized (tablesOccupied) {
            return tablesOccupied.add(target);
        }
    }

    public synchronized void updateDoneRule(Rule doneRule, boolean isSuccessful) {
        synchronized (rulesRunning) {
            tablesOccupied.remove(doneRule.getTarget());
            rulesRunning.remove(doneRule);
            com.pearson.Utilities.LoggerUtils.printRuleArray(rulesRunning);
            if (isSuccessful) {
                publish("Rule " + doneRule.getId() + " has completed running");
                if (!XMLInterface.isLeaf(doneRule)) {
                    List<Rule> ruleList = Arrays.asList(doneRule.getDependencies().getRuleArray());
                    executeThreads(ruleList);
                }
            }

            if (rulesRunning.isEmpty()) {
                logger.debug("Finished running all rules");
                cleanUp();
            }
        }

    }

    public synchronized void addToLog(String s) {
        publish(s);
    }

}
