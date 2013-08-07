package com.pearson.Readers;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Database.SQL.Column;
import com.pearson.Database.SQL.Database;
import com.pearson.Utilities.StackTrace;
import noNamespace.Rule;
import noNamespace.SubstitutionActionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Random;

/**
 * @author Ruslan Kiselev
 *         Date: 7/17/13
 *         Time: 4:22 PM
 *         Project Name: DataScrubber
 */
public class DateSubstitutionRuleReader extends SubstitutionReader {
    
    private static Logger logger = LoggerFactory.getLogger(DateSubstitutionRuleReader.class.getName());

    public DateSubstitutionRuleReader(Rule rule, Database database) {
        super(rule, database);
    }

    @Override
    public void run() {

        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();
        Column selectedColumn = mySQLTable.columns.get(rule.getSubstitute().getColumn());

        if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
            try {
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
                disableConstraints();
                if (selectedColumn.getType() == MySQLDataType.TIME){
                    mySQLTable.setColumnToValue(selectedColumn.name, getRandomTime());
                }
                else if (selectedColumn.getType() == MySQLDataType.TIMESTAMP){
                    mySQLTable.setColumnToValue(selectedColumn.name, getRandomTimeStamp());
                }
                // in case of Date, DateTime, Year
                else {
                    mySQLTable.setColumnToValue(selectedColumn.name, getRandomDate());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (actionType == SubstitutionActionType.SET_TO_NULL) {
            try {
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
                disableConstraints();
                setToNull();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {

            try {
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
                disableConstraints();
                if (selectedColumn.getType() == MySQLDataType.TIME){
                    mySQLTable.setColumnToValue(selectedColumn.name, new Time(rule.getSubstitute().getDateValue1().longValue()));
                }
                else if (selectedColumn.getType() == MySQLDataType.TIMESTAMP){
                    mySQLTable.setColumnToValue(selectedColumn.name, new Timestamp(rule.getSubstitute().getDateValue1().longValue()));
                }
                // in case of Date, DateTime, Year
                else {
                    mySQLTable.setColumnToValue(selectedColumn.name, new Date(rule.getSubstitute().getDateValue1().longValue()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            mySQLTable.cleanResourses();
        } catch (SQLException e) {
            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
        }
    }

    private Time getRandomTime(){
        return new Time(getRandomMillis());
    }

    private Date getRandomDate(){
        return new Date(getRandomMillis());
    }

    private Timestamp getRandomTimeStamp(){
        return new Timestamp(getRandomMillis());
    }

    private long getRandomMillis() {
        return System.currentTimeMillis() - new Random().nextLong();
    }
}
