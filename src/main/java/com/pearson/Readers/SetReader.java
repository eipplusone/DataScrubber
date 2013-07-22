package com.pearson.Readers;

import com.pearson.Interface.RuleNode;
import com.pearson.SQL.Database;
import com.pearson.Utilities.ThreadExectutor;
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

    private void executeThreads(LinkedList<Rule> firstLevelRules, int threadPoolSize) {

        ThreadExectutor.initialise(threadPoolSize);

        for(Rule rule: firstLevelRules){
            ThreadExectutor.execute(new RuleReader(rule, database));
        }
    }

    public void run(Database database) {

        RuleNode root = getRulesTree(this.setDocument);

        // get the list of all rules that yet to be run in the order they suppose to run
        LinkedList<Rule> rulesToRun = getChildren(root);

        executeThreads(rulesToRun, numberOfThreadsAllowed);
    }

    private LinkedList<Rule> getChildren(RuleNode root) {

        LinkedList<Rule> rulesToReturn = new LinkedList<>();

        // Breadth-first search
        Enumeration it  = root.children();

        while(it.hasMoreElements()){
            // add all first level rules
            RuleNode firstLevelRule = (RuleNode) it.nextElement();
            rulesToReturn.add(firstLevelRule.getRule());
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
