package com.pearson.Rules;

import com.pearson.SQL.MySQLTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.*;

/**
 * @author Ruslan Kiselev
 *         Date: 6/14/13
 *         Time: 10:20 AM
 *         Project Name: DataScrubber
 */
public class StringSubstitution extends Substitution {

    public StringSubstitution(String mySQLTable, String columnName) {
        super(mySQLTable, columnName);
    }

//    public void setToValue(String value) throws SQLException {
//
//        mySQLTable.disableUniqueChecks();
//        mySQLTable.disableForeignKeyConstraints();
//        mySQLTable.disableTriggers();
//
//        mySQLTable.setColumnToValue(columnName, value);
//
//        mySQLTable.enableTriggers();
//        mySQLTable.enableForeignKeyConstraints();
//        mySQLTable.enableUniqueChecks();
//    }
//
//    public void setToRandom(int count) throws SQLException {
//
//        mySQLTable.disableUniqueChecks();
//        mySQLTable.disableForeignKeyConstraints();
//        mySQLTable.disableTriggers();
//
//        mySQLTable.setColumnToValue(columnName, RandomStringUtils.random(count));
//
//        mySQLTable.enableTriggers();
//        mySQLTable.enableForeignKeyConstraints();
//        mySQLTable.enableUniqueChecks();
//    }

    /**
     * Updates the column with random values within the file
     */
//    public void setFromFile(File file) throws SQLException {
//        HashMap<Integer, String> words = new HashMap<>();
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(file);
//
//            for (int i = 0; i < mySQLTable.getNumberOfRows(); i++) {
//                if (scanner.hasNext()) {
//                    String stringToUpdate = scanner.next();
//                    // if we reached the end of file make sure that no newline character is inside
//                    if (stringToUpdate.contains("\n")) {
//                        stringToUpdate.replaceAll("\n", "");
//                        words.put(i, stringToUpdate);
//                    }
//                    assert (stringToUpdate != null);
//
//                } else {
//                    scanner = new Scanner(file);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        finally {
//            scanner.close();
//        }
//        Random random = new Random();
//        for (int i = 0; i < mySQLTable.getNumberOfRows(); i++){
//            String stringToUpdate = words.get(random.nextInt(words.size() - 1));
//            mySQLTable.updateRow(stringToUpdate, columnName, i);
//        }
//
//    }
}
