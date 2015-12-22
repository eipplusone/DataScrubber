package com.pearson.Readers.SubstitutionReaders;

import com.pearson.Database.MySQL.MySQLTableWorker;
import com.pearson.Database.SQL.MySQLTable;
import com.pearson.Readers.RuleReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * This is an abstract class for three types of substitution readers(sorted by type). SetToNull,
 * enablsing and disabling constraints are shared by different types of substitutions
 *
 * @author Ruslan Kiselev
 *         Date: 7/18/13
 *         Time: 9:22 AM
 *         Project Name: DataScrubber
 */
public abstract class SubstitutionReader extends RuleReader {

    private static Logger logger = LoggerFactory.getLogger(SubstitutionReader.class.getName());
    MySQLTableWorker mySQLTableWorker;

    protected SubstitutionReader(RuleReader.Builder builder) throws SQLException {
        super(builder);
        this.mySQLTableWorker = new MySQLTableWorker(mySQLTable, connection, databaseName);
    }

    public void setToNull() throws SQLException {

        mySQLTableWorker.setColumnToNull(rule.getSubstitute().getColumn());
    }

    protected void createAutoIncrementColumn(MySQLTable table, MySQLTableWorker mySQLTableWorker) throws SQLException {
        synchronized (table) {
            if (mySQLTableWorker.getAutoIncrementColumn() == null) {
                mySQLTableWorker.addAutoIncrementColumn();
            }
        }
    }
}
