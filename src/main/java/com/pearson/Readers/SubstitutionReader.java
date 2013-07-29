package com.pearson.Readers;

import com.pearson.SQL.Database;
import com.pearson.SQL.MySQLTable;
import noNamespace.Rule;

import java.sql.SQLException;

/**
 * This is an abstract class for three types of substitution readers(sorted by type). SetToNull,
 * enablsing and disabling constraints are shared by different types of substitutions
 *
 * @author Ruslan Kiselev
 *         Date: 7/18/13
 *         Time: 9:22 AM
 *         Project Name: DataScrubber
 */
public abstract class SubstitutionReader implements Runnable {
    Database database;
    Rule rule;
    MySQLTable mySQLTable;

    public SubstitutionReader(Rule rule, Database database) {
        this.rule = rule;
        this.database = database;

        mySQLTable = database.getTable(rule.getTarget());
    }

    public abstract void run();


    public void setToNull() throws SQLException {

        disableConstraints();
        mySQLTable.setColumnToNull(rule.getSubstitute().getColumn());
        enableConstraints();

    }

    protected void disableConstraints() throws SQLException {

        mySQLTable.getConnectionConfig().disableUniqueChecks();
        mySQLTable.getConnectionConfig().disableForeignKeyConstraints();

    }

    protected void enableConstraints() throws SQLException {

        mySQLTable.getConnectionConfig().enableForeignKeyConstraints();
        mySQLTable.getConnectionConfig().enableUniqueChecks();

    }

}
