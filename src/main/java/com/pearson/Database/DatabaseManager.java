package com.pearson.Database;

import ch.qos.logback.core.pattern.color.BlackCompositeConverter;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    private Set<Connection> openConnections;
    public BoneCP connectionPool;

    public DatabaseManager(String username, String password, String JDBCURL) throws SQLException {

        openConnections = Collections.synchronizedSet(new HashSet());
        createConnectionPool(username, password, JDBCURL);
    }

    public void createConnectionPool(String username, String password, String JDBCURL) throws SQLException {

        BoneCPConfig config = new BoneCPConfig();
        config.setMaxConnectionsPerPartition(20);
        config.setJdbcUrl(JDBCURL);
        config.setJdbcUrl(JDBCURL);
        config.setUsername(username);

        config.setUsername(username);
        config.setPassword(password);
        config.setDefaultAutoCommit(false);

        connectionPool = new BoneCP(config);
    }

    public Connection getConnection() throws SQLException {
        Connection returnConnection = connectionPool.getConnection();
        openConnections.add(returnConnection);
        returnConnection.close();
        return returnConnection;
    }

    public void disconnect(Connection connection) throws SQLException {

        connection.close();
    }

    public void shutDown() throws SQLException {

        closeAllConnections();
        connectionPool.close();
    }

    public void closeAllConnections() throws SQLException {

        synchronized (openConnections){
            for(Connection connection : openConnections){
                connection.close();
            }
        }
    }
}
