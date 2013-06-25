package com.pearson.Database;

import java.sql.Connection;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jeffrey Schmidt
 */
public class DatabaseUtilities {
    
    private static Logger logger = LoggerFactory.getLogger(DatabaseUtilities.class.getName());

    public static boolean setMysqlDefaultSchema(Connection connection, String schemaName) {
        
        if (connection == null) {
            return false;
        }
        
        Statement statement = null;
        
        try {
            
            if ((connection == null) || !connection.isValid(5) || connection.isReadOnly()) {
                return false;
            }
            
            statement = connection.createStatement();
            
            statement.execute("USE " + schemaName);   
            
            return true;
        }
        catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }       
        finally {
            DatabaseCleanup.cleanup(connection, statement);
        }
    }
    
    public static boolean updateSchemaStatistics() {
        
        Connection connection = DatabaseManager.getConnection();
        Statement statement = null;

        try {
            
            if ((connection == null) || !connection.isValid(5) || connection.isReadOnly()) {
                return false;
            }
            
            statement = connection.createStatement();
            
            statement.execute("CALL SYSCS_UTIL.SYSCS_UPDATE_STATISTICS('APP', 'STOCK_DATA', null)");                 
            statement.execute("CALL SYSCS_UTIL.SYSCS_UPDATE_STATISTICS('APP', 'GAINERS', null)");       
            statement.execute("CALL SYSCS_UTIL.SYSCS_UPDATE_STATISTICS('APP', 'ORDERS', null)");   
            
            connection.commit();
            
            return true;
        }
        catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }       
        finally {
            DatabaseCleanup.cleanup(connection, statement);
        }
    }
    
    public static boolean compressDerbyTable(String schemaName, String tableName) {
        
        Connection connection = DatabaseManager.getConnection();
        Statement statement = null;

        try {
            
            if ((connection == null) || !connection.isValid(5) || connection.isReadOnly()) {
                return false;
            }
            
            statement = connection.createStatement();
 
            statement.execute("CALL SYSCS_UTIL.SYSCS_COMPRESS_TABLE('" + schemaName + "', '" + tableName + "', 1)");    

            connection.commit();
            
            return true;
        }
        catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }       
        finally {
            DatabaseCleanup.cleanup(connection, statement);
        }
    }
    
    public static boolean setDerbyRuntimeStatisticsCapture(boolean isEnableStatistics) {
        
        Connection connection = DatabaseManager.getConnection();
        Statement statement = null;

        try {
            
            if ((connection == null) || !connection.isValid(5) || connection.isReadOnly()) {
                return false;
            }
            
            statement = connection.createStatement();
 
            String enabledOrDisabled;
            if (isEnableStatistics) {
                enabledOrDisabled = "1";
            }
            else {
                enabledOrDisabled = "0";
            }
            
            statement.execute("CALL SYSCS_UTIL.SYSCS_SET_RUNTIMESTATISTICS(" + enabledOrDisabled + ")");    
            statement.execute("CALL SYSCS_UTIL.SYSCS_SET_STATISTICS_TIMING(" + enabledOrDisabled + ")");  

            connection.commit();
            
            return true;
        }
        catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }       
        finally {
            DatabaseCleanup.cleanup(connection, statement);
        }
    }
    
}
