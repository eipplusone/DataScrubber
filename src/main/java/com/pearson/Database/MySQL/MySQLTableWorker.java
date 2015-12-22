package com.pearson.Database.MySQL;

import com.pearson.Database.SQL.Column;
import com.pearson.Database.SQL.DatabaseSession;
import com.pearson.Database.SQL.MySQLTable;
import com.pearson.Utilities.Query;
import com.pearson.Utilities.SQLStatements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Rulsan Kiselev
 *
 * May the force be with you.
 */
public class MySQLTableWorker {
    private static Logger logger = LoggerFactory.getLogger(MySQLTableWorker.class.getName());
    MySQLTable mySQLTable;
    DatabaseSession databaseSession;

    public MySQLTableWorker(MySQLTable mySQLTable, Connection connection, String databaseName) throws SQLException {
        this.mySQLTable = mySQLTable;
        databaseSession = new DatabaseSession(connection);

        databaseSession.setDefaultDatabase(databaseName);
        databaseSession.disableForeignKeyConstraints();
        databaseSession.disableUniqueChecks();
    }

    /**
     * Returns number of rows in the MySQLTable
     *
     * @return number of rows max in all columns
     */
    public int getNumberOfRows() throws SQLException {

        String numberOfRowsQuery = SQLStatements.GET_NUMBER_OF_ROWS + mySQLTable.getTableName();

        ResultSet results = databaseSession.createStatement().executeQuery(numberOfRowsQuery);

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

        String numberOfRowsQuery = SQLStatements.GET_NUMBER_OF_ROWS_IN_A_COLUMN + mySQLTable.getTableName();

        databaseSession.createPreparedStatement(numberOfRowsQuery);
        databaseSession.addPreparedStatementParameters(columnName);

        ResultSet resultSet = databaseSession.executePreparedStatement();

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

            if (getAutoIncrementColumn() == null) throw new SQLException("Auto increment column is null");

            Query query = new Query();
            query.update(mySQLTable.getTableName()).set(columnName).where(getAutoIncrementColumn().name);

            databaseSession.createPreparedStatement(query.toString());
            databaseSession.addPreparedStatementParameters(object, id);

            databaseSession.executePreparedStatement();

            databaseSession.commit();
        } catch (SQLException exc) {
            databaseSession.rollback();
            throw exc;
        }
    }

    public void setColumnToNull(String columnName) throws SQLException {
        try {
            databaseSession.createStatement().executeUpdate("UPDATE " + mySQLTable.getTableName() + " SET " + columnName + " = NULL");

            databaseSession.commit();
        } catch (SQLException exc) {
            databaseSession.rollback();
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

        int numberOfRows = getNumberOfRows();
        String autoIncrementColumn = getAutoIncrementColumn().name;
        int randomNumber = new Random().nextInt(numberOfRows - 1);

        // get a random row
        String query = SQLStatements.appendColumns(columnNames) + ", " + autoIncrementColumn
                + " FROM " + mySQLTable.getTableName() + " LIMIT " + randomNumber + ", 1";

        ResultSet resultSet = databaseSession.createStatement().executeQuery(query);

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

            String query = null;
            String idColumn = getAutoIncrementColumn().name;

            if (idColumn == null)
                throw new SQLException("MySQLTable " + mySQLTable.getTableName() + " does not have auto-increment column;" +
                        " please create an auto-increment column");

            // main logic for swap
            for (String columnName : columnNames) {
                // move the cursor of the result set onto the next line

                if (row1.next() && row2.next()) {
                    //query = "UPDATE " + tableName + " SET " + columnName + " = " + "?" + " WHERE " + idColumn + " = " + "?";
                    Object row1Object = row1.getObject(columnName);
                    Object row2Object = row2.getObject(columnName);
                    Query queryObject = new Query();
                    queryObject.update(mySQLTable.getTableName()).set(columnName).where(idColumn);
                    databaseSession.createPreparedStatement(queryObject.toString());
                    databaseSession.addPreparedStatementParameters(row1Object, row2.getInt(idColumn));
                    databaseSession.executePreparedStatement();

                    databaseSession.createPreparedStatement(queryObject.toString());
                    databaseSession.addPreparedStatementParameters(row2Object, row1.getInt(idColumn));
                    databaseSession.executePreparedStatement();
                }
            }
            databaseSession.commit();
        } catch (SQLException exc) {
            databaseSession.rollback();
            throw exc;
        }

    }

    /** don't fix what ain't broke... Isn't that kind of contradictive with notion of refactoring?

    /**
     * Adds an auto-increment column to the table, start from 1
     *
     * @return returns the auto-increment column
     */

    public Column addAutoIncrementColumn() throws SQLException {
        Column autoIncrementColumn;

        try {

            String createColumnQuery = "ALTER TABLE " + mySQLTable.getTableName() + " add column datascrubber_rowid INT NOT NULL AUTO_INCREMENT UNIQUE";

            databaseSession.createStatement().executeUpdate(createColumnQuery);

            // end creating index column

            // begin adding to app database
            autoIncrementColumn = new Column("datascrubber_rowid");
            autoIncrementColumn.nullable = false;
            autoIncrementColumn.autoIncrement = true;
            mySQLTable.columns.put(autoIncrementColumn.name, autoIncrementColumn);

            databaseSession.commit();
        } catch (SQLException exc) {
            databaseSession.rollback();
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
            String sql = "ALTER TABLE " + mySQLTable.getTableName() + " DROP " + getAutoIncrementColumn().name;
            databaseSession.createStatement().executeUpdate(sql);
            databaseSession.commit();
        } catch (SQLException exc) {
            databaseSession.rollback();
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
        Iterator<Column> it = mySQLTable.columns.values().iterator();
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

            Query query = new Query();
            query = query.update(mySQLTable.getTableName()).set(columnName);

            databaseSession.createPreparedStatement(query.toString());
            databaseSession.addPreparedStatementParameters(value);
            databaseSession.executePreparedStatement();

            databaseSession.commit();
        } catch (SQLException exc) {
            databaseSession.rollback();
            throw exc;
        }

    }

    public MySQLTable getMySQLTable() {
        return mySQLTable;
    }

    public void cleanupAutomatic() throws SQLException {
        // Since we don't always commit, certain queries keep a lock on the table
        // I make an assumption that if we are done, we want to finalize the commit.
        // Usually this doesn't mean that anything changes
        // for example if I call getNumberOfRows in a loop, it gets the number of rows
        // but still has metadatalock(since loop statement is checked last after a loop)
        // TODO: Write a better explanation
        databaseSession.commit();
        databaseSession.cleanupAutomatic();
    }
}
