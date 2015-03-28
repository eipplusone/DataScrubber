package com.pearson.Readers.SubstitutionReaders;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Database.MySQL.MySQLTableWorker;
import com.pearson.Database.SQL.Column;
import com.pearson.Readers.RuleReader;
import noNamespace.Rule;
import noNamespace.SubstitutionActionType;
import org.apache.commons.lang.ObjectUtils;
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

    public DateSubstitutionRuleReader(RuleReader.Builder builder) throws SQLException {
       super(builder);
    }

    public void runRule() throws SQLException, XMLParseException {

        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();
        Column selectedColumn = mySQLTableWorker.getMySQLTable().columns.get(rule.getSubstitute().getColumn());

        createAutoIncrementColumn(mySQLTable, mySQLTableWorker);

        // todo modify this to be a date within range
        if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
            if (selectedColumn.getType() == MySQLDataType.TIME) {
                for (int j = 0; j <= mySQLTableWorker.getNumberOfRows(); j++) {
                    mySQLTableWorker.updateRow(getRandomTime(), rule.getSubstitute().getColumn(), j);
                }
            } else if (selectedColumn.getType() == MySQLDataType.TIMESTAMP) {
                logger.debug("Shuffling TimeStamp");
                for (int j = 0; j <= mySQLTableWorker.getNumberOfRows(); j++) {
                    mySQLTableWorker.updateRow(getRandomTimeStamp(), rule.getSubstitute().getColumn(), j);
                }
            }
            // in case of Date, DateTime, Year
            else if (selectedColumn.getType() == MySQLDataType.DATE ||
                    selectedColumn.getType() == MySQLDataType.DATETIME ||
                    selectedColumn.getType() == MySQLDataType.YEAR) {
                logger.debug("Shuffling Date/DateTime/Year");
                for (int j = 0; j <= mySQLTableWorker.getNumberOfRows(); j++) {
                    mySQLTableWorker.updateRow(getRandomDate(), rule.getSubstitute().getColumn(), j);
                }
            } else throw new XMLParseException("Date type doesn't match any supported date types");
        } else if (actionType == SubstitutionActionType.SET_TO_NULL) {
            setToNull();
        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {
            if (selectedColumn.getType() == MySQLDataType.TIME) {
                mySQLTableWorker.setColumnToValue(selectedColumn.name, new Time(rule.getSubstitute().getDateValue1().longValue()));
            } else if (selectedColumn.getType() == MySQLDataType.TIMESTAMP) {
                mySQLTableWorker.setColumnToValue(selectedColumn.name, new Timestamp(rule.getSubstitute().getDateValue1().longValue()));
            }
            // in case of Date, DateTime, Year
            else {
                mySQLTableWorker.setColumnToValue(selectedColumn.name, new Date(rule.getSubstitute().getDateValue1().longValue()));
            }
        }

        mySQLTableWorker.cleanupAutomatic();
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
