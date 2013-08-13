package pearson.Readers;

import noNamespace.Rule;
import noNamespace.SubstitutionActionType;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pearson.Database.SQL.Database;
import pearson.Utilities.StackTrace;

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

        if (actionType == SubstitutionActionType.SET_FROM_FILE) {

            File selectedFile = new File(rule.getSubstitute().getFilePath());

            mySQLTable.getConnectionConfig().setDefaultDatabase(database);
            disableConstraints();
            setFromFile(selectedFile);

        } else if (actionType == SubstitutionActionType.SET_TO_RANDOM) {

            int stringLength = rule.getSubstitute().getNumericValue().intValue();

            mySQLTable.getConnectionConfig().setDefaultDatabase(database);
            disableConstraints();
            setToRandom(stringLength);

        } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {

            String setToString = rule.getSubstitute().getStringValue1();

            mySQLTable.getConnectionConfig().setDefaultDatabase(database);
            disableConstraints();
            setToValue(setToString);

        } else if (actionType == SubstitutionActionType.SET_TO_NULL) {

            mySQLTable.getConnectionConfig().setDefaultDatabase(database);
            disableConstraints();
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
            int index = 0;
            // if there are still words inside the scanner
            while (scanner.hasNext()) {

                String stringToUpdate = scanner.next();
                // if string to update contains a new line character(in case the word is at the end of line)
                if (stringToUpdate.contains("\n")) {
                    stringToUpdate.replaceAll("\n", "");
                }

                assert (stringToUpdate != null);
                words.put(index++, stringToUpdate);
                // if there are no more words inside scanner just start from the beginning of file
            }
            scanner.close();

        Random random = new Random();
        // get a random word from the list and update with it next row inside column
        for (int i = 0; i < mySQLTable.getNumberOfRows(); i++) {
            // we want to continue execution if we can't update row for some reason
            try {
                String stringToUpdate = words.get(random.nextInt(words.size() - 1));
                mySQLTable.updateRow(stringToUpdate, rule.getSubstitute().getColumn(), i);
            }
            catch (SQLException exc) {
                logger.error(exc + System.lineSeparator() + StackTrace.getStringFromStackTrace(exc));
            }
        }

    }
}
