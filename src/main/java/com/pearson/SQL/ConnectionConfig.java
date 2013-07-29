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

    public void setDefaultDatabase(Database database) throws SQLException {

        if(!databaseInterface.isConnectionValid()){
            throw new SQLException("ConnectionConfig - Connection isn't valid");
        }

        databaseInterface.createStatement().executeUpdate("USE " + database.getDatabaseName());
    }
}
