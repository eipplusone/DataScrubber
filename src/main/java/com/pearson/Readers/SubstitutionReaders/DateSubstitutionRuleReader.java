package com.pearson.Readers.SubstitutionReaders;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Database.SQL.Column;
import com.pearson.Database.SQL.Database;
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
    public Rule call() throws SQLException {

        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();
        Column selectedColumn = mySQLTable.columns.get(rule.getSubstitute().getColumn());
        prepareToRun();

        if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
            if (selectedColumn.getType() == MySQLDataType.TIME) {
                mySQLTable.setColumnToValue(selectedColumn.name, getRandomTime());
            } else if (selectedColumn.getType() == MySQLDataType.TIMESTAMP) {
                mySQLTable.setColumnToValue(selectedColumn.name, getRandomTimeStamp());
            }
            // in case of Date, DateTime, Year
            else {
                mySQLTable.setColumnToValue(selectedColumn.name, getRandomDate());
            }

        } else if (actionType == SubstitutionActionType.SET_TO_NULL) {
            setToNull();

        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {
            if (selectedColumn.getType() == MySQLDataType.TIME) {
                mySQLTable.setColumnToValue(selectedColumn.name, new Time(rule.getSubstitute().getDateValue1().longValue()));
            } else if (selectedColumn.getType() == MySQLDataType.TIMESTAMP) {
                mySQLTable.setColumnToValue(selectedColumn.name, new Timestamp(rule.getSubstitute().getDateValue1().longValue()));
            }
            // in case of Date, DateTime, Year
            else {
                mySQLTable.setColumnToValue(selectedColumn.name, new Date(rule.getSubstitute().getDateValue1().longValue()));
            }
        }

        mySQLTable.cleanResourses();

        return rule;
    }

    private Time getRandomTime() {
        return new Time(getRandomMillis());
    }

    private Date getRandomDate() {
        return new Date(getRandomMillis());
    }

    private Timestamp getRandomTimeStamp() {
        return new Timestamp(getRandomMillis());
    }

    private long getRandomMillis() {
        return System.currentTimeMillis() - new Random().nextLong();
    }
}
