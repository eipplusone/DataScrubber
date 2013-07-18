package com.pearson.Readers;

import com.pearson.Rules.SubstitutionTypes.DateSubstitutionTypes;
import com.pearson.SQL.Database;
import com.pearson.SQL.MySQLTable;
import com.pearson.Utilities.Constants;
import noNamespace.Rule;
import noNamespace.SubstitutionActionType;

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

    public DateSubstitutionRuleReader(Rule rule, Database database) {
        super(rule, database);
    }

    @Override
    public void run() {

        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();

        if (actionType == SubstitutionActionType.SET_TO_RANDOM) {

        }
        else if (actionType == SubstitutionActionType.SET_TO_NULL) {

        }
        else if (actionType == SubstitutionActionType.SET_TO_VALUE) {

        }
    }

    public void setDateTo(Date date) throws SQLException {
        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), date);
    }

    public void setDateTo(Timestamp timestamp) throws SQLException {
        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), timestamp);
    }

    public void setDateTo(Time time) throws SQLException {
        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), time);
    }

    public void setRandomDate(int yearPeriod, Date date) throws SQLException {
        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), getRandomDate(yearPeriod, date));
    }

    public void setRandomDate(int yearPeriod, Timestamp timestamp) throws SQLException {
        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), getRandomTimeStamp(yearPeriod, timestamp));
    }

    public void setDateWithinPeriod(int yearPeriod, Time time) throws SQLException {
        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), getRandomTime(yearPeriod, time));
    }

    private Date getRandomDate(int yearPeriod, Date date){

        double randomYear = Math.random() * yearPeriod;

        long offset = (long)(randomYear * Constants.MILLISECONDS_IN_A_YEAR);

        Random random = new Random();

        long randomMillisFromEpoch = date.getTime() + (random.nextBoolean() ? offset : offset * (-1));

        return new Date(randomMillisFromEpoch);

    }

    private Timestamp getRandomTimeStamp(int yearPeriod, Timestamp timestamp){

        double randomYear = Math.random() * yearPeriod;

        long offset = (long)(randomYear * Constants.MILLISECONDS_IN_A_YEAR);

        Random random = new Random();

        long randomMillisFromEpoch = timestamp.getTime() + (random.nextBoolean() ? offset : offset * (-1));

        return new Timestamp(randomMillisFromEpoch);

    }

    private Time getRandomTime(int yearPeriod, Time time){

        return new Time(System.currentTimeMillis() - new Random().nextLong());

    }
}
