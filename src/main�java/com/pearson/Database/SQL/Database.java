package com.pearson.Database.SQL;

import com.pearson.Database.DatabaseManager;
import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Interface.DatabaseConnectionInfo;
import com.pearson.Utilities.SQLStatements;
import com.pearson.Utilities.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA. User: UKISERU Date: 6/7/13 Time: 9:25 AM To
 * change this template use File | Settings | File Templates.
 */
public class Database implements Iterable<MySQLTable>{

    private static Logger logger = LoggerFactory.getLogger(Database.class.getName());

    private String databaseName;
    private TreeMap<String, MySQLTable> tables = new TreeMap<String, MySQLTable>();
    private DatabaseManager databaseManager;

    public Database(DatabaseConnectionInfo connectionInfo) throws SQLException {
        databaseName = connectionInfo.getDefaultSchema();
        databaseManager = new DatabaseManager(connectionInfo);
        fillTables();
    }

    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public String toString() {
        return tables.toString();
    }

    /**
     * fillTables populates database with information(tables and columns).
     */
    private void fillTables() throws SQLException {
        DatabaseSession databaseSession = new DatabaseSession(databaseManager.getConnection("Filling tables for database"));
        databaseSession.setDefaultDatabase(databaseName);
        try {
            try (PreparedStatement tablesStmt = databaseSession.createPreparedStatement(SQLStatements.GET_TABLES)) {
                try (ResultSet tablesResult = tablesStmt.executeQuery()) {
                    while (tablesResult.next()) {
                        add(new MySQLTable(tablesResult.getString("Tables_in_" + databaseName)));
                    }
                }
            }


            for (MySQLTable mySQLTable : tables.values()) {
                try (PreparedStatement columnsStmt = databaseSession.createPreparedStatement(SQLStatements.GET_COLUMNS)) {
                    columnsStmt.setString(1, mySQLTable.getTableName());
                    try (ResultSet columnsResult = columnsStmt.executeQuery()) {
                        while (columnsResult.next()) {
                            Column c = new Column(columnsResult.getString("column_name"));

                            switch (columnsResult.getString("is_nullable")) {
                                case "YES":
                                    c.nullable = true;
                                    break;
                                case "NO":
                                default:
                                    c.nullable = false;
                                    break;
                            }

                            c.type = MySQLDataType.valueOf(columnsResult.getString("data_type").toUpperCase());

                            c.character_max_length = columnsResult.getLong("character_maximum_length");
                            c.numeric_precision = columnsResult.getInt("numeric_precision");
                            c.numeric_scale = columnsResult.getInt("numeric_scale");
                            c.autoIncrement = columnsResult.getString("extra").equals("auto_increment");
                            mySQLTable.columns.put(c.name, c);
                        }
                    }
                }
            }


        } catch (SQLException sqle) {
            logger.error(sqle + System.lineSeparator() + StackTrace.getStringFromStackTrace(sqle));
        } finally {
            if (databaseSession.isConnectionValid()) {
                try {
                    databaseSession.cleanupAutomatic();
                } catch (SQLException sqle) {
                    logger.error(sqle + System.lineSeparator() + StackTrace.getStringFromStackTrace(sqle));
                }
            }
        }
    }

    private void add(MySQLTable mySQLTable) {
        tables.put(mySQLTable.getTableName(), mySQLTable);
    }

    /**
     * Returns MySQLTable object associated with the tableName in the Database object
     * @param tableName - String value of tableName
     * @return MySQLTable object
     * @throws SQLException if the table doesn't exist
     */
    public MySQLTable getTable(String tableName) throws SQLException {
        if (!tables.containsKey(tableName)) {
            throw new SQLException("Table " + tableName +
                    " does not exist in the database " + databaseName);
        }
        return tables.get(tableName);
    }

    public void cleanUp() throws SQLException {

        databaseManager.shutDown();
    }

    public Connection getConnection(String metadata) throws SQLException {
        return databaseManager.getConnection(metadata);
    }

    public int getNumberOfOpenConnections() throws SQLException {
        return databaseManager.getNumberOfOpenConnections();
    }

    public List<String> getConnectionsMetadata(){
        return databaseManager.getConnectionsMetadata();
    }

    public static boolean isConnectionValid(DatabaseConnectionInfo currentConnection) {
        return DatabaseManager.isConnectionValid(currentConnection);
    }

    @Override
    public Iterator<MySQLTable> iterator() {
        return tables.values().iterator();
    }

    public int getTotalOpenedConnections() {
        return databaseManager.getTotalConnectionsOpened();
    }
}
