package com.pearson.Rules;

import com.pearson.SQL.Column;
import com.pearson.SQL.MySQLTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: UKISERU
 * Date: 6/7/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Shuffle extends Rule {



    ArrayList<String> columnNames;

    public Shuffle(ArrayList<String> columnNames, MySQLTable mySQLTable) {
        super(mySQLTable);
        this.columnNames = columnNames;
    }

    /**
     * Shuffles the entire column
     *
     * @throws SQLException
     */
    public void run() throws SQLException {


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
            ResultSet randomRow1 = mySQLTable.getRandomRow(columnNames);
            ResultSet randomRow2 = mySQLTable.getRandomRow(columnNames);
            mySQLTable.swap(randomRow1, randomRow2, columnNames);
        }
        // get the result set that has already been shuffled

        mySQLTable.enableForeignKeyConstraints();
        mySQLTable.enableUniqueChecks();

        // clean all the resources
        mySQLTable.cleanResourses();

    }
}
