package com.pearson.SQL;

import com.pearson.Database.DatabaseInterface;

import java.util.HashMap;

/**
 * @author Ruslan Kiselev
 *         Date: 6/19/13
 *         Time: 11:54 AM
 *         Project Name: DataScrubber
 */
public class Table {

    protected DatabaseInterface databaseInterface = null;
    protected boolean isConnectionValid = false;

    public HashMap<String, Column> columns = new HashMap<>();
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

    public HashMap<String, Column> getColumns() {
        return columns;
    }

    public void setColumns(HashMap<String, Column> columns) {
        this.columns = columns;
    }

    public void add(Column column) {
        columns.put(column.getName(), column);
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
