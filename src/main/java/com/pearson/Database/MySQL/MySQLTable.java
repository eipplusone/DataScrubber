package com.pearson.Database.MySQL;

import com.pearson.Database.DatabaseInterface;
import com.pearson.Database.DatabaseManager;
import com.pearson.Database.SQL.Column;
import com.pearson.Database.SQL.Table;
import com.pearson.Utilities.Query;
import com.pearson.Utilities.SQLStatements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Rulsan Kiselev
 * Date: 6/6/13
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class MySQLTable extends Table {
    private static Logger logger = LoggerFactory.getLogger(MySQLTable.class.getName());

    com.pearson.Database.SQL.ConnectionConfig connectionConfig;
    DatabaseInterface databaseInterface;
    DatabaseManager databaseManager;

    public MySQLTable(String tableName, DatabaseManager databaseManager) {
        super(tableName);
        this.databaseManager = databaseManager;
        connectionConfig = new com.pearson.Database.SQL.ConnectionConfig(databaseInterface);
    }

    /**
     * Returns number of rows in the MySQLTable
     *
     * @return number of rows max in all columns
     */
    public int getNumberOfRows() throws SQLException {
        establishConnection();

        String numberOfRowsQuery = SQLStatements.GET_NUMBER_OF_ROWS + tableName;

        ResultSet results = databaseInterface.createStatement().executeQuery(numberOfRowsQuery);

        results.next();
        return results.getInt(1);

    }

    /**
     * Returns number of rows in a specified column
     *
     * @param columnName column that is used to retrieve number of rows
     * @return number of rows
     * @
     */
    public int getNumberOfRows(String columnName) throws SQLException {
        establishConnection();

        String numberOfRowsQuery = SQLStatements.GET_NUMBER_OF_ROWS_IN_A_COLUMN + tableName;

        databaseInterface.createPreparedStatement(numberOfRowsQuery);
        databaseInterface.addPreparedStatementParameters(columnName);

        ResultSet resultSet = databaseInterface.executePreparedStatement();

        resultSet.next();
        return resultSet.getInt(1);
    }

    /**
     * Sets row in columnName where auto-increment column equals to id to the string representation of the object
     *
     * @param object
     * @param columnName
     * @param id
     */
    public void updateRow(Object object, String columnName, int id) throws SQLException {
        try {
            establishConnection();

            if (getAutoIncrementColumn() == null) throw new NullPointerException("Auto increment column is null");

            Query query = new Query();
            query.update(tableName).set(columnName).where(getAutoIncrementColumn().name);

            databaseInterface.createPreparedStatement(query.toString());
            databaseInterface.addPreparedStatementParameters(object, id);

            databaseInterface.executePreparedStatement();

            databaseInterface.commit();
        } catch (SQLException exc) {
            databaseInterface.rollback();
            throw exc;
        }
    }

    public void setColumnToNull(String columnName) throws SQLException {
        try {
            establishConnection();
            databaseInterface.createStatement().executeUpdate("UPDATE " + tableName + " SET " + columnName + " = NULL");

            databaseInterface.commit();
        } catch (SQLException exc) {
            databaseInterface.rollback();
            throw exc;
        }
    }

    /**
     * Returns a random row constructed from specified columns. Includes information only about the columns and it's autoincrement column
     *
     * @param columnNames - column names to include into the result set of the row
     * @return
     */
    public ResultSet getRandomRow(ArrayList<String> columnNames) throws SQLException {
        establishConnection();

        int numberOfRows = getNumberOfRows();
        String autoIncrementColumn = getAutoIncrementColumn().name;
        int randomNumber = new Random().nextInt(numberOfRows - 1);

        // get a random row
        String query = SQLStatements.appendColumns(columnNames) + ", " + autoIncrementColumn
                + " FROM " + tableName + " LIMIT " + randomNumber + ", 1";

        ResultSet resultSet = databaseInterface.createStatement().executeQuery(query);

        return resultSet;
    }

    /**
     * Swaps two rows(which are result sets that are the results of queries).
     * Must have only one row inside the resultSet.
     *
     * @param row1        first row to be swapped with
     * @param row2        Second row to be swapped with
     * @param columnNames columns inside the rows to be updated
     * @
     */
    public void swap(ResultSet row1, ResultSet row2, ArrayList<String> columnNames) throws SQLException {
        try {
            establishConnection();

            String query = null;
            String idColumn = getAutoIncrementColumn().name;

            if (idColumn == null)
                throw new SQLException("MySQLTable " + tableName + " does not have auto-increment column;" +
                        " please create an auto-increment column");

            // main logic for swap
            for (String columnName : columnNames) {
                // move the cursor of the result set onto the next line

                if (row1.next() && row2.next()) {
                    //query = "UPDATE " + tableName + " SET " + columnName + " = " + "?" + " WHERE " + idColumn + " = " + "?";
                    Object row1Object = row1.getObject(columnName);
                    Object row2Object = row2.getObject(columnName);
                    Query queryObject = new Query();
                    queryObject.update(tableName).set(columnName).where(idColumn);
                    databaseInterface.createPreparedStatement(queryObject.toString());
                    databaseInterface.addPreparedStatementParameters(row1Object, row2.getInt(idColumn));
                    databaseInterface.executePreparedStatement();

                    databaseInterface.createPreparedStatement(queryObject.toString());
                    databaseInterface.addPreparedStatementParameters(row2Object, row1.getInt(idColumn));
                    databaseInterface.executePreparedStatement();
                }
            }
            databaseInterface.commit();
        } catch (SQLException exc) {
            databaseInterface.rollback();
            throw exc;
        }

    }

    /**
     * Adds an auto-increment column to the table, start from 1
     *
     * @return returns the auto-increment column
     */

    public Column addAutoIncrementColumn() throws SQLException {
        Column autoIncrementColumn;

        try {
            establishConnection();

            int numberOfRows = getNumberOfRows();
            String createColumnQuery = "ALTER TABLE " + tableName + " add column datascrubber_rowid INT NOT NULL AUTO_INCREMENT UNIQUE";

            databaseInterface.createStatement().executeUpdate(createColumnQuery);

            // end creating index column

            // begin adding to app database
            autoIncrementColumn = new Column("datascrubber_rowid");
            autoIncrementColumn.nullable = false;
            autoIncrementColumn.autoIncrement = true;
            columns.put(autoIncrementColumn.name, autoIncrementColumn);

            databaseInterface.commit();
        } catch (SQLException exc) {
            databaseInterface.rollback();
            throw exc;
        }

        return autoIncrementColumn;
    }

    /**
     * Deletes autoIncrementColumn if there is one
     *
     * @throws Exception
     */
    public void deleteAutoIncrementColumn() throws SQLException {
        try {
            establishConnection();
            databaseInterface.createStatement().executeUpdate("ALTER TABLE " + tableName + " DROP " + getAutoIncrementColumn().name);
            databaseInterface.commit();
        } catch (SQLException exc) {
            databaseInterface.rollback();
            throw exc;
        }
    }

    /**
     * Get auto increment column if there is one. If there is no auto increment column,
     * return null
     *
     * @return auto-increment column
     */
    public Column getAutoIncrementColumn() {
        Iterator<Column> it = columns.values().iterator();
        while (it.hasNext()) {
            Column column = it.next();
            if (column.autoIncrement) {
                return column;
            }
        }
        return null;
    }

    /**
     * @param columnName
     * @param value
     * @throws SQLException
     */
    public void setColumnToValue(String columnName, Object value) throws SQLException {
        try {
            establishConnection();

            Query query = new Query();
            query = query.update(tableName).set(columnName);

            databaseInterface.createPreparedStatement(query.toString());
            databaseInterface.addPreparedStatementParameters(value);
            databaseInterface.executePreparedStatement();

            databaseInterface.commit();
        } catch (SQLException exc) {
            databaseInterface.rollback();
            throw exc;
        }

    }

    /**
     * cleanResourses should be called in case any of the operations on
     * table were interrupted by an exception to make sure
     * all the connections are closed
     * <p/>
     * cleanResources must be called outside of this class
     */
    public void cleanResourses() throws SQLException {

        databaseInterface.cleanupAutomatic();
    }

    /**
     * @return
     * @throws SQLException
     */
    public com.pearson.Database.SQL.ConnectionConfig getConnectionConfig() throws SQLException {
        // make sure this table connection to the database
        establishConnection();
        // make sure that both classes work on the same connection
        // todo : make sure that you don't have to do anything before using the object except instaniating it!
        connectionConfig.setDatabaseInterface(databaseInterface);
        return connectionConfig;
    }

    /**
     * Warning: the reason why we have establishConnection at every method is because if any of the methods inside loop
     * they will trigger getConnection() every turn. This way we don't idle a connection on creation of the object
     * and allow to reuse one connection for the entire mySQL table purposes
     */
    // todo test if this method can still be valid after cleanupAutomatic
    private void establishConnection() throws SQLException {

        if (databaseInterface == null || !databaseInterface.isConnectionValid()) {
            databaseInterface = new DatabaseInterface(databaseManager.getConnection());
        }
        assert databaseInterface.isConnectionValid();
    }

}
