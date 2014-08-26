package com.pearson.Readers;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * Created by Ruslan Kiselev on 8/26/14.
 * <p/>
 * May the force be with you.
 */
public class MySQLException extends SQLException {
    // Rule ID for future reference
    private String ruleID;
    private SQLException exception;


    public MySQLException(SQLException e, String ruleID) {
        this.ruleID = ruleID;
        this.exception = e;
    }

    @Override
    public String getSQLState() {
        return exception.getSQLState();
    }

    @Override
    public int getErrorCode() {
        return exception.getErrorCode();
    }

    @Override
    public SQLException getNextException() {
        return exception.getNextException();
    }

    @Override
    public void setNextException(SQLException ex) {
        exception.setNextException(ex);
    }

    @Override
    public Iterator<Throwable> iterator() {
        return exception.iterator();
    }

    @Override
    public String getMessage() {
        return exception.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return exception.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return exception.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return exception.initCause(cause);
    }

    @Override
    public String toString() {
        return exception.toString();
    }

    @Override
    public void printStackTrace() {
        exception.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        exception.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        exception.printStackTrace(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return exception.fillInStackTrace();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return exception.getStackTrace();
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        exception.setStackTrace(stackTrace);
    }

    public String getRuleID() {
        return ruleID;
    }
}
