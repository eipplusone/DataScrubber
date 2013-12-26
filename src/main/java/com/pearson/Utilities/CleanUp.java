package com.pearson.Utilities;

import com.pearson.Database.DatabaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for debugging purposes; fixes messed up triggers in the database from datascrubber_triggers_copy
 *
 * If you are to use that class, make sure that you have a copy of all your triggers inside datascrubber_triggers_copy
 *
 * @author Ruslan Kiselev
 *         Date: 7/22/13
 *         Time: 4:07 PM
 *         Project Name: DataScrubber
 */
public class CleanUp {

    public static DatabaseInterface databaseInterface = null;
    public static Logger logger = LoggerFactory.getLogger(CleanUp.class.getName());

    public static void main(String[] args) {


//        try {
//            databaseManager.createConnectionPool("dbadmin", "Pw123", "jdbc:mysql://10.25.98.121:3306/core");
//        } catch (SQLException e) {
//            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e))();
//        }

        //if there are any triggers left inside trigger table delete them
//        try {
//            deleteTriggers();
//        } catch (SQLException e) {
//            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e))();
//        }

        try {
            enableTriggers();
        } catch (SQLException e) {
            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
        }

//        databaseInterface.cleanupAutomatic();

        System.out.println("Triggers have been fixed");
    }

    private static void copyTable(String tableToCopyFrom, String newTable) throws SQLException {

        establishConnection();

        if (!databaseInterface.isConnectionValid()) {
            throw new SQLException("Connection is not valid");
        }

        String query = "create table " + newTable + " LIKE " + tableToCopyFrom;

        databaseInterface.createStatement().executeUpdate(query);

        query = "insert " + newTable + " SELECT * " + " FROM " + tableToCopyFrom;

        databaseInterface.createStatement().executeUpdate(query);

        databaseInterface.commit();

    }

    private static void deleteTriggers() throws SQLException {

        establishConnection();

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

        establishConnection();

        String query = "SELECT * FROM " + "information_schema.triggers";

        return databaseInterface.createStatement().executeQuery(query);
    }

    public static void disableTriggers() throws SQLException {

        copyTable("information_schema.triggers", "datascrubber_triggers_copy");
        deleteTriggers();

    }

    public static void enableTriggers() throws SQLException {

        establishConnection();

        String query = "SELECT * FROM " + "reserve_triggers_copy";

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

        establishConnection();

        String createTriggerQuery = "CREATE TRIGGER " + resultSet.getString("trigger_name") +
                " " + resultSet.getString("action_timing") + " " + resultSet.getString("event_manipulation") +
                " ON " + resultSet.getString("event_object_table") + " FOR EACH ROW " +
                resultSet.getString("action_statement");

        databaseInterface.createStatement().executeUpdate(createTriggerQuery);

        databaseInterface.commit();

    }


    private static void establishConnection() {

//        if(databaseInterface == null || !databaseInterface.isConnectionValid()){
//            databaseInterface = new DatabaseInterface(databaseManager.getConnection());
//        }
    }

    public static void fixTriggers() {
//
//        if(DatabaseSettings.isTriggersExist()){
//            deleteTriggers();
//        }
//
    }

    public static void deleteRowId() throws SQLException {
        // get all tables where column_name = "datascrubber_rowid"

        establishConnection();

        String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA WHERE COLUMN_NAME = ?";
        databaseInterface.createPreparedStatement(query);
        databaseInterface.addPreparedStatementParameters("datascrubber_rowid");
        ResultSet resultSet = databaseInterface.executePreparedStatement();

        while(resultSet.next()){
            query = "ALTER TABLE " + resultSet.getString("TABLE_NAME") + " DELETE datascrubber_rowid";
        }

    }
}
