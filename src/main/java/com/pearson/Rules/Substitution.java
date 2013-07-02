package com.pearson.Rules;

import com.pearson.SQL.MySQLTable;

import java.sql.SQLException;

/**
 * @author Ruslan Kiselev
 *         Date: 6/17/13
 *         Time: 3:23 PM
 *         Project Name: DataScrubber
 */
public class Substitution extends Rule{

    String columnName;

    public Substitution(String mySQLTable, String columnName) {

        super(mySQLTable);
        this.columnName = columnName;

    }

//    public void setToNull() throws SQLException {
//
//        disableConstraints();
//        mySQLTable.setColumnToNull(columnName);
//        enableConstraints();
//
//    }
//
//    private void disableConstraints() throws SQLException {
//
//        mySQLTable.disableUniqueChecks();
//        mySQLTable.disableForeignKeyConstraints();
//        mySQLTable.disableTriggers();
//
//    }
//
//    private void enableConstraints() throws SQLException {
//
//        mySQLTable.enableTriggers();
//        mySQLTable.enableForeignKeyConstraints();
//        mySQLTable.enableUniqueChecks();
//
//    }
}
