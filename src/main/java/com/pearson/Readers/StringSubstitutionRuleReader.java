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

    Rule rule;

    @Override
    public void run() {
        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();

        if(actionType == SubstitutionActionType.SET_FROM_FILE) {

        }
        else if (actionType == SubstitutionActionType.SET_TO_RANDOM) {

        }
        else if (actionType == SubstitutionActionType.SET_TO_VALUE) {

        }
        else if (actionType == SubstitutionActionType.SET_TO_NULL) {

        }

    }

    public void setToValue(String value) throws SQLException {

        mySQLTable.disableUniqueChecks();
        mySQLTable.disableForeignKeyConstraints();
        mySQLTable.disableTriggers();

        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), value);

        mySQLTable.enableTriggers();
        mySQLTable.enableForeignKeyConstraints();
        mySQLTable.enableUniqueChecks();
    }

    public void setToRandom(int count) throws SQLException {

        mySQLTable.disableUniqueChecks();
        mySQLTable.disableForeignKeyConstraints();
        mySQLTable.disableTriggers();

        mySQLTable.setColumnToValue(rule.getSubstitute().getColumn(), RandomStringUtils.random(count));

        mySQLTable.enableTriggers();
        mySQLTable.enableForeignKeyConstraints();
        mySQLTable.enableUniqueChecks();
    }

    /**
     * Updates the column with random values within the file
     */
    public void setFromFile(File file) throws SQLException {
        HashMap<Integer, String> words = new HashMap<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

            for (int i = 0; i < mySQLTable.getNumberOfRows(); i++) {
                if (scanner.hasNext()) {
                    String stringToUpdate = scanner.next();
                    // if we reached the end of file make sure that no newline character is inside
                    if (stringToUpdate.contains("\n")) {
                        stringToUpdate.replaceAll("\n", "");
                        words.put(i, stringToUpdate);
                    }
                    assert (stringToUpdate != null);

                } else {
                    scanner = new Scanner(file);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
        Random random = new Random();
        for (int i = 0; i < mySQLTable.getNumberOfRows(); i++){
            String stringToUpdate = words.get(random.nextInt(words.size() - 1));
            mySQLTable.updateRow(stringToUpdate, rule.getSubstitute().getColumn(), i);
        }

    }
}
