package com.pearson.Readers.SubstitutionReaders;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Database.MySQL.MySQLTable;
import com.pearson.Database.SQL.Column;
import com.pearson.Database.SQL.Database;
import noNamespace.Rule;
import noNamespace.SubstitutionActionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Random;

/**
 * @author Ruslan Kiselev
 *         Date: 7/17/13
 *         Time: 4:23 PM
 *         Project Name: DataScrubber
 */
public class NumericSubstitutionRuleReader extends SubstitutionReader {

    private static Logger logger = LoggerFactory.getLogger(NumericSubstitutionRuleReader.class.getName());

    public NumericSubstitutionRuleReader(Rule rule, Database database, MySQLTable mySQLTable) {

        super(rule, database, mySQLTable);
    }

    public Rule call() throws SQLException {

        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();
        prepareToRun();

        if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
            String columnName = rule.getSubstitute().getColumn();
            MySQLDataType dataType = database.getTable(rule.getTarget()).columns.get(columnName).getType();
            setToRandom(dataType);

        } else if (actionType == SubstitutionActionType.SET_TO_NULL) {
            setToNull();

        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {
            mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), rule.getSubstitute().getNumericValue());
        }

        mySQLTable.cleanResourses();

        return rule;
    }

    private void setToRandom(MySQLDataType dataType) throws SQLException {

        Column targetColumn = database.getTable(mySQLTable.getTableName()).columns.get(rule.getSubstitute().getColumn());
        for (int i = 0; i <= mySQLTable.getNumberOfRows(targetColumn.getName()); i++) {
            Random random = new Random();
            if (dataType == MySQLDataType.BIGINT || dataType == MySQLDataType.INT)
                mySQLTable.updateRow(random.nextInt(2147483647), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.SMALLINT)
                mySQLTable.updateRow(random.nextInt(32767), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.TINYINT)
                mySQLTable.updateRow(random.nextInt(127), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.MEDIUMINT)
                mySQLTable.updateRow(random.nextInt(8388607), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.FLOAT)
                mySQLTable.updateRow(random.nextFloat(), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.DOUBLE)
                mySQLTable.updateRow(random.nextDouble(), targetColumn.getName(), i);
        }
    }
}
