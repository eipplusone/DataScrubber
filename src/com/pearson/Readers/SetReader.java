package pearson.Readers;

import pearson.Database.DatabaseSettings;
import pearson.Database.SQL.Database;
import pearson.Interface.Interfaces.XMLInterface;
import pearson.Interface.RuleNode;
import pearson.Utilities.StackTrace;
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
public class SetReader {

    public static Logger logger = LoggerFactory.getLogger(SetReader.class.getName());

    //todo : implement  tablesOccupied non-static
    public static Set<String> tablesOccupied;
    //variables
    MaskingSetDocument setDocument = null;
    Database database;


    public SetReader(MaskingSetDocument setDocument, Database database) {
        this.setDocument = setDocument;
        this.database = database;
    }

    /**
     * Returns a Tree composed of rules inside the rules set. The first level of rules are children of root
     * the root itself is masking set object
     */
    public static RuleNode getRulesTree(MaskingSetDocument setDocument) {

        RulesDocument.Rules rules = setDocument.getMaskingSet().getRules();


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

    public void run(int numberOfThreadsAllowed) {

        RuleNode root = getRulesTree(this.setDocument);

        // get the list of all rules that yet to be run in the order they suppose to run
        LinkedList<Rule> rulesToRun = getChildren(root);

        DatabaseSettings databaseSettings = database.getDatabaseSettings();

        try {
            if (databaseSettings.isTriggersExist()) {

                databaseSettings.disableTriggers();

                try {
                    executeThreads(rulesToRun, numberOfThreadsAllowed);
                } catch (ExecutionException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                } catch (InterruptedException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                } catch (Exception e) {
                    logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                }

                databaseSettings.enableTriggers();

            } else {
                try {
                    executeThreads(rulesToRun, numberOfThreadsAllowed);
                } catch (InterruptedException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                } catch (ExecutionException e) {
                    logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                } catch (Exception e) {
                    logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
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

    private void executeThreads(List<Rule> list, ExecutorService executor, Set rulesDone) {

        Set rulesRunning = Collections.synchronizedSet(new HashSet());
        Iterator<Rule> it = list.iterator();

        for (Rule rule : list) {

            Future<Rule> future = executor.submit(new RuleReader(rule, database));
            // we have to wait for a little in order for RuleReader to startup running
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            }
            tablesOccupied.add(rule.getTarget());
            rulesRunning.add(future);
        }

        while (!rulesRunning.isEmpty()) {

            synchronized (rulesRunning) {

                for (Object futureObject : rulesRunning) {

                    Future<Rule> future = (Future<Rule>) futureObject;
                    if (future.isDone()) {
                        Rule doneRule = null;
                        try {
                            doneRule = future.get();
                            if (!XMLInterface.isLeaf(doneRule))
                                executeThreads(Arrays.asList(doneRule.getDependencies().getRuleArray()), executor, rulesDone);
                        } catch (ExecutionException exc) {
                            // if there was an exception that halted the rule run, it would be caught here
                            logger.error(exc + System.lineSeparator() + StackTrace.getStringFromStackTrace(exc));
                        } catch (InterruptedException e) {
                            // if there was an exception that halted the rule run, it would be caught here
                            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
                        } finally {
                            tablesOccupied.remove(doneRule.getTarget());
                            rulesRunning.remove(future);
                        }
                    }
                }
            }
        }


    }
}

