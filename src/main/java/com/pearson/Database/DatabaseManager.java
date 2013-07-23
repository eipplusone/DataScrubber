package com.pearson.Database;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.SQLException;

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
            throw new NullPointerException("DatabaseManager - connection pool has not been initialised");
        }

        try {
            connection = connectionPool.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
    }

    public static void shutDown() {
        try {
            if (connectionPool != null) {
                connectionPool.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
