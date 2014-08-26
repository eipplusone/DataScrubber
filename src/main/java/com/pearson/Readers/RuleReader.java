package com.pearson.Readers;

import com.pearson.Database.MySQL.MySQLTable;
import com.pearson.Database.SQL.Database;
import com.pearson.Readers.SubstitutionReaders.DateSubstitutionRuleReader;
import com.pearson.Readers.SubstitutionReaders.NumericSubstitutionRuleReader;
import com.pearson.Readers.SubstitutionReaders.StringSubstitutionRuleReader;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.SubstitutionDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @author Ruslan Kiselev
 *         Date: 7/15/13
 *         Time: 3:42 PM
 *         Project Name: DataScrubber
 *
 *         Rule reader is called everytime we need to parse a rule. Shuffling is implemented within
 *         RuleReader, whereas all the substitution methods are handled by calling the respective substitution
 *         class.
 */
public class RuleReader implements Callable<Rule> {

    private static Logger logger = LoggerFactory.getLogger(RuleReader.class.getName());

    private Rule rule;
    private Database database;

    public RuleReader(Rule rule, Database database) {
        this.rule = rule;
        this.database = database;
    }

    public Rule call(Database database) throws SQLException, FileNotFoundException {

        if (rule.getRuleType() == RuleType.SHUFFLE) return callShuffle(database);
        else if (rule.getRuleType() == RuleType.SUBSTITUTION) return callSubstitution(database);
        else throw new IllegalArgumentException("XML file is invalid");
    }

    private Rule callShuffle(Database database) throws SQLException {

        MySQLTable mySQLTable = database.getTable(rule.getTarget());

        // BUG; having already done these setting in a parent rule causes the child rule,
        // if it's the rule that targets the same table
        // to exit with exception right away.

        // Aug 12th: Checked on a test set, wasn't able to imitate the bug.
        // Thereotically it shouldn't matter since the quieries that these methods
        // do if ran, don't affect the running of the program. English skilzz

        mySQLTable.getConnectionConfig().setDefaultDatabase(database);
        mySQLTable.getConnectionConfig().disableForeignKeyConstraints();
        mySQLTable.getConnectionConfig().disableUniqueChecks();

        boolean autoIncrementColumnCreated = false;
        if (mySQLTable.getAutoIncrementColumn() == null) {
            mySQLTable.addAutoIncrementColumn();
            autoIncrementColumnCreated = true;
        }

        // begin shuffling
        logger.debug("Rule " + rule.getId() + " finished setting up; begin shuffling");
        for (int i = 0; i < mySQLTable.getNumberOfRows(); i++) {
            // two random rows to be swapped
            String[] columnNamesArray = rule.getShuffle().getColumnArray();
            ResultSet randomRow1 = mySQLTable.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
            ResultSet randomRow2 = mySQLTable.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
            mySQLTable.swap(randomRow1, randomRow2, new ArrayList(Arrays.asList(columnNamesArray)));
        }
        // get the result set that has already been shuffled
        logger.debug("Rule " + rule.getId() + " finished shuffling");

        if (autoIncrementColumnCreated) {
            logger.debug("Rule " + rule.getId() + ": Deleting autoincrement column");
            mySQLTable.deleteAutoIncrementColumn();
            autoIncrementColumnCreated = false;
        }

        // clean all the resources
        logger.debug("Rule " + rule.getId() + ":Cleaning resources");
        mySQLTable.cleanResourses();
        logger.debug("Rule " + rule.getId() + ":Finished running; returning to dispatch thread");

        return rule;
    }

    private Rule callSubstitution(Database database) throws SQLException, FileNotFoundException {

        SubstitutionDataType.Enum dataType = rule.getSubstitute().getSubstitutionDataType();

        if (dataType == SubstitutionDataType.DATE) return new DateSubstitutionRuleReader(rule, database).call();
        else if (dataType == SubstitutionDataType.NUMERIC)
            return new NumericSubstitutionRuleReader(rule, database).call();
        else return new StringSubstitutionRuleReader(rule, database).call();
    }

    @Override
    public Rule call() throws SQLException, FileNotFoundException, InterruptedException {

        boolean done = false;

        while (!done) {

            // if we succeeded in adding table to the tablesOccupied(if no other thread submitted it before us)
            // add() returns false if the element is already present in the set; since the set is
            // thread-safe, that means only one rule at a time would be able to add table to the set
            if (SetReader.addTarget(rule.getTarget())) {
                try {
                    if (!rule.isSetDisabled() && rule.getDisabled() == false) {
                        logger.info("Rule " + rule.getId() + " has started running");
                        call(database);
                    }
                } catch (SQLException e) {
                    logger.error("Rule " + rule.getId() + " has exited with an SQL exception");
                    throw new MySQLException(e, rule.getId());
                }
                done = true;
            } else {
                Thread.sleep(500);
                logger.info("Rule " + rule.getId() + " is waiting for table " + rule.getTarget());
            }
        }

        return rule;
    }
}
