package com.pearson.Database.SQL;

import com.pearson.Database.DatabaseInterface;
import com.pearson.Utilities.SQLStatements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author Ruslan Kiselev
 *         Date: 7/22/13
 *         Time: 11:59 AM
 *         Project Name: DataScrubber
 */
public class DatabaseSession {
    
    private static Logger logger = LoggerFactory.getLogger(DatabaseSession.class.getName());

    final DatabaseInterface databaseInterface;

    public DatabaseSession(Connection connection) {
        databaseInterface = new DatabaseInterface(connection);
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

    public void setDefaultDatabase(String defaultDatabase) throws SQLException {

        if(!databaseInterface.isConnectionValid()){
            throw new SQLException("DatabaseConnection - Connection isn't valid");
        }

        if (defaultDatabase == null) {
            throw new SQLException("Database name can't be null");
        }

        logger.info("Using database " + defaultDatabase);
        databaseInterface.createStatement().executeUpdate("USE " + defaultDatabase);

        databaseInterface.commit();
    }

    public Statement createStatement() throws SQLException {
        return databaseInterface.createStatement();
    }

    public void cleanupAutomatic() throws SQLException {
        databaseInterface.cleanupAutomatic();
    }

    public PreparedStatement createPreparedStatement(String sql) throws SQLException {

        return databaseInterface.createPreparedStatement(sql);

    }

    public ResultSet executePreparedStatement() throws SQLException {

        return databaseInterface.executePreparedStatement();
    }

    public void addPreparedStatementParameters(Object... objects) {

        databaseInterface.addPreparedStatementParameters(objects);
    }

    public void commit() throws SQLException {

        databaseInterface.commit();
    }

    public void rollback() throws SQLException {

        databaseInterface.rollback();
    }

    public boolean isConnectionValid() {
        return databaseInterface.isConnectionValid();
    }

    public void setPreparedStatementParameters() throws SQLException {
        databaseInterface.setPreparedStatementParameters();
    }
}
