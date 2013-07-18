package com.pearson.Readers;

import com.pearson.Interface.RuleNode;
import com.pearson.SQL.Database;
import noNamespace.MaskingSetDocument;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.RulesDocument;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ruslan Kiselev
 *         Date: 7/15/13
 *         Time: 2:59 PM
 *         Project Name: DataScrubber
 */
public class SetReader implements Runnable {

    MaskingSetDocument setDocument = null;
    Database database;
    private int numberOfThreadsAllowed = 50;


    public SetReader(MaskingSetDocument setDocument, Database database) {
        this.setDocument = setDocument;
        this.database = database;
    }

    /**
     * Returns a Tree composed of rules inside the rules set. The first level of rules are children of root
     * the root itself is masking set object
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

    public static void executeThreads(LinkedList<Rule> threads, int threadPoolSize) {

        HashSet<String> tablesOccupied = new HashSet<>();

        if ((threads == null) || threads.isEmpty() || (threadPoolSize <= 0)) return;

        try {
            ExecutorService threadExecutor = Executors.newFixedThreadPool(threadPoolSize);
            while (!threads.isEmpty()) {
                if(!tablesOccupied.contains(threads.getFirst().getTarget())) {
                    Rule ruleToRun = threads.remove();
                    tablesOccupied.add(ruleToRun.getTarget());
                    threadExecutor.execute(new RuleReader(ruleToRun));
                }
            }

            threadExecutor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Multithreading logic
    public void run(Database database) {

        RuleNode root = getRulesTree(this.setDocument);

        // get the list of all rules that yet to be run in the order they suppose to run
        LinkedList<Rule> rulesToRun = getRulesAsList(root);

        executeThreads(rulesToRun, numberOfThreadsAllowed);
    }

    private LinkedList<Rule> getRulesAsList(RuleNode root) {

        LinkedList<Rule> rulesToReturn = new LinkedList<>();

        // Breadth-first search
        Queue queue = new LinkedList();
        Enumeration it  = root.children();

        while(it.hasMoreElements()){
            // add all first level rules
            RuleNode firstLevelRule = (RuleNode) it.nextElement();
            rulesToReturn.add(firstLevelRule.getRule());
            queue.add(firstLevelRule.getRule());
        }

        while(!queue.isEmpty()) {
            RuleNode parentNode = (RuleNode) queue.remove();
            RuleNode childNode = null;

            it = parentNode.children();

            while(it.hasMoreElements()) {
                childNode = (RuleNode) it.nextElement();
                rulesToReturn.add(childNode.getRule());
                queue.add(childNode);
                rulesToReturn.add(childNode.getRule());
            }
        }

        return rulesToReturn;
    }


    public void run() {

        run(database);
    }

    public int getNumberOfThreadsAllowed() {
        return numberOfThreadsAllowed;
    }

    public void setNumberOfThreadsAllowed(int numberOfThreadsAllowed) {
        this.numberOfThreadsAllowed = numberOfThreadsAllowed;
    }
}
