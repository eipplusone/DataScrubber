package com.pearson.SQL;

import com.pearson.Database.DatabaseInterface;
import com.pearson.Database.DatabaseManager;

import java.util.ArrayList;

/**
 * @author Ruslan Kiselev
 *         Date: 6/19/13
 *         Time: 11:54 AM
 *         Project Name: DataScrubber
 */
public class Table {

    protected DatabaseInterface databaseInterface = null;
    protected boolean isConnectionValid = false;

    public ArrayList<Column> columns = new ArrayList<>();
    protected String tableName;

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void close(){
        databaseInterface.close();
        databaseInterface = null;
    }

    public DatabaseInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public void add(Column column) {
        columns.add(column);
    }


    /**
     * clearResourses should be called in case any of the operations on
     * table were interrupted by an exception to make sure
     * all the connections are closed
     */
    public void cleanResourses(){
        databaseInterface.cleanupAutomatic();
    }
}
