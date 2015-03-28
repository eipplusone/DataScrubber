package com.pearson.Database;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.pearson.Interface.DatabaseConnectionInfo;
import com.pearson.Utilities.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * com.pearson.Database manager is class that encapsulates giving and closing
 * connections logic. It's main engine is BoneCP connection pool, which asks the
 * database for connection. The connection is then passed to the class that
 * requests it.
 *
 * @author Ruslan Kiselev
 */
public class DatabaseManager {

    private static Logger logger = LoggerFactory.getLogger(DatabaseManager.class.getName());

    private Map<Connection, String> openedConnections;
    public BoneCP connectionPool;
    int counterConnections = 0;

    public DatabaseManager(DatabaseConnectionInfo connectionInfo) throws SQLException {
        openedConnections = Collections.synchronizedMap(new HashMap<Connection, String>());
        createConnectionPool(connectionInfo);
    }

    public void createConnectionPool(DatabaseConnectionInfo connectionInfo) throws SQLException {
        BoneCPConfig config = new BoneCPConfig();
        config.setMaxConnectionsPerPartition(20);
        config.setJdbcUrl(connectionInfo.getJDBCURL());
        config.setUsername(connectionInfo.getUsername());
        config.setPassword(connectionInfo.getPassword());

        config.setDefaultAutoCommit(false);

        connectionPool = new BoneCP(config);
    }

    public int getTotalConnectionsOpened() {
        return counterConnections;
    }

    public Connection getConnection(String metadata) throws SQLException {
        Connection returnConnection = connectionPool.getConnection();
        openedConnections.put(returnConnection, metadata);
        counterConnections++;
        logger.debug("Opened a new connection");
        return returnConnection;
    }

    public void shutDown() throws SQLException {
        int closedConnections = closeAllConnections();
        logger.debug("Closed " + closedConnections + " open connections" +
                "on connection pool shutdown");
        connectionPool.shutdown();
        logger.info("Connection pool was closed");
    }

    private int closeAllConnections() throws SQLException {
        int closedConnections = 0;
        synchronized (openedConnections) {
            for (Connection connection : openedConnections.keySet()) {
                connection.close();
                closedConnections++;
            }
        }
        return closedConnections;
    }

    public static boolean isConnectionValid(DatabaseConnectionInfo connectionInfo) {

        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(connectionInfo.getJDBCURL());
        config.setUsername(connectionInfo.getUsername());
        config.setPassword(connectionInfo.getPassword());

        try {
            new BoneCP(config).close(); // just testing the connection
            return true;
        }
        catch (SQLException exc) {
            logger.error(exc + System.lineSeparator() + StackTrace.getStringFromStackTrace(exc));
            return false;
        }
    }

    public int getNumberOfOpenConnections() throws SQLException {
        int counter = 0;
        for (Connection connection : openedConnections.keySet()) {
            if (!connection.isClosed()) {
               counter++;
            }
        }
        return counter;
    }

    public List<String> getConnectionsMetadata() {
        List<String> listToReturn = new ArrayList<String>();
        for (String connectionInfo : openedConnections.values()) {
            listToReturn.add(connectionInfo);
        }

        return listToReturn;
    }
}