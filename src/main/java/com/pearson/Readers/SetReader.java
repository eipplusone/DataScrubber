package com.pearson.Readers;

import com.pearson.Database.DatabaseSettings;
import com.pearson.Database.SQL.Database;
import com.pearson.Interface.Interfaces.XMLInterface;
import com.pearson.Interface.RuleNode;
import com.pearson.Interface.Windows.ProgressWindow;
import com.pearson.Utilities.StackTrace;
import noNamespace.MaskingSetDocument;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.RulesDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.ExecutionException;
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


    public SetReader(final MaskingSetDocument setDocument, Database database, final String name) {
        this.setDocument = setDocument;
        this.database = database;
        this.setName = name;

        System.out.println();


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

        // get the list of all rules that yet to be run in the order they suppose to run
        LinkedList<Rule> rulesToRun = getChildren(root);

        DatabaseSettings databaseSettings = database.getDatabaseSettings();
        try {
            if (databaseSettings.isTriggersExist()) {
                databaseSettings.disableTriggers();
                executeThreads(rulesToRun, numberOfThreadsAllowed);
                databaseSettings.enableTriggers();
            }
            // if database has no triggers
            else {
                executeThreads(rulesToRun, numberOfThreadsAllowed);
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

    /**
     * Preparation method for the main execute threads method. Initializes worker that runs threads,
     * set that keeps track of occupied tables in order to prevent deadlock(from running multiple threads
     * on the same table)
     *
     * @param firstLevelRules - list of the first level rules in a set
     * @param threadPoolSize  - number of threads
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void executeThreads(LinkedList<Rule> firstLevelRules, int threadPoolSize) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        tablesOccupied = Collections.synchronizedSet(new HashSet<String>());

        executeThreads(firstLevelRules, executor);
    }

    /**
     * Main method for executing the rule set. It starts off by submitting the first level rules to the executor,
     * waiting for them to complete. It then cycles through the future object list, checking if anyone finished running yet.
     * If thread detects a finished rule, it calls itself on the children of that particular rule.
     *
     * @param list     - list of first level rules to run
     * @param executor - Executor to run the threads in
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void executeThreads(List<Rule> list, ExecutorService executor) throws ExecutionException, InterruptedException {

        final Set<Future<Rule>> rulesRunning = Collections.synchronizedSet(new HashSet<Future<Rule>>());

        for (Rule rule : list) {

            Future<Rule> future;
            if (!rule.getDisabled()) {
                publish("Rule " + rule.getId() + " starts running");
                logger.info("Rule " + rule.getId() + " starts running");
                future = executor.submit(new RuleReader(rule, database));
                rulesRunning.add(future);
            }
        }


        synchronized (rulesRunning) {
            while (!rulesRunning.isEmpty()) {

                Future<Rule> future;
                Iterator<Future<Rule>> iter = rulesRunning.iterator();
                while (iter.hasNext()) {
                    future = iter.next();
                    if (future.isDone()) {
                        Rule doneRule = future.get();
                        logger.debug("Rule " + doneRule.getId() + ":Returned to SwingWorker");
                        rulesRunning.remove(future);
                        tablesOccupied.remove(doneRule.getTarget());
                        publish("Rule " + doneRule.getId() + " has completed running");
                        logger.info("Rule " + doneRule.getId() + " has completed running");
                        progressWindow.setProgress(progressWindow.getProgress() + 100 / setDocument.getMaskingSet().getRules().sizeOfRuleArray());

                        if (!XMLInterface.isLeaf(doneRule))
                            executeThreads(Arrays.asList(doneRule.getDependencies().getRuleArray()), executor);
                        else {
                            return;
                        }
                    }
                }
            }
            logger.debug("Finished running all the rules");
        }

    }

    public static boolean addTarget(String target) {
        synchronized (tablesOccupied) {
            return tablesOccupied.add(target);
        }
    }
}
