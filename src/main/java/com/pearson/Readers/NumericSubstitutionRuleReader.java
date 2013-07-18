package com.pearson.Readers;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Rules.SubstitutionTypes.NumericSubstitutionTypes;
import com.pearson.SQL.Database;
import noNamespace.Rule;
import noNamespace.SubstitutionActionType;

import java.sql.SQLException;
import java.util.Random;

/**
 * @author Ruslan Kiselev
 *         Date: 7/17/13
 *         Time: 4:23 PM
 *         Project Name: DataScrubber
 */
public class NumericSubstitutionRuleReader extends SubstitutionReader {


    public NumericSubstitutionRuleReader(Rule rule, Database database) {

        super(rule, database);
    }

    public void run() {

        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();

        if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
            String columnName = rule.getSubstitute().getColumn();
            MySQLDataType dataType = database.getTable(mySQLTable.getTableName()).columns.get(columnName).getType();

            try {
                setToRandom(dataType);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (actionType == SubstitutionActionType.SET_TO_NULL) {
            try {
                setToNull();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {
            mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), rule.getSubstitute().getNumericValue());
        }

    }

    private void setToRandom(MySQLDataType dataType) throws SQLException {
        if(dataType == MySQLDataType.BIGINT || dataType == MySQLDataType.INT)
            mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), new Random().nextInt());
        else if (dataType == MySQLDataType.SMALLINT)
            mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), new Random().nextInt(32767));
        else if (dataType == MySQLDataType.TINYINT)
            mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), new Random().nextInt(127));
        else if (dataType == MySQLDataType.MEDIUMINT)
            mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), new Random().nextInt(8388607));
        else if (dataType == MySQLDataType.FLOAT)
            mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), new Random().nextFloat());
        else if (dataType == MySQLDataType.DOUBLE)
            mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), new Random().nextDouble());

    }
}
