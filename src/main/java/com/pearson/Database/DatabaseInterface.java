package com.pearson.Database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jeffrey Schmidt
 */
public class DatabaseInterface {
    
    private static Logger logger = LoggerFactory.getLogger(DatabaseInterface.class.getName());
    
    private Connection connection_ = null;
    private PreparedStatement preparedStatement_ = null;
    private List<Object> preparedStatementParameters_ = null;
    private Statement statement_ = null;
    private ResultSet results_ = null;
    private boolean isTransactionOpen_ = false;
    private boolean closeConnectionAfterOperation_ = true;
    
    public DatabaseInterface(Connection connection) {
        connection_ = connection;
    }
    
    public DatabaseInterface(Connection connection, boolean beginTransaction) {
        connection_ = connection;
        isTransactionOpen_ = beginTransaction;
    }
    
    public void beginTransaction() {
        
        try {           
            if (connection_.getAutoCommit()) {
                logger.warn("Cannot being a transaction when autocommit mode is enabled");
            }
            else {
                if (!isTransactionOpen_) {
                    isTransactionOpen_ = true;
                }
                else {
                    logger.debug("Request to being a transaction on a DatabaseInterface when transaction is already open");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    public void cleanupAutomatic() {
        if (isTransactionOpen_) {
            cleanupForNextStatement();
        }
        else if (closeConnectionAfterOperation_) {
            close();
        }
        else if (!closeConnectionAfterOperation_ && !isTransactionOpen_) {
            cleanupForNextStatement();
        }
    }

    public void cleanupForNextStatement() {
        DatabaseCleanup.cleanup(statement_);
        DatabaseCleanup.cleanup(preparedStatement_);
        DatabaseCleanup.cleanup(results_);
         
        if (preparedStatementParameters_ != null) {
            preparedStatementParameters_.clear();
        }
    }
    
    public void close() {
        
        if (isTransactionOpen_ == true) {
            logger.warn("Closing a DatabaseInterface with an open transaction. Rolling back transaction...");
            rollback();
        }
        
        if (preparedStatementParameters_ != null) {
            preparedStatementParameters_.clear();
            preparedStatementParameters_ = null;
        }

        DatabaseCleanup.cleanup(this);
    }
    
    public Statement createStatement() {
        try {
            if (connection_ == null) {
                return null;
            }

            statement_ = connection_.createStatement();
            return statement_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public Statement createStatement(int fetchSize) {
        try {
            if (connection_ == null) {
                return null;
            }

            statement_ = connection_.createStatement();
            statement_.setFetchSize(fetchSize);
            return statement_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public Statement setStatement(Statement statement) {
        try {
            if (connection_ == null) {
                return null;
            }
   
            statement_ = statement;
            return statement_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public Statement setStatement(Statement statement, int fetchSize) {
        try {
            if (connection_ == null) {
                return null;
            }

            statement_ = statement;
            statement_.setFetchSize(fetchSize);
            return statement_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public PreparedStatement createPreparedStatement(String sql) {
        try {
            if (connection_ == null) {
                return null;
            }
   
            preparedStatement_ = connection_.prepareStatement(sql);
            preparedStatementParameters_ = new ArrayList<>();
            
            return preparedStatement_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public PreparedStatement createPreparedStatement(String sql, List<Object> preparedStatementParameters) {
        try {
            if (connection_ == null) {
                return null;
            }
   
            preparedStatement_ = connection_.prepareStatement(sql);
            preparedStatementParameters_ = preparedStatementParameters;
            
            return preparedStatement_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public PreparedStatement createPreparedStatement(String sql, int fetchSize) {
        try {
            if (connection_ == null) {
                return null;
            }
   
            preparedStatement_ = connection_.prepareStatement(sql);
            preparedStatement_.setFetchSize(fetchSize);
            preparedStatementParameters_ = new ArrayList<>();
            return preparedStatement_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public PreparedStatement createPreparedStatement(String sql, int fetchSize, List<Object> preparedStatementParameters) {
        try {
            if (connection_ == null) {
                return null;
            }
   
            preparedStatement_ = connection_.prepareStatement(sql);
            preparedStatement_.setFetchSize(fetchSize);
            preparedStatementParameters_ = preparedStatementParameters;
            return preparedStatement_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public PreparedStatement setPreparedStatement(PreparedStatement preparedStatement) {
        try {
            if (connection_ == null) {
                return null;
            }
   
            preparedStatement_ = preparedStatement;
            preparedStatementParameters_ = new ArrayList<>();
            return preparedStatement_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public ResultSet executePreparedStatement() {
        try {
            if ((connection_ == null) || (preparedStatement_ == null)) {
                throw new NullPointerException("DatabaseInterface - connection or preparedStatement is null");
            }
   
            setPreparedStatementParameters();
            
            boolean result = preparedStatement_.execute();
            
            if (result) {
                results_ = preparedStatement_.getResultSet();
            }
             
            return results_;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }
    
    public boolean executeBatchPreparedStatement() {
        try {
            if ((connection_ == null) || (preparedStatement_ == null)) {
                return false;
            }
   
            if (results_ != null) {
                if (!results_.isClosed()) {
                    logger.error("Cannot submit PreparedStatement - DatabaseInterface already has an open ResultSet");
                    return false;
                }
            }
            
            int[] results = preparedStatement_.executeBatch();

            if ((results.length == Statement.EXECUTE_FAILED) || (results.length == Statement.SUCCESS_NO_INFO)) {
                logger.error("PreparedStatement executeBatch failed. Result=" + results.length);
                return false;
            }
            
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }   
    }
    
    public List<Object> addPreparedStatementParameters(Object... objects) {
        try {
            if ((connection_ == null) || (preparedStatement_ == null) || (preparedStatementParameters_ == null)) {
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
    
    public boolean commit() {
        
        try {
            if (!connection_.getAutoCommit()) {
                connection_.commit();
            }
            else {
                logger.debug("Cannot commit when Auto-Commit is enabled");
            }

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {       
            isTransactionOpen_ = false;
        }
    }
    
    public void rollback() {
        
        try {
            if (!connection_.getAutoCommit()) {
                connection_.rollback();
            }
            else {
                logger.debug("Cannot rollback when Auto-Commit is enabled");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {       
            isTransactionOpen_ = false;
        }
    }
    
    public boolean endTransaction(boolean commitTransaction) {
        
        boolean endTransactionSuccess = false;
        
        if (commitTransaction) {
            endTransactionSuccess = commit();
        }
        else {
            rollback();
        }
        
        isTransactionOpen_ = false;
        return endTransactionSuccess;
    }
    
    public boolean endTransactionConditional(boolean commitTransaction) {
        
        boolean endTransactionSuccess = true;
        
        if (commitTransaction && !isTransactionOpen()) {
            endTransactionSuccess = commit();
        }
        else if (!isTransactionOpen()) {
            rollback();
        }
        
        return endTransactionSuccess;
    }
    
    public boolean isConnectionValid() {
        try {
            if (connection_ == null) {
                return false;
            }
            else if (!connection_.isValid(5)) {
                connection_.close();
                return false;
            }
            else {
                return true;
            }
        }
        catch (Exception e) {
            try {
                connection_.close();
            }
            catch (Exception e2) {}
            
            e.printStackTrace();
            return false;
        }   
    }
    
    public boolean isConnectionValid(int timeout) {
        try {
            if (connection_ == null) {
                return false;
            }
            else if (!connection_.isValid(timeout)) {
                connection_.close();
                return false;
            }
            else {
                return true;
            }
        }
        catch (Exception e) {
            try {
                connection_.close();
            }
            catch (Exception e2) {}
            
            e.printStackTrace();
            
            return false;
        }   
    }
    
    public boolean isResultSetValid() {
        try {
            if ((results_ == null) || results_.isClosed()) {
                return false;
            }
            else {
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }   
    }
    
    private int setPreparedStatementParameter(Object object, int index) {
        
        if ((preparedStatement_ == null) || (preparedStatementParameters_ == null)) {
            logger.warn("Can't set preparedStatementParameters - preparedStatementParameters or preparedStatement is null");
            return -1;
        } 
        
        try {
            if (object == null) {
                preparedStatement_.setObject(index++, null);
            }       
            else if (object instanceof Boolean) {
                preparedStatement_.setBoolean(index++, (Boolean) object);
            }
            else if (object instanceof Byte) {
                preparedStatement_.setByte(index++, (Byte) object);
            }
            else if (object instanceof BigDecimal) {
                preparedStatement_.setBigDecimal(index++, (BigDecimal) object);
            }
            else if (object instanceof Integer) {
                preparedStatement_.setInt(index++, (Integer) object);
            }
            else if (object instanceof Long) {
                preparedStatement_.setLong(index++, (Long) object);
            }
            else if (object instanceof Double) {
                preparedStatement_.setDouble(index++, (Double) object);
            }
            else if (object instanceof Float) {
                preparedStatement_.setFloat(index++, (Float) object);
            }
            else if (object instanceof String) {
                preparedStatement_.setString(index++, (String) object);
            }
            else if (object instanceof List) {
                for (Object listObject : (List) object) {
                    setPreparedStatementParameter(listObject, index++);
                }
            }
            else if (object instanceof java.sql.Timestamp) {
                preparedStatement_.setTimestamp(index++, (java.sql.Timestamp) object);
            }
            else if (object instanceof java.sql.Date) {
                preparedStatement_.setDate(index++, (java.sql.Date) object);
            }
            else if (object instanceof java.util.Date) {
                java.util.Date tempDate = (java.util.Date) object;
                java.sql.Date dateSql = new java.sql.Date(tempDate.getTime());
                preparedStatement_.setDate(index++, dateSql);
            }
            else {
                if (object instanceof Object) {}
                else {
                    logger.warn("Setting PreparedStatement parameter to 'object' type when object is not an object type");
                }
                
                preparedStatement_.setObject(index++, object);
            }
            
            return index;
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }  
    }
    
    public void setPreparedStatementParameters() {
        
        if ((preparedStatement_ == null) || (preparedStatementParameters_ == null)) {
            logger.warn("Can't set preparedStatementParameters - preparedStatementParameters or preparedStatement is null");
            return;
        } 
        
        try {
            int index = 1;

            for (Object object : preparedStatementParameters_) {
                int incrementedIndex = setPreparedStatementParameter(object, index);
                index = incrementedIndex;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }   
    }

    public Connection getConnection() {
        return connection_;
    }
    
    public Statement getStatement() {
        return statement_;
    }
    
    public PreparedStatement getPreparedStatement() {
        return preparedStatement_;
    }
    
    public List<Object> getPreparedStatementParameters() {
        return preparedStatementParameters_;
    }
    
    public ResultSet getResults() {
        return results_;
    }

    public boolean isTransactionOpen() {
        return isTransactionOpen_;
    }
    
    public void setTransactionOpen(boolean isTransactionOpen) {
        this.isTransactionOpen_ = isTransactionOpen;
    }
    
    public boolean isCloseConnectionAfterOperation() {
        return closeConnectionAfterOperation_;
    }
    
    public void setCloseConnectionAfterOperation(boolean closeConnectionAfterOperation) {
        this.closeConnectionAfterOperation_ = closeConnectionAfterOperation;
    }
}
