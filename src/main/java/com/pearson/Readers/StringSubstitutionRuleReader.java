package com.pearson.Readers;

import com.pearson.SQL.Database;
import noNamespace.Rule;
import noNamespace.SubstitutionActionType;
import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Ruslan Kiselev
 *         Date: 7/17/13
 *         Time: 4:24 PM
 *         Project Name: DataScrubber
 */
public class StringSubstitutionRuleReader extends SubstitutionReader {

    public StringSubstitutionRuleReader(Rule rule, Database database) {
        super(rule, database);
    }

    @Override
    public void run() {

        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();

        if (actionType == SubstitutionActionType.SET_FROM_FILE) {
            File selectedFile = new File(rule.getSubstitute().getFilePath());

            try {
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
                disableConstraints();
                setFromFile(selectedFile);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
            int stringLength = rule.getSubstitute().getNumericValue().intValue();

            try {
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
                disableConstraints();
                setToRandom(stringLength);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {
            String setToString = rule.getSubstitute().getStringValue1();

            try {
                mySQLTable.getConnectionConfig().setDefaultDatabase(database);
                disableConstraints();
                setToValue(setToString);
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
        }

        mySQLTable.cleanResourses();
    }

    public void setToValue(String value) throws SQLException {

        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), value);
    }

    public void setToRandom(int count) throws SQLException {

        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), RandomStringUtils.random(count));
    }

    /**
     * Updates the column with random values within the file
     */
    public void setFromFile(File file) throws SQLException {

        HashMap<Integer, String> words = new HashMap();
        Scanner scanner = null;

        //build a hashmap of words to replace
        try {
            scanner = new Scanner(file);

            for (int i = 0; i < mySQLTable.getNumberOfRows(); i++) {
                // if there are still words inside the scanner
                if (scanner.hasNext()) {
                    String stringToUpdate = scanner.next();
                    // if we reached the end of file make sure that no newline character is inside
                    if (stringToUpdate.contains("\n")) {
                        stringToUpdate.replaceAll("\n", "");
                    }

                    words.put(i, stringToUpdate);
                    assert (stringToUpdate != null);
                    // if there are no more words inside scanner just start from the beginning of file
                } else {
                    scanner = new Scanner(file);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        Random random = new Random();
        // get a random word from the list and update with it next row inside column
        for (int i = 0; i < mySQLTable.getNumberOfRows(); i++) {
            String stringToUpdate = words.get(random.nextInt(words.size() - 1));
            mySQLTable.updateRow(stringToUpdate, rule.getSubstitute().getColumn(), i);
        }

    }
}
