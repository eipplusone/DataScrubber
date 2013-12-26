package com.pearson.Readers;

import com.pearson.Database.SQL.Database;
import com.pearson.Utilities.StackTrace;
import noNamespace.Rule;
import noNamespace.SubstitutionActionType;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger logger = LoggerFactory.getLogger(StringSubstitutionRuleReader.class.getName());

    public StringSubstitutionRuleReader(Rule rule, Database database) {
        super(rule, database);
    }

    @Override
    public Rule call() throws SQLException, FileNotFoundException {

        SubstitutionActionType.Enum actionType = rule.getSubstitute().getSubstitutionActionType();
        prepareToRun();

        if (actionType == SubstitutionActionType.SET_FROM_FILE) {
            File selectedFile = new File(rule.getSubstitute().getFilePath());
            setFromFile(selectedFile);

        } else if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
            int stringLength = rule.getSubstitute().getNumericValue().intValue();
            setToRandom(stringLength);

        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {
            String setToString = rule.getSubstitute().getStringValue1();
            setToValue(setToString);

        } else if (actionType == SubstitutionActionType.SET_TO_NULL) {
            setToNull();
        }

        mySQLTable.cleanResourses();

        return rule;
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
    public void setFromFile(File file) throws SQLException, FileNotFoundException {

        HashMap<Integer, String> words = new HashMap();
        Scanner scanner = null;

        //build a hashmap of words to replace
        scanner = new Scanner(file);

        int i = 0;
        while (scanner.hasNext()) {

            String stringToUpdate = scanner.next();
            // if we reached the end of file make sure that no newline character is inside
            if (stringToUpdate.contains("\n")) {
                stringToUpdate.replaceAll("\n", "");
            }

            assert (stringToUpdate != null);
            words.put(i++, stringToUpdate);
        }


        Random random = new Random();
        // get a random word from the list and update with it next row inside column
        for (int j = 0; j < mySQLTable.getNumberOfRows(); j++) {
            String stringToUpdate = words.get(random.nextInt(words.size() - 1));
            mySQLTable.updateRow(stringToUpdate, rule.getSubstitute().getColumn(), j);
        }
    }
}
