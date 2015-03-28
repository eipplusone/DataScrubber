package com.pearson.Readers;

import com.pearson.Database.DatabaseSettings;
import com.pearson.Database.MySQL.MySQLTableWorker;
import com.pearson.Database.SQL.Column;
import com.pearson.Database.SQL.Database;
import com.pearson.Database.SQL.MySQLTable;
import com.pearson.Interface.DatabaseConnectionInfo;
import com.pearson.Interface.Interfaces.XMLInterface;
import com.pearson.Interface.RuleNode;
import com.pearson.Interface.Windows.ProgressWindow;
import com.pearson.Utilities.Constants;
import com.pearson.Utilities.StackTrace;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import noNamespace.MaskingSetDocument;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.RulesDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.modelmbean.XMLParseException;
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
    private final Database database;

    ProgressWindow progressWindow;

    private static Set<String> tablesOccupied; // main locking mechanism
    private static Set<MySQLTable> tablesUsed; // for after run cleaning purposes
    MaskingSetDocument setDocument = null;
    DatabaseConnectionInfo connectionInfo;
    private int numberOfThreadsAllowed = 50;
    String setName;
    ExecutorService executor;
    private ArrayList<Rule> rulesRunning;

    public SetReader(final MaskingSetDocument setDocument, DatabaseConnectionInfo connectionInfo, final String name) throws SQLException {
        this.setDocument = setDocument;
        this.setName = name;
        this.connectionInfo = connectionInfo;
        rulesRunning = new ArrayList<Rule>();

        File configFile = new File(Constants.CONFIG_FILE);
        XMLInterface.checkConfigFile(configFile);

        database = new Database(connectionInfo);
        progressWindow = new ProgressWindow(this);

        // to guarantee thread safety
        tablesOccupied = Collections.synchronizedSet(new HashSet<String>());
        tablesUsed = Collections.synchronizedSet(new HashSet<MySQLTable>());

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

        if (setDocument == null) throw new IllegalArgumentException("SetDocument is Null");

        // parse the document
        MaskingSetDocument.MaskingSet maskingSet = setDocument.getMaskingSet();
        RulesDocument.Rules rules = maskingSet.getRules();

        // a dummy root rule; it is not displayed inside main window(rules have to start somewhere)
        Rule rootRule = RulesDocument.Factory.newInstance().addNewRules().addNewRule();

        rootRule.setId("Root");
        rootRule.setTarget("Root");
        rootRule.setRuleType(RuleType.SHUFFLE);
        rootRule.addNewShuffle();

        // root is not displayed; it only creates number of columns TODO make it more generic
        RuleNode root = new RuleNode(rootRule);

        // populate the tree with the nodes we parsed out
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

        // get the list of all rules that yet to be run in the order they suppose to run
        LinkedList<Rule> rulesToRun = getChildren(root);
        executor = Executors.newFixedThreadPool(numberOfThreadsAllowed);

        executeThreads(rulesToRun);
        return 0;
    }

    private void executeThreads(List<Rule> list) {

        for (Rule rule : list) {

            Future<Rule> future;
            if (!rule.getDisabled()) {
                publish("Rule " + rule.getId() + " starts running");
                logger.info("Rule " + rule.getId() + " starts running");
                rulesRunning.add(rule);
                try {
                    tablesUsed.add(database.getTable(rule.getTarget()));
                    RuleReader.Builder builder = new RuleReader.Builder()
                            .rule(rule)
                            .connection(database.getConnection("For running rule" + rule.getId()))
                            .databaseName(database.getDatabaseName())
                            .setReader(this)
                            .mySQLTable(database.getTable(rule.getTarget()));
                    executor.submit(builder.build());
                }
                catch (SQLException exc) {
                    logger.error(exc + System.lineSeparator() + StackTrace.getStringFromStackTrace(exc));
                } catch (XMLParseException e) {
                    logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                }
            }
        }
//        com.pearson.Utilities.LoggerUtils.printRuleArray(rulesRunning);
    }

    public boolean addTarget(String target) {
        synchronized (tablesOccupied) {
            return tablesOccupied.add(target);
        }
    }

    /**
     * This method handles all the work that needs to be done on the GUI related part
     * that happens after a rule is done. Rule reader *has* to call this method.
     * @param doneRule
     * @param isSuccessful
     */
    public synchronized void updateDoneRule(Rule doneRule, boolean isSuccessful) {
        synchronized (rulesRunning) {
            tablesOccupied.remove(doneRule.getTarget());
            rulesRunning.remove(doneRule);
//            com.pearson.Utilities.LoggerUtils.printRuleArray(rulesRunning);
            if (isSuccessful) {
                publish("Rule " + doneRule.getId() + " has completed running");
                if (!XMLInterface.isLeaf(doneRule)) {
                    publish("Running leaf rules of rule " + doneRule.getId());
                    List<Rule> ruleList = Arrays.asList(doneRule.getDependencies().getRuleArray());
                    executeThreads(ruleList);
                }
            }

            if (rulesRunning.isEmpty()) {
                logger.debug("Finished running all rules");
                int numberOfOpenConnections = 0;
                try {
                    numberOfOpenConnections = database.getNumberOfOpenConnections();
                } catch (SQLException e) {
                    logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                }
                if (numberOfOpenConnections != 0) {
                    logger.debug("Opened connections " + numberOfOpenConnections);
                    logger.debug("Total connections opened " + database.getTotalOpenedConnections());
                }

                List<String> metadata = database.getConnectionsMetadata();
                for (String entry : metadata) {
                    logger.debug("Unclosed connection: " + entry);
                }
                try {
                    cleanUp();
                } catch (SQLException e) {
                    logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                }
            }
        }
    }

    public synchronized void addToLog(String s) {
        publish(s);
    }

    private void cleanUp() throws SQLException {
        for (MySQLTable table : tablesUsed) {
            try {
                MySQLTableWorker worker = new MySQLTableWorker(table, database.getConnection("Cleaning up datascrubber_rowid"), database.getDatabaseName());
                Column autoIncrementColumn = worker.getAutoIncrementColumn();
                if (autoIncrementColumn != null && autoIncrementColumn.getName().equals("datascrubber_rowid")) {
                    worker.deleteAutoIncrementColumn();
                }
                worker.cleanupAutomatic();
            } catch (SQLException e) {
                logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            }
        }
        database.cleanUp();
    }
}
