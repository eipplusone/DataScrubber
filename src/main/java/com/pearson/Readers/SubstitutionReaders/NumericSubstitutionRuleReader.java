package com.pearson.Readers.SubstitutionReaders;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Database.SQL.Column;
import noNamespace.SubstitutionActionType;
import noNamespace.SubstitutionRule;
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

    public NumericSubstitutionRuleReader(Builder builder) throws SQLException {
       super(builder);
    }

    public void runRule() throws SQLException {

        SubstitutionActionType.Enum actionType;

        SubstitutionRule dummy = rule.getSubstitute();
        actionType = dummy.getSubstitutionActionType();

        createAutoIncrementColumn(mySQLTable, mySQLTableWorker);

        if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
            String columnName = rule.getSubstitute().getColumn();
            MySQLDataType dataType = mySQLTableWorker.getMySQLTable().columns.get(columnName).getType();
            setToRandom(dataType);

        } else if (actionType == SubstitutionActionType.SET_TO_NULL) {
            setToNull();

        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {
            mySQLTableWorker.setColumnToValue(rule.getSubstitute().getColumn(), rule.getSubstitute().getNumericValue());
        }

        mySQLTableWorker.cleanupAutomatic();
    }

    private void setToRandom(MySQLDataType dataType) throws SQLException {

        Column targetColumn = mySQLTableWorker.getMySQLTable().columns.get(rule.getSubstitute().getColumn());
        for (int i = 0; i <= mySQLTableWorker.getNumberOfRows(targetColumn.getName()); i++) {
            Random random = new Random();
            if (dataType == MySQLDataType.BIGINT || dataType == MySQLDataType.INT)
                mySQLTableWorker.updateRow(random.nextInt(2147483647), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.SMALLINT)
                mySQLTableWorker.updateRow(random.nextInt(32767), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.TINYINT)
                mySQLTableWorker.updateRow(random.nextInt(127), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.MEDIUMINT)
                mySQLTableWorker.updateRow(random.nextInt(8388607), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.FLOAT)
                mySQLTableWorker.updateRow(random.nextFloat(), targetColumn.getName(), i);
            else if (dataType == MySQLDataType.DOUBLE)
                mySQLTableWorker.updateRow(random.nextDouble(), targetColumn.getName(), i);
        }
    }
}
