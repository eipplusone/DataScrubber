package com.pearson.Readers;

import com.pearson.Database.MySQL.MySQLTable;
import com.pearson.Database.SQL.Database;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.SubstitutionDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public void run(Database database) throws SQLException {
        if (rule.getRuleType() == RuleType.SHUFFLE) runShuffle(database);
        else if (rule.getRuleType() == RuleType.SUBSTITUTION) runSubstitution(database);
    }

    private void runShuffle(Database database) throws SQLException {

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

        try {
            mySQLTable.deleteAutoIncrementColumn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mySQLTable.getConnectionConfig().enableForeignKeyConstraints();
        mySQLTable.getConnectionConfig().enableUniqueChecks();

        // clean all the resources
        mySQLTable.cleanResourses();
    }

    private void runSubstitution(Database database) {

        SubstitutionDataType.Enum dataType = rule.getSubstitute().getSubstitutionDataType();

        if (dataType == SubstitutionDataType.DATE) new DateSubstitutionRuleReader(rule, database).run();
        else if (dataType == SubstitutionDataType.NUMERIC) new NumericSubstitutionRuleReader(rule, database).run();
        else if (dataType == SubstitutionDataType.STRING) new StringSubstitutionRuleReader(rule, database).run();
    }

    @Override
    public Rule call() throws Exception {

        while (true) {

            if (!SetReader.isTableOccupied(rule.getTarget())) {
                try {
                    // if we succeeded in adding table to the tablesOccupied(if no other thread submitted it before us)
                    if (SetReader.tablesOccupied.add(rule.getTarget())){
                        run(database);
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    break;
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return rule;
    }
}
