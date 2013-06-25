package com.pearson.SQL;

import com.pearson.Database.DatabaseManager;
import com.pearson.Utilities.SQLStatements;
import com.pearson.Database.MySQL.MySQLDataType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    private String name;

    public Database(String schema_name, String username, String password, String JDBCURL) throws SQLException {

        name = schema_name;
        connect(username, password, JDBCURL);

    }

    /**
     * Connect creates connection pool to draw connections from, and populates
     * database with information
     *
     * @param username username that is used to log in on MySQL server
     * @param password password that is used to log in on MySQL server
     * @param JDBCURL url that is used to log in on MySQL server
     */
    public void connect(String username, String password, String JDBCURL) throws SQLException {
        DatabaseManager.createConnectionPool(username, password, JDBCURL + "/" + name);
        assert DatabaseManager.connectionPool != null;
    }

    @Override
    public String toString() {
        return tables.toString();
    }

    /**
     * fillTables populates database with information(tables and columns).
     */
    public void fillTables() {
        Connection conn = DatabaseManager.getConnection();
        try {
            try (PreparedStatement tablesStmt = conn.prepareStatement(SQLStatements.GET_TABLES)) {
                try (ResultSet tablesResult = tablesStmt.executeQuery()) {
                    while (tablesResult.next()) {

                        add(new MySQLTable(tablesResult.getString("Tables_in_" + name)));
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
                            mySQLTable.columns.add(c);
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
        return tables.get(tableName);
    }

    public void getMetaData() {
        Connection connection = DatabaseManager.getConnection();
    }
}
