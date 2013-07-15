package com.pearson.SQL;

import com.pearson.Database.DatabaseManager;
import com.pearson.Utilities.Query;
import com.pearson.Utilities.SQLStatements;

import java.sql.Connection;
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

    public MySQLTable(String tableName) {
        super(tableName);
    }

    /**
     * Returns number of rows in the MySQLTable
     *
     * @return number of rows max in all columns
     */
    public int getNumberOfRows() throws SQLException {

        String numberOfRowsQuery = SQLStatements.GET_NUMBER_OF_ROWS + tableName;

        if (!isConnectionValid) {
            return -1;
        }


        ResultSet results = databaseInterface.createStatement().executeQuery(numberOfRowsQuery);

        if (databaseInterface.isResultSetValid()) {
            results.next();
            return results.getInt(1);
        } else return -1;

    }

    /**
     * Returns number of rows in a specified column
     *
     * @param columnName column that is used to retrieve number of rows
     * @return number of rows
     * @
     */
    public int getNumberOfRows(String columnName) throws SQLException {

        String numberOfRowsQuery = SQLStatements.GET_NUMBER_OF_ROWS_IN_A_COLUMN + tableName;

        if (isConnectionValid) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createPreparedStatement(numberOfRowsQuery);
        databaseInterface.addPreparedStatementParameters(columnName);

        ResultSet resultSet = databaseInterface.executePreparedStatement();

        if (databaseInterface.isResultSetValid()) {
            resultSet.next();
            return resultSet.getInt(1);
        } else return -1;

    }

    // disable different keys checks

    public void disableSafeUpdate() throws SQLException {

        if (isConnectionValid) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createStatement().executeUpdate("SET SQL_SAFE_UPDATES=0; ");

        databaseInterface.commit();
    }

    public void enableSafeUpdate() throws SQLException {

        if (isConnectionValid) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createStatement().executeUpdate("SET SQL_SAFE_UPDATES=1; ");

        databaseInterface.commit();
    }

    public void disableForeignKeyConstraints() throws SQLException {

        if (isConnectionValid) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createStatement().executeUpdate("SET foreign_key_checks = 0");

        databaseInterface.commit();

    }

    public void setCharacterSetClient(String characterSet) throws SQLException {

        if (isConnectionValid) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createPreparedStatement(SQLStatements.SET_CHARACTER_SET_CLIENT);
        databaseInterface.addPreparedStatementParameters(characterSet);
        databaseInterface.executePreparedStatement();

        databaseInterface.commit();

    }

    public void setSessionProperties(String properties) throws SQLException {

        if (isConnectionValid) {
            throw new SQLException("Connection is not valid");
        }

        String sql = "SET SESSION SQL_MODE = ?";
        databaseInterface.createPreparedStatement(SQLStatements.SET_CHARACTER_SET_CLIENT);
        databaseInterface.addPreparedStatementParameters(sql);
        databaseInterface.executePreparedStatement();

        databaseInterface.commit();

    }

    public void copyTable(String tableToCopyFrom, String newTable) throws SQLException {

        if (isConnectionValid) {
            throw new SQLException("Connection is not valid");
        }

        String query = "create table " + newTable + " LIKE " + tableToCopyFrom;

        databaseInterface.createStatement().executeUpdate(query);

        query = "insert " + newTable + " SELECT * " + " FROM " + tableToCopyFrom;

        databaseInterface.createStatement().executeUpdate(query);

        databaseInterface.commit();

    }

    private void deleteTriggers() throws SQLException {

        if (isConnectionValid) {
            throw new SQLException("Connection is not valid");
        }

        ResultSet resultSet = getTriggers();

        while (resultSet.next()) {
            databaseInterface.createStatement().executeUpdate("DROP TRIGGER " + resultSet.getString("TRIGGER_NAME"));
        }

        databaseInterface.commit();

    }

    public ResultSet getTriggers() throws SQLException {

        String query = "SELECT * FROM " + "information_schema.triggers";

        return databaseInterface.createStatement().executeQuery(query);
    }

    public void disableTriggers() throws SQLException {

        copyTable("information_schema.triggers", "datascrubber_triggers_copy");
        deleteTriggers();

    }

    public void enableTriggers() throws SQLException {

        String query = "SELECT * FROM " + "datascrubber_triggers_copy";

        ResultSet resultSet = databaseInterface.createStatement().executeQuery(query);

        while (resultSet.next()) createTrigger(resultSet);

        databaseInterface.createStatement().executeUpdate("DROP TABLE datascrubber_triggers_copy");

        databaseInterface.commit();

    }

    /**
     * method that creates a trigger. The result set must include all of the triggers for the
     * database.
     *
     * @param resultSet
     */
    private void createTrigger(ResultSet resultSet) throws SQLException {

        Connection connection = DatabaseManager.getConnection();

//        String sessionProperties = resultSet.getString("SQL_MODE");
//
//        setSessionProperties(sessionProperties);
//
//        String character_set = "utf8";
//
//        setCharacterSetClient(character_set);

        String createTriggerQuery = "CREATE TRIGGER " + resultSet.getString("trigger_name") +
                " " + resultSet.getString("action_timing") + " " + resultSet.getString("event_manipulation") +
                " ON " + resultSet.getString("event_object_table") + " FOR EACH ROW " +
                resultSet.getString("action_statement");

        databaseInterface.createStatement().executeUpdate(createTriggerQuery);

        databaseInterface.commit();

    }

    public ResultSet getColumnContents(String columnName) throws SQLException {

        ResultSet resultSet = databaseInterface.createStatement().executeQuery("SELECT " + columnName + " FROM " + tableName);

        return resultSet;
    }

    /**
     * Sets row in columnName where auto-increment column equals to id to the string representation of the object
     *
     * @param object
     * @param columnName
     * @param id
     */
    public void updateRow(Object object, String columnName, int id) {

        Query query = new Query();
        query.update(tableName).set(columnName).where(getAutoIncrementColumn().name);

        databaseInterface.createPreparedStatement(query.toString());
        databaseInterface.addPreparedStatementParameters(object, id);

        databaseInterface.executePreparedStatement();

        databaseInterface.commit();
    }

    public void enableForeignKeyConstraints() throws SQLException {

        databaseInterface.createStatement().executeUpdate("SET foreign_key_checks = 1");

        databaseInterface.commit();

    }

    public void disableUniqueChecks() throws SQLException {

        databaseInterface.createStatement().executeUpdate("SET unique_checks = 0");

        databaseInterface.commit();

    }

    public void enableUniqueChecks() throws SQLException {

        databaseInterface.createStatement().executeUpdate("SET unique_checks = 1");

        databaseInterface.commit();
    }

    public void disablePrimaryKey(String primaryKeyColumn) throws SQLException {

        databaseInterface.createStatement().executeUpdate("ALTER TABLE " + tableName + " DROP PRIMARY KEY");

        databaseInterface.commit();

    }

    public void enablePrimaryKey(String primaryKeyColumn) throws SQLException {

        databaseInterface.createStatement().executeUpdate("ALTER TABLE " + tableName + " ADD PRIMARY KEY");

        databaseInterface.commit();
    }

    public void setColumnToNull(String columnName) throws SQLException {

        databaseInterface.createStatement().executeUpdate("UPDATE " + tableName + " SET " + columnName + " = NULL");

        databaseInterface.commit();

    }

    /**
     * Returns a random row constructed from specified columns. Includes information only about the columns and it's autoincrement column
     *
     * @param columnNames - column names to include into the result set of the row
     * @return
     */
    public ResultSet getRandomRow(ArrayList<String> columnNames) throws SQLException {

        int numberOfRows = getNumberOfRows();

        String autoIncrementColumn = addAutoIncrementColumn().name;

        int randomNumber = new Random().nextInt(numberOfRows - 1);

        Connection connection = DatabaseManager.getConnection();

        // get a random row
        String query = SQLStatements.appendColumns(columnNames) + ", " + autoIncrementColumn
                + " FROM " + tableName + " LIMIT " + randomNumber + ", 1";

        ResultSet resultSet = connection.createStatement().executeQuery(query);

        connection.close();

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


        Connection connection = DatabaseManager.getConnection();
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
                Query queryObject = new Query();
                queryObject.update(tableName).set(columnName).where(idColumn);

                databaseInterface.createPreparedStatement(queryObject.toString());
                databaseInterface.addPreparedStatementParameters(row1.getObject(columnName), row2.getObject(idColumn));
                databaseInterface.executePreparedStatement();

                databaseInterface.createPreparedStatement(queryObject.toString());
                databaseInterface.addPreparedStatementParameters(row2.getObject(columnName), row1.getInt(idColumn));
                databaseInterface.executePreparedStatement();
            }
        }


        databaseInterface.commit();
    }

    /**
     * Adds an auto-increment column to the table, start from 1
     *
     * @return returns the auto-increment column
     */

    public Column addAutoIncrementColumn() throws SQLException {

        int numberOfRows = getNumberOfRows();

        String createColumnQuery = "ALTER TABLE " + tableName + " add column datascrubber_rowid INT NOT NULL";

        databaseInterface.createStatement().executeUpdate(createColumnQuery);

        // end creating index column

        // populate the index column
        for (int i = 0; i < numberOfRows; i++) {

            String updateIndexColumnQuery = "INSERT INTO " + tableName + " VALUES (" + i + ")";
            databaseInterface.createStatement().executeUpdate(updateIndexColumnQuery);
        }
        // end updating the database

        // begin adding to app database
        Column autoIncrementColumn = new Column("datascrubber_rowid");
        autoIncrementColumn.nullable = false;
        columns.put(autoIncrementColumn.name, autoIncrementColumn);

        databaseInterface.commit();

        return autoIncrementColumn;
    }

    /**
     * Deletes autoIncrementColumn if there is one
     *
     * @throws Exception
     */
    public void deleteAutoIncrementColumn() throws Exception {

        databaseInterface.createStatement().executeUpdate("ALTER TABLE " + tableName + " DROP " + getAutoIncrementColumn().name);
        databaseInterface.commit();
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

    public void setColumnToValue(String columnName, Object value) {

        Query query = new Query();
        query = query.update(tableName).set(columnName);

        databaseInterface.createPreparedStatement(query.toString());
        databaseInterface.addPreparedStatementParameters(value);
        databaseInterface.executePreparedStatement();

        databaseInterface.commit();

    }

}
