package pearson.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jeffrey Schmidt
 */
public class DatabaseInterface {
    
    private static Logger logger = LoggerFactory.getLogger(DatabaseInterface.class.getName());
    
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private List<Object> preparedStatementParameters_ = null;
    private Statement statement = null;
    private ResultSet results = null;

    public DatabaseInterface(Connection connection) {
        this.connection = connection;
    }
    
    public void cleanupAutomatic() throws SQLException {

        if (connection != null){
            connection.close();
        }

        if (results != null) {
            results.close();
        }

        if(preparedStatement != null) {
            preparedStatement.close();
        }

        if (statement != null) {
            statement.close();
        }
    }

    public Statement createStatement() throws SQLException {

            statement = connection.createStatement();
            return statement;
    }
    
    public PreparedStatement createPreparedStatement(String sql) throws SQLException {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatementParameters_ = new ArrayList<>();
            
            return preparedStatement;
    }
    
    public ResultSet executePreparedStatement() throws SQLException {

            setPreparedStatementParameters();
            
            boolean result = preparedStatement.execute();
            
            if (result) {
                results = preparedStatement.getResultSet();
            }
             
            return results;
    }
    
    public List<Object> addPreparedStatementParameters(Object... objects) {
        try {
            if ((connection == null) || (preparedStatement == null) || (preparedStatementParameters_ == null)) {
                return null;
            } 
            
            preparedStatementParameters_.addAll(Arrays.asList(objects));
            
            return preparedStatementParameters_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }  
    }
    
    public void commit() throws SQLException {
        
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            else {
                logger.debug("Cannot commit when Auto-Commit is enabled");
            }
    }
    
    public void rollback() throws SQLException {
        
            if (!connection.getAutoCommit()) {
                connection.rollback();
            }
            else {
                logger.debug("Cannot rollback when Auto-Commit is enabled");
            }
    }
    
    public boolean isConnectionValid() throws SQLException {
            if (connection == null) {
                return false;
            }
            else if (!connection.isValid(5)) {
                connection.close();
                return false;
            }
            else {
                return true;
            }
    }

    private int setPreparedStatementParameter(Object object, int index) {
        
        if ((preparedStatement == null) || (preparedStatementParameters_ == null)) {
            logger.warn("Can't set preparedStatementParameters - preparedStatementParameters or preparedStatement is null");
            return -1;
        } 
        
        try {
            if (object == null) {
                preparedStatement.setObject(index++, null);
            }       
            else if (object instanceof Boolean) {
                preparedStatement.setBoolean(index++, (Boolean) object);
            }
            else if (object instanceof Byte) {
                preparedStatement.setByte(index++, (Byte) object);
            }
            else if (object instanceof BigDecimal) {
                preparedStatement.setBigDecimal(index++, (BigDecimal) object);
            }
            else if (object instanceof Integer) {
                preparedStatement.setInt(index++, (Integer) object);
            }
            else if (object instanceof Long) {
                preparedStatement.setLong(index++, (Long) object);
            }
            else if (object instanceof Double) {
                preparedStatement.setDouble(index++, (Double) object);
            }
            else if (object instanceof Float) {
                preparedStatement.setFloat(index++, (Float) object);
            }
            else if (object instanceof String) {
                preparedStatement.setString(index++, (String) object);
            }
            else if (object instanceof List) {
                for (Object listObject : (List) object) {
                    setPreparedStatementParameter(listObject, index++);
                }
            }
            else if (object instanceof java.sql.Timestamp) {
                preparedStatement.setTimestamp(index++, (java.sql.Timestamp) object);
            }
            else if (object instanceof java.sql.Date) {
                preparedStatement.setDate(index++, (java.sql.Date) object);
            }
            else if (object instanceof java.util.Date) {
                java.util.Date tempDate = (java.util.Date) object;
                java.sql.Date dateSql = new java.sql.Date(tempDate.getTime());
                preparedStatement.setDate(index++, dateSql);
            }
            else {
                if (object instanceof Object) {}
                else {
                    logger.warn("Setting PreparedStatement parameter to 'object' type when object is not an object type");
                }
                
                preparedStatement.setObject(index++, object);
            }
            
            return index;
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }  
    }
    
    public void setPreparedStatementParameters() {
        
        if ((preparedStatement == null) || (preparedStatementParameters_ == null)) {
            logger.warn("Can't set preparedStatementParameters - preparedStatementParameters or preparedStatement is null");
            return;
        } 
        
            int index = 1;

            for (Object object : preparedStatementParameters_) {
                int incrementedIndex = setPreparedStatementParameter(object, index);
                index = incrementedIndex;
            }
    }

    public Connection getConnection() {
        return connection;
    }
    
    public Statement getStatement() {
        return statement;
    }
    
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
    
    public List<Object> getPreparedStatementParameters() {
        return preparedStatementParameters_;
    }
    
    public ResultSet getResults() {
        return results;
    }

}
