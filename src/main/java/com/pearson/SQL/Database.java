package com.pearson.SQL;

import com.pearson.Database.DatabaseManager;
import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Utilities.SQLStatements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA. User: UKISERU Date: 6/7/13 Time: 9:25 AM To
 * change this template use File | Settings | File Templates.
 */
public class Database {

    public static boolean isConnectionValid(String defaultSchema, String username, String password, String url) {
        try {
            DatabaseManager.createConnectionPool(username, password, url + "/" + defaultSchema);
            DatabaseManager.shutDown();
            return true;
        } catch(SQLException ex){
            return false;
        }
    }
    public TreeMap<String, MySQLTable> tables = new TreeMap<String, MySQLTable>();
    private String databaseName;

    public Database(String schema_name, String username, String password, String JDBCURL) throws SQLException {

        databaseName = schema_name;
        connect(username, password, JDBCURL);
        fillTables();

    }

    /**
     * Connect creates connection pool to draw connections from, and populates
     * database with information
     *
     * @param username username that is used to log in on MySQL server
     * @param password password that is used to log in on MySQL server
     * @param JDBCURL url that is used to log in on MySQL server
     */
    private void connect(String username, String password, String JDBCURL) throws SQLException {
        DatabaseManager.createConnectionPool(username, password, JDBCURL + "/" + databaseName);
        assert DatabaseManager.connectionPool != null;
    }

    @Override
    public String toString() {
        return tables.toString();
    }

    /**
     * fillTables populates database with information(tables and columns).
     */
    private void fillTables() {
        Connection conn = DatabaseManager.getConnection();
        try {
            try (PreparedStatement tablesStmt = conn.prepareStatement(SQLStatements.GET_TABLES)) {
                try (ResultSet tablesResult = tablesStmt.executeQuery()) {
                    while (tablesResult.next()) {
                        add(new MySQLTable(tablesResult.getString("Tables_in_" + databaseName)));
                    }
                }
            }


            for (MySQLTable mySQLTable : tables.values()) {
                try (PreparedStatement columnsStmt = conn.prepareStatement(SQLStatements.GET_COLUMNS)) {
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
            System.out.println(sqle.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqle) {
                    System.out.println(sqle.toString());
                }
            }
        }
    }

    public void add(MySQLTable mySQLTable) {
        tables.put(mySQLTable.getTableName(), mySQLTable);
    }

    public MySQLTable getTable(String tableName) {

        if (!tables.containsKey(tableName)) throw new IllegalArgumentException("Table " + tableName + " does not exist in the database " + databaseName);
        return tables.get(tableName);
    }
}
