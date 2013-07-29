package com.pearson.Readers;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.SQL.Column;
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
            MySQLDataType dataType = database.getTable(rule.getTarget()).columns.get(columnName).getType();

            try {
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
                disableConstraints();
                setToRandom(dataType);
                enableConstraints();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (actionType == SubstitutionActionType.SET_TO_NULL) {
            try {
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
                disableConstraints();
                setToNull();
                enableConstraints();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {
            try {
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
                disableConstraints();
                mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), rule.getSubstitute().getNumericValue());
                enableConstraints();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        mySQLTable.cleanResourses();

    }

    private void setToRandom(MySQLDataType dataType) throws SQLException {

        if(mySQLTable.getAutoIncrementColumn() == null){
            mySQLTable.addAutoIncrementColumn();
        }

        Column targetColumn = database.getTable(mySQLTable.getTableName()).columns.get(rule.getSubstitute().getColumn());
        for (int i = 0; i < mySQLTable.getNumberOfRows(targetColumn.getName()); i++) {
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

        try {
            mySQLTable.deleteAutoIncrementColumn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
