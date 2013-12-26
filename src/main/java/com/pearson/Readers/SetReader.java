package com.pearson.Readers;

import com.pearson.Database.DatabaseSettings;
import com.pearson.Database.SQL.Database;
import com.pearson.Interface.Interfaces.XMLInterface;
import com.pearson.Interface.RuleNode;
import com.pearson.Utilities.StackTrace;
import noNamespace.MaskingSetDocument;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.RulesDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ruslan Kiselev
 *         Date: 7/15/13
 *         Time: 2:59 PM
 *         Project Name: DataScrubber
 */
public class SetReader implements Runnable {

    public static Logger logger = LoggerFactory.getLogger(SetReader.class.getName());

    public static Set<String> tablesOccupied;
    //variables
    MaskingSetDocument setDocument = null;
    Database database;
    private int numberOfThreadsAllowed = 50;


    public SetReader(MaskingSetDocument setDocument, Database database) {
        this.setDocument = setDocument;
        this.database = database;
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

    /**
     * Static method that returns if a rule occupies given table at the moment
     * @param target
     * @return
     */
    public static boolean isTableOccupied(String target) {
        return tablesOccupied.contains(target);
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

    public void run() {

        RuleNode root = getRulesTree(this.setDocument);

        // get the list of all rules that yet to be run in the order they suppose to run
        LinkedList<Rule> rulesToRun = getChildren(root);

        DatabaseSettings databaseSettings = database.getDatabaseSettings();

        try {
            if (databaseSettings.isTriggersExist()) {

                try {
                    databaseSettings.disableTriggers();
                } catch (SQLException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                }


                try {
                    executeThreads(rulesToRun, numberOfThreadsAllowed);
                } catch (ExecutionException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                } catch (InterruptedException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                }

                try {
                    databaseSettings.enableTriggers();
                } catch (SQLException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                }

            }
            // if database have no triggers
            else {
                try {
                    executeThreads(rulesToRun, numberOfThreadsAllowed);
                } catch (InterruptedException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                } catch (ExecutionException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                }
            }
        } catch (SQLException e) {
            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
        }
    }

    private void executeThreads(LinkedList<Rule> firstLevelRules, int threadPoolSize) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        tablesOccupied = Collections.synchronizedSet(new HashSet<String>());
        Set rulesDone = Collections.synchronizedSet(new HashSet<Rule>());


        executeThreads(firstLevelRules, executor, rulesDone);
    }

    private void executeThreads(List<Rule> list, ExecutorService executor, Set rulesDone) throws ExecutionException, InterruptedException {

        Set rulesRunning = Collections.synchronizedSet(new HashSet());

        Iterator<Rule> it = list.iterator();

        for (Rule rule : list) {

            Future<Rule> future;
            if (!rule.getDisabled()) {
                logger.info("Rule " + rule.getId() + " starts running");
                future = executor.submit(new RuleReader(rule, database));
                // we have to wait for a little in order for RuleReader to startup running
                Thread.sleep(10);
                tablesOccupied.add(rule.getTarget());
                rulesRunning.add(future);
            }
        }

        while (!rulesRunning.isEmpty()) {

            synchronized (rulesRunning) {
                for (Object futureObject : rulesRunning) {
                    Future<Rule> future = (Future<Rule>) futureObject;
                    if (future.isDone()) {
                        Rule doneRule = future.get();
                        tablesOccupied.remove(future.get().getTarget());
                        rulesRunning.remove(future);

                        logger.info("Rule " + doneRule.getId() + " has completed running");

                        if (!XMLInterface.isLeaf(doneRule))
                            executeThreads(Arrays.asList(doneRule.getDependencies().getRuleArray()), executor, rulesDone);
                        else return;
                    }
                }
            }
        }


    }

    public void setNumberOfThreadsAllowed(int numberOfThreadsAllowed) {
        this.numberOfThreadsAllowed = numberOfThreadsAllowed;
    }
}
