package com.pearson.Readers.SubstitutionReaders;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Database.MySQL.MySQLTable;
import com.pearson.Database.SQL.Column;
import com.pearson.Database.SQL.Database;
import noNamespace.Rule;
import noNamespace.SubstitutionActionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.modelmbean.XMLParseException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author Ruslan Kiselev
 *         Date: 7/17/13
 *         Time: 4:22 PM
 *         Project Name: DataScrubber
 */
public class DateSubstitutionRuleReader extends SubstitutionReader {

    private static Logger logger = LoggerFactory.getLogger(DateSubstitutionRuleReader.class.getName());

    public DateSubstitutionRuleReader(Rule rule, Database database, MySQLTable mySQLTable) {
        super(rule, database, mySQLTable);
    }

    @Override
    public Rule call() throws SQLException, XMLParseException {

        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();
        Column selectedColumn = mySQLTable.columns.get(rule.getSubstitute().getColumn());
        prepareToRun();

        //todo modify this to be a date within range
        if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
            if (selectedColumn.getType() == MySQLDataType.TIME) {
                logger.debug("Shuffling time");
                for (int j = 0; j <= mySQLTable.getNumberOfRows(); j++) {
                    mySQLTable.updateRow(getRandomTime(), rule.getSubstitute().getColumn(), j);
                }
            } else if (selectedColumn.getType() == MySQLDataType.TIMESTAMP) {
                logger.debug("Shuffling TimeStamp");
                for (int j = 0; j <= mySQLTable.getNumberOfRows(); j++) {
                    mySQLTable.updateRow(getRandomTimeStamp(), rule.getSubstitute().getColumn(), j);
                }
            }
            // in case of Date, DateTime, Year
            else if (selectedColumn.getType() == MySQLDataType.DATE ||
                    selectedColumn.getType() == MySQLDataType.DATETIME ||
                    selectedColumn.getType() == MySQLDataType.YEAR) {
                logger.debug("Shuffling Date/DateTime/Year");
                for (int j = 0; j <= mySQLTable.getNumberOfRows(); j++) {
                    mySQLTable.updateRow(getRandomDate(), rule.getSubstitute().getColumn(), j);
                }
            } else throw new XMLParseException("Date type doesn't match any supported date types");
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
        return (long) (System.currentTimeMillis() * Math.random());
    }
}
