package com.pearson.Rules;

import com.pearson.SQL.MySQLTable;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Random;

import com.pearson.Utilities.Constants;

/**
 * @author Ruslan Kiselev
 *         Date: 6/14/13
 *         Time: 10:20 AM
 *         Project Name: DataScrubber
 */
public class DateSubstitution extends Substitution {

    public DateSubstitution(MySQLTable mySQLTable, String columnName) {
        super(mySQLTable, columnName);
    }

    public void setDateTo(Date date) throws SQLException {
        mySQLTable.setColumnToValue(columnName, date);
    }

    public void setDateTo(Timestamp timestamp) throws SQLException {
        mySQLTable.setColumnToValue(columnName, timestamp);
    }

    public void setDateTo(Time time) throws SQLException {
        mySQLTable.setColumnToValue(columnName, time);
    }

    public void setRandomDate(int yearPeriod, Date date) throws SQLException {
        mySQLTable.setColumnToValue(columnName, getRandomDate(yearPeriod, date));
    }

    public void setRandomDate(int yearPeriod, Timestamp timestamp) throws SQLException {
        mySQLTable.setColumnToValue(columnName, getRandomTimeStamp(yearPeriod, timestamp));
    }

    public void setDateWithinPeriod(int yearPeriod, Time time) throws SQLException {
        mySQLTable.setColumnToValue(columnName, getRandomTime(yearPeriod, time));
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
