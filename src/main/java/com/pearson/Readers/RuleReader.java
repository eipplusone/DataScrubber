package com.pearson.Readers;

import com.pearson.Database.MySQL.MySQLTable;
import com.pearson.Database.SQL.Database;
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

        mySQLTable.getConnectionConfig().setDefaultDatabase(database);
        mySQLTable.getConnectionConfig().disableForeignKeyConstraints();
        mySQLTable.getConnectionConfig().disableUniqueChecks();

        if (mySQLTable.getAutoIncrementColumn() == null) mySQLTable.addAutoIncrementColumn();

        // begin shuffling
        for (int i = 0; i < mySQLTable.getNumberOfRows(); i++) {
            // two random rows to be swapped
            String[] columnNamesArray = rule.getShuffle().getColumnArray();
            ResultSet randomRow1 = mySQLTable.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
            ResultSet randomRow2 = mySQLTable.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
            mySQLTable.swap(randomRow1, randomRow2, new ArrayList(Arrays.asList(columnNamesArray)));
        }
        // get the result set that has already been shuffled

        mySQLTable.deleteAutoIncrementColumn();

        // clean all the resources
        mySQLTable.cleanResourses();

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

            if (!SetReader.isTableOccupied(rule.getTarget())) {
                // if we succeeded in adding table to the tablesOccupied(if no other thread submitted it before us)
                if (SetReader.tablesOccupied.add(rule.getTarget())) {
                    try {
                        if (!rule.isSetDisabled() && rule.getDisabled() == false) call(database);
                    } catch (SQLException e) {
                        logger.error("Rule " + rule.getId() + " has exited with an SQL exception");
                        throw e;
                    }
                    done = true;
                }
            } else {
                Thread.sleep(500);
            }
        }

        return rule;
    }
}
