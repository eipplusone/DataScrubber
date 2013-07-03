package com.pearson.Rules;


import java.sql.SQLException;
import java.util.Random;

/**
 * @author Ruslan Kiselev
 *         Date: 6/14/13
 *         Time: 10:20 AM
 *         Project Name: DataScrubber
 */
public class NumericSubstitution extends Substitution {

    public NumericSubstitution(String mySQLTable, String columnName) {
        super(mySQLTable, columnName);
    }

//    public void setToRandom(MySQLDataType dataType) throws SQLException {
//        if(dataType == MySQLDataType.BIGINT || dataType == MySQLDataType.INT)
//            mySQLTable.setColumnToValue(columnName, new Random().nextInt());
//        else if (dataType == MySQLDataType.SMALLINT)
//            mySQLTable.setColumnToValue(columnName, new Random().nextInt(32767));
//        else if (dataType == MySQLDataType.TINYINT)
//            mySQLTable.setColumnToValue(columnName, new Random().nextInt(127));
//        else if (dataType == MySQLDataType.MEDIUMINT)
//            mySQLTable.setColumnToValue(columnName, new Random().nextInt(8388607));
//        else if (dataType == MySQLDataType.FLOAT)
//            mySQLTable.setColumnToValue(columnName, new Random().nextFloat());
//        else if (dataType == MySQLDataType.DOUBLE)
//            mySQLTable.setColumnToValue(columnName, new Random().nextDouble());
//
//    }
//
//    public void setToValue(int value) throws SQLException {
//        mySQLTable.setColumnToValue(columnName, value);
//    }
//
//    public void setToValue(double value) throws SQLException {
//        mySQLTable.setColumnToValue(columnName, value);
//
//    }
//
//    public void setToValue(float value) throws SQLException {
//        mySQLTable.setColumnToValue(columnName, value);
//
//    }
}
