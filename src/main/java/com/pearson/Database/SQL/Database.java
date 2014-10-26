package com.pearson.Database.SQL;

import com.pearson.Database.DatabaseInterface;
import com.pearson.Database.DatabaseManager;
import com.pearson.Database.DatabaseSettings;
import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Database.MySQL.MySQLTable;
import com.pearson.Interface.DatabaseConnection;
import com.pearson.Utilities.SQLStatements;
import com.pearson.Utilities.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA. User: UKISERU Date: 6/7/13 Time: 9:25 AM To
 * change this template use File | Settings | File Templates.
 */
public class Database {

    private static Logger logger = LoggerFactory.getLogger(Database.class.getName());

    private String databaseName;
    public TreeMap<String, MySQLTable> tables = new TreeMap<String, MySQLTable>();
    private DatabaseManager databaseManager;
    private DatabaseSettings databaseSettings;

    public Database(DatabaseConnection connection) {
        databaseName = connection.getDefaultSchema();
        connect(connection.getUsername(), connection.getPassword(), connection.getJDBCURL())
    }

    public static boolean isConnectionValid(String defaultSchema, String username, String password, String url) {

        try {
            DatabaseManager databaseManager = new DatabaseManager(username, password, url);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public String getDatabaseName() {
        return databaseName;
    }


    public Database(String schema_name, String username, String password, String JDBCURL) throws SQLException {

        databaseName = schema_name;
        connect(username, password, JDBCURL);
        fillTables();
        databaseSettings = new DatabaseSettings(new DatabaseInterface(databaseManager.getConnection()), this);
    }

    /**
     * Connect creates connection pool to draw connections from
     *
     * @param username username that is used to log in on MySQL server
     * @param password password that is used to log in on MySQL server
     * @param JDBCURL  url that is used to log in on MySQL server
     */
    private void connect(String username, String password, String JDBCURL) throws SQLException {
        databaseManager = new DatabaseManager(username, password, JDBCURL);
    }

    @Override
    public String toString() {
        return tables.toString();
    }

    /**
     * fillTables populates database with information(tables and columns).
     */
    private void fillTables() throws SQLException {
        DatabaseInterface databaseInterface = new DatabaseInterface(databaseManager.getConnection());
        ConnectionConfig config = new ConnectionConfig(databaseInterface);
        config.setDefaultDatabase(this);
        System.out.println("bla3");
        try {
            try (PreparedStatement tablesStmt = databaseInterface.createPreparedStatement(SQLStatements.GET_TABLES)) {
                try (ResultSet tablesResult = tablesStmt.executeQuery()) {
                    while (tablesResult.next()) {
                        add(new MySQLTable(tablesResult.getString("Tables_in_" + databaseName), this.databaseManager));
                    }
                }
            }


            for (MySQLTable mySQLTable : tables.values()) {
                try (PreparedStatement columnsStmt = databaseInterface.createPreparedStatement(SQLStatements.GET_COLUMNS)) {
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
            if (databaseInterface.isConnectionValid()) {
                try {
                    databaseInterface.cleanupAutomatic();
                } catch (SQLException sqle) {
                    logger.error(sqle + System.lineSeparator() + StackTrace.getStringFromStackTrace(sqle));
                }
            }
        }
    }

    public void add(MySQLTable mySQLTable) {
        tables.put(mySQLTable.getTableName(), mySQLTable);
    }

    public MySQLTable getTable(String tableName) throws SQLException {

        if (!tables.containsKey(tableName)) {
            throw new SQLException("Table " + tableName +
                    " does not exist in the database " + databaseName);
        }
        return tables.get(tableName);
    }

    public DatabaseSettings getDatabaseSettings() {

        return databaseSettings;
    }

    public void cleanUp() throws SQLException {

        databaseSettings.cleanUp();
        databaseManager.shutDown();
    }
}
