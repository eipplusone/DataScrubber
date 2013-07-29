package com.pearson.Database;

import com.pearson.SQL.Database;
import com.pearson.Utilities.Constants;
import com.pearson.Utilities.Query;
import com.pearson.Utilities.SQLStatements;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ruslan Kiselev
 *         Date: 7/24/13
 *         Time: 10:41 AM
 *         Project Name: DataScrubber
 */
public class DatabaseSettings {
    private static DatabaseInterface databaseInterface;

    private static void copyTable(String tableToCopyFrom, String newTable) throws SQLException {


        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        String query = "SHOW TABLES LIKE \'" + newTable + "\'";

        databaseInterface.createPreparedStatement(query);
        ResultSet resultSet = databaseInterface.executePreparedStatement();

        if(!resultSet.isBeforeFirst()){
            query = "create table " + newTable + " LIKE " + tableToCopyFrom;

            databaseInterface.createStatement().executeUpdate(query);
        }

        query = "insert " + newTable + " SELECT * " + " FROM " + tableToCopyFrom;

        databaseInterface.createStatement().executeUpdate(query);

        databaseInterface.commit();

    }

    private static void deleteTriggers() throws SQLException {


        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        ResultSet resultSet = getTriggers();

        while (resultSet.next()) {
            databaseInterface.createStatement().executeUpdate("DROP TRIGGER " + resultSet.getString("TRIGGER_NAME"));
        }

        databaseInterface.commit();

    }

    private static ResultSet getTriggers() throws SQLException {


        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        String query = "SELECT * FROM " + "information_schema.triggers";

        return databaseInterface.createStatement().executeQuery(query);
    }

    public static void disableTriggers() throws SQLException {

        copyTable("information_schema.triggers", "datascrubber_triggers_copy");
        deleteTriggers();

    }

    public static void enableTriggers() throws SQLException {

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
    private static void createTrigger(ResultSet resultSet) throws SQLException {

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

    public static void setDatabaseInterface(DatabaseInterface databaseInterface_){
        databaseInterface = databaseInterface_;
    }

    public static void cleanUp() {
        databaseInterface.cleanupAutomatic();
    }

    public static boolean isTriggersExist() throws SQLException {

        ResultSet resultSet = getTriggers();

        if(resultSet.isBeforeFirst()) return false;
        else return true;
    }
}
