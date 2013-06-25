package com.pearson.Database;

import com.pearson.SQL.Database;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * com.pearson.Database manager is class that encapsulates giving and closing
 * connections logic. It's main engine is BoneCP connection pool, which asks the
 * database for connection. The connection is then passed to the class that
 * requests it.
 *
 * @author Ruslan Kiselev
 */
public class DatabaseManager {

    public static BoneCP connectionPool = null;
    private static boolean connectionEstablished;

    public static void createConnectionPool(String userName, String password, String JDBCURL) throws SQLException {

        BoneCPConfig config = new BoneCPConfig();
        config.setMaxConnectionsPerPartition(20);
        config.setJdbcUrl(JDBCURL);

        config.setUsername(userName);
        config.setPassword(password);
        config.setDefaultAutoCommit(false);

        connectionPool = new BoneCP(config);
        connectionEstablished = true;
    }

    public static Connection getConnection() {

        Connection connection = null;

        if (!connectionEstablished) {
            return null;
        }

        try {
            connection = connectionPool.getConnection();
        } catch (Exception e) {
            connection = null;
            System.out.println("Error: getConnection - couldn't get connection");
        } finally {
            return connection;
        }
    }

    public static boolean disconnect(Connection connection) {
        try {
            if ((connection != null) && !connection.isClosed()) {

                if (!connection.getAutoCommit()) {
                    connection.commit();
                }

                connection.close();
            }

            return true;
        } catch (Exception e) {
            connection = null;
            System.out.println("Error: disconnect - couldn't close the connection");
            return false;
        }
    }

    public static boolean disconnectAndShutdown(Connection connection) {
        try {
            if ((connection != null) && !connection.isClosed()) {
                connection.close();
            }

            return true;
        } catch (Exception e) {
            connection = null;
            System.out.println("Error: disconnectAndShutDown - couldn't close the connection");
            return false;
        } finally {
            try {
                if (connectionPool != null) {
                    connectionPool.close();
                }
            } catch (Exception e) {
                System.out.println("Error: disconnectAndShutDown - couldn't close the connection pool");
            }

        }
    }

    public static void shutDown() {
        try {
            if (connectionPool != null) {
                connectionPool.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
