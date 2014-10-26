package com.pearson.Readers;

import com.pearson.Database.MySQL.MySQLTable;
import com.pearson.Database.SQL.Database;
import com.pearson.Readers.SubstitutionReaders.DateSubstitutionRuleReader;
import com.pearson.Readers.SubstitutionReaders.NumericSubstitutionRuleReader;
import com.pearson.Readers.SubstitutionReaders.StringSubstitutionRuleReader;
import com.pearson.Utilities.StackTrace;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.SubstitutionDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.modelmbean.XMLParseException;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Ruslan Kiselev
 *         Date: 7/15/13
 *         Time: 3:42 PM
 *         Project Name: DataScrubber
 *         <p/>
 *         Rule reader is called everytime we need to parse a rule. Shuffling is implemented within
 *         RuleReader, whereas all the substitution methods are handled by calling the respective substitution
 *         class.
 */
public class RuleReader implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(RuleReader.class.getName());

    private Rule rule;
    private Database database;
    private SetReader setReader;

    public RuleReader(Rule rule, Database database, SetReader setReader) {
        this.rule = rule;
        this.database = database;
        this.setReader = setReader;
    }

    public void call(Database database) throws SQLException, FileNotFoundException, XMLParseException {

        if (rule.getRuleType() == RuleType.SHUFFLE) callShuffle(database);
        else if (rule.getRuleType() == RuleType.SUBSTITUTION) callSubstitution(database);
        else throw new IllegalArgumentException("XML file is invalid");
    }

    private void callShuffle(Database database) throws SQLException {

        MySQLTable mySQLTable = database.getTable(rule.getTarget());

        // BUG; having already done these setting in a parent rule causes the child rule,
        // if it's the rule that targets the same table
        // to exit with exception right away.

        // Aug 12th: Checked on a test set, wasn't able to imitate the bug.
        // Theoretically it shouldn't matter since the queries that these methods
        // do if ran, don't affect the running of the program.

        mySQLTable.getConnectionConfig().setDefaultDatabase(database);
        mySQLTable.getConnectionConfig().disableForeignKeyConstraints();
        mySQLTable.getConnectionConfig().disableUniqueChecks();

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

        // clean all the resources
        logger.debug("Rule " + rule.getId() + ":Cleaning resources");
        mySQLTable.cleanResourses();
        logger.debug("Rule " + rule.getId() + ":Finished running; returning to dispatch thread");
    }

    private void callSubstitution(Database database) throws SQLException, FileNotFoundException, XMLParseException {

        String target = rule.getTarget();
        MySQLTable mySQLTable = database.getTable(target);

        /* TODO: WARNING! THIS LEAVES DATABASE IN A STATE DIFFERENT THAN FROM BEFORE!!! NEED TO FIGURE OUT A WAY TO FIX THIS!!!*/
        // or maybe it's session based so no need to worry
        mySQLTable.getConnectionConfig().setDefaultDatabase(database);
        mySQLTable.getConnectionConfig().disableForeignKeyConstraints();
        mySQLTable.getConnectionConfig().disableUniqueChecks();

        SubstitutionDataType.Enum dataType = rule.getSubstitute().getSubstitutionDataType();

        Rule returnRule = null;
        if (dataType == SubstitutionDataType.DATE)
            returnRule = new DateSubstitutionRuleReader(rule, database, mySQLTable).call();
        else if (dataType == SubstitutionDataType.NUMERIC)
            returnRule = new NumericSubstitutionRuleReader(rule, database, mySQLTable).call();
        else if (dataType == SubstitutionDataType.STRING)
            returnRule = new StringSubstitutionRuleReader(rule, database, mySQLTable).call();
        else throw new SQLException("SubstitutionDataType doesn't match any specified types.");

        mySQLTable.cleanResourses();
    }

    @Override
    public void run() {

        boolean done = false;

        try {
            while (!done) {

                // if we succeeded in adding table to the tablesOccupied(if no other thread submitted it before us)
                // add() returns false if the element is already present in the set; since the set is
                // thread-safe, that means only one rule at a time would be able to add table to the set
                if (setReader.addTarget(rule.getTarget())) {
                    if (!rule.isSetDisabled() && rule.getDisabled() == false) {
                        logger.info("Rule " + rule.getId() + " has started running");
                        call(database);
                    }
                    done = true;
                } else {
                    Thread.sleep(500);
                }
            }
            setReader.updateDoneRule(rule, true);
        } catch (Exception e) {
            setReader.addToLog("Rule " + rule.getId() + " has exited with an exception - see logs for details");
            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            setReader.updateDoneRule(rule, false); // let setReader know that we finished running it, but with exception
        }
    }
}
