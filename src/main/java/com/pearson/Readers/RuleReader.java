package com.pearson.Readers;

import com.pearson.SQL.Column;
import com.pearson.SQL.Database;
import com.pearson.SQL.MySQLTable;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.SubstitutionActionType;
import noNamespace.SubstitutionDataType;

import java.awt.print.PrinterAbortException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Ruslan Kiselev
 *         Date: 7/15/13
 *         Time: 3:42 PM
 *         Project Name: DataScrubber
 */
public class RuleReader implements Runnable {

    private Rule rule;
    private Database database;

    public RuleReader(Rule rule) {
        this.rule = rule;
        this.database = database;
    }

    public void run(Database database) throws SQLException {
        if (rule.getRuleType() == RuleType.SHUFFLE) runShuffle(database);
        else if (rule.getRuleType() == RuleType.SUBSTITUTION) runSubstitution(database);
    }

    private void runShuffle(Database database) throws SQLException {

        MySQLTable mySQLTable = database.getTable(rule.getTarget());

        mySQLTable.disableForeignKeyConstraints();
        mySQLTable.disableUniqueChecks();

        // create an unique key column if there is no such column
        Column autoIncrementColumn = mySQLTable.getAutoIncrementColumn();
        if (autoIncrementColumn == null) {
            mySQLTable.addAutoIncrementColumn();
        }


        // begin shuffling
        for (int i = 0; i < mySQLTable.getNumberOfRows(); i++) {
            // two random rows to be swapped
            String[] columnNamesArray = rule.getShuffle().getColumnArray();
            ResultSet randomRow1 = mySQLTable.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
            ResultSet randomRow2 = mySQLTable.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
            mySQLTable.swap(randomRow1, randomRow2, new ArrayList(Arrays.asList(columnNamesArray)));
        }
        // get the result set that has already been shuffled

        mySQLTable.enableForeignKeyConstraints();
        mySQLTable.enableUniqueChecks();

        // clean all the resources
        mySQLTable.cleanResourses();
    }

    private void runSubstitution(Database database) {

        SubstitutionDataType.Enum dataType = rule.getSubstitute().getSubstitutionDataType();

        if(dataType == SubstitutionDataType.DATE) new DateSubstitutionRuleReader(rule, database).run();
        else if (dataType == SubstitutionDataType.NUMERIC) new NumericSubstitutionRuleReader(rule, database).run();
        else if (dataType == SubstitutionDataType.STRING) new StringSubstitutionRuleReader(rule, database).run();
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    public void run() {
        try {
            run(database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
