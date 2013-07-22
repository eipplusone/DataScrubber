package com.pearson.SQL;

import com.pearson.Database.DatabaseInterface;
import com.pearson.Database.DatabaseManager;
import com.pearson.Utilities.SQLStatements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ruslan Kiselev
 *         Date: 7/22/13
 *         Time: 11:59 AM
 *         Project Name: DataScrubber
 */
public class ConnectionConfig {

    DatabaseInterface databaseInterface;

    public ConnectionConfig(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    // disable different keys checks

    public void disableSafeUpdate() throws SQLException {

        
        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createStatement().executeUpdate("SET SQL_SAFE_UPDATES=0; ");

        databaseInterface.commit();
    }

    public void enableSafeUpdate() throws SQLException {

        
        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createStatement().executeUpdate("SET SQL_SAFE_UPDATES=1; ");

        databaseInterface.commit();
    }

    public void disableForeignKeyConstraints() throws SQLException {

        
        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createStatement().executeUpdate("SET foreign_key_checks = 0");

        databaseInterface.commit();

    }

    public void enableForeignKeyConstraints() throws SQLException {


        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createStatement().executeUpdate("SET foreign_key_checks = 1");

        databaseInterface.commit();

    }

    public void setCharacterSetClient(String characterSet) throws SQLException {

        
        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createPreparedStatement(SQLStatements.SET_CHARACTER_SET_CLIENT);
        databaseInterface.addPreparedStatementParameters(characterSet);
        databaseInterface.executePreparedStatement();

        databaseInterface.commit();

    }

    public void setSessionProperties(String properties) throws SQLException {

        
        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        String sql = "SET SESSION SQL_MODE = ?";
        databaseInterface.createPreparedStatement(SQLStatements.SET_CHARACTER_SET_CLIENT);
        databaseInterface.addPreparedStatementParameters(sql);
        databaseInterface.executePreparedStatement();

        databaseInterface.commit();

    }

    private void copyTable(String tableToCopyFrom, String newTable) throws SQLException {

        
        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        String query = "create table " + newTable + " LIKE " + tableToCopyFrom;

        databaseInterface.createStatement().executeUpdate(query);

        query = "insert " + newTable + " SELECT * " + " FROM " + tableToCopyFrom;

        databaseInterface.createStatement().executeUpdate(query);

        databaseInterface.commit();

    }

    private void deleteTriggers() throws SQLException {

        
        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        ResultSet resultSet = getTriggers();

        while (resultSet.next()) {
            databaseInterface.createStatement().executeUpdate("DROP TRIGGER " + resultSet.getString("TRIGGER_NAME"));
        }

        databaseInterface.commit();

    }

    private ResultSet getTriggers() throws SQLException {


        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        String query = "SELECT * FROM " + "information_schema.triggers";

        return databaseInterface.createStatement().executeQuery(query);
    }

    public void disableTriggers() throws SQLException {

        copyTable("information_schema.triggers", "datascrubber_triggers_copy");
        deleteTriggers();

    }

    public void enableTriggers() throws SQLException {

        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

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

// creating triggers result in modifying the trigger table settting: todo version 2.0 set sql_mode and character_set when changing triggers
//        String sessionProperties = resultSet.getString("SQL_MODE");
//
//        setSessionProperties(sessionProperties);
//
//        String character_set = "utf8";
//
//        setCharacterSetClient(character_set);

        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        String createTriggerQuery = "CREATE TRIGGER " + resultSet.getString("trigger_name") +
                " " + resultSet.getString("action_timing") + " " + resultSet.getString("event_manipulation") +
                " ON " + resultSet.getString("event_object_table") + " FOR EACH ROW " +
                resultSet.getString("action_statement");

        databaseInterface.createStatement().executeUpdate(createTriggerQuery);

        databaseInterface.commit();

    }

    public void disableUniqueChecks() throws SQLException {


        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createStatement().executeUpdate("SET unique_checks = 0");

        databaseInterface.commit();

    }

    public void enableUniqueChecks() throws SQLException {


        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        databaseInterface.createStatement().executeUpdate("SET unique_checks = 1");

        databaseInterface.commit();
    }

    public void setDatabaseInterface(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }
}
