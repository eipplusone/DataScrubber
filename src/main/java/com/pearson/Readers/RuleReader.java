package com.pearson.Readers;

import com.pearson.Database.MySQL.MySQLTableWorker;
import com.pearson.Database.SQL.Database;
import com.pearson.Database.SQL.MySQLTable;
import com.pearson.Readers.SubstitutionReaders.DateSubstitutionRuleReader;
import com.pearson.Readers.SubstitutionReaders.NumericSubstitutionRuleReader;
import com.pearson.Readers.SubstitutionReaders.StringSubstitutionRuleReader;
import com.pearson.Utilities.StackTrace;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.ShuffleRule;
import noNamespace.SubstitutionDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.modelmbean.XMLParseException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Ruslan Kiselev
 *         Date: 7/15/13
 *         Time: 3:42 PM
 *         Project Name: DataScrubber

 *         Rule reader is called everytime we need to parse a rule. Shuffling is implemented within
 *         RuleReader, whereas all the substitution methods are handled by calling the respective substitution
 *         class.
 */
public abstract class RuleReader implements Runnable, RuleRunner {

    private static Logger logger = LoggerFactory.getLogger(RuleReader.class.getName());

    protected Rule rule;
    protected Connection connection;
    protected SetReader setReader;
    protected String databaseName;
    protected MySQLTable mySQLTable;

    protected RuleReader(Builder builder) {

        this.rule = builder.rule;
        this.connection = builder.connection;
        this.setReader = builder.setReader;
        this.databaseName = builder.databaseName;
        this.mySQLTable = builder.mySQLTable;
    }

    public static class Builder {

        private Rule rule;
        private Connection connection;
        private SetReader setReader;
        private String databaseName;
        private MySQLTable mySQLTable;

        public Builder rule(Rule rule) {
            this.rule = rule; return this;
        }

        public Builder connection(Connection connection) {
            this.connection = connection; return this;
        }

        public Builder setReader(SetReader setReader) {
            this.setReader = setReader; return this;
        }

        public Builder databaseName(String databaseName) {
            this.databaseName = databaseName; return this;
        }

        public Builder mySQLTable(MySQLTable mySQLTable) {
            this.mySQLTable = mySQLTable; return this;
        }

        public RuleReader build() throws XMLParseException, SQLException {
            if (rule.getRuleType() == RuleType.SHUFFLE) return new ShuffleRuleReader(this);
            else if (rule.getRuleType() == RuleType.SUBSTITUTION) {

                SubstitutionDataType.Enum dataType = rule.getSubstitute().getSubstitutionDataType();

                if (dataType == SubstitutionDataType.DATE) return new DateSubstitutionRuleReader(this);
                else if (dataType == SubstitutionDataType.NUMERIC) return new NumericSubstitutionRuleReader(this);
                else if (dataType == SubstitutionDataType.STRING) return new StringSubstitutionRuleReader(this);
                else throw new XMLParseException("SubstitutionDataType doesn't match any specified types.");
            }
            else throw new XMLParseException("XML file is invalid");
        }

        private class ShuffleRuleReader extends RuleReader {
            public ShuffleRuleReader(Builder builder) {
                super(builder);
            }

            public void runRule() throws SQLException {

                MySQLTableWorker mysqlTableWorker = new MySQLTableWorker(mySQLTable, connection, databaseName);

                // begin shuffling
                logger.debug("Rule " + rule.getId() + " finished setting up; begin shuffling");

                for (int i = 0; i < mysqlTableWorker.getNumberOfRows(); i++) {
                    // two random rows to be swapped
                    String[] columnNamesArray = rule.getShuffle().getColumnArray();
                    ResultSet randomRow1 = mysqlTableWorker.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
                    ResultSet randomRow2 = mysqlTableWorker.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
                    mysqlTableWorker.swap(randomRow1, randomRow2, new ArrayList(Arrays.asList(columnNamesArray)));
                }

                // get the result set that has already been shuffled
                logger.debug("Rule " + rule.getId() + " finished shuffling");

                // clean all the resources
                logger.debug("Rule " + rule.getId() + ":Cleaning resources");
                mysqlTableWorker.cleanupAutomatic();
                logger.debug("Rule " + rule.getId() + ":Finished running; returning to dispatch thread");
            }
        }
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
                        runRule();
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
