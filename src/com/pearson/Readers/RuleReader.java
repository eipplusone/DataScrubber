package pearson.Readers;

import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.SubstitutionDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pearson.Database.MySQL.MySQLTable;
import pearson.Database.SQL.Database;
import pearson.Utilities.StackTrace;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @author Ruslan Kiselev
 *         Date: 7/15/13
 *         Time: 3:42 PM
 *         Project Name: DataScrubber
 */
public class RuleReader implements Callable<Rule> {

    private static Logger logger = LoggerFactory.getLogger(RuleReader.class.getName());

    private Rule rule;
    private Database database;

    public RuleReader(Rule rule, Database database) {
        this.rule = rule;
        this.database = database;
    }

    public void run(Database database) throws SQLException, FileNotFoundException {
        if (rule.getRuleType() == RuleType.SHUFFLE) runShuffle(database);
        else if (rule.getRuleType() == RuleType.SUBSTITUTION) runSubstitution(database);
    }

    private void runShuffle(Database database) throws SQLException {

        MySQLTable mySQLTable = database.getTable(rule.getTarget());

        mySQLTable.getConnectionConfig().setDefaultDatabase(database);
        mySQLTable.getConnectionConfig().disableForeignKeyConstraints();
        mySQLTable.getConnectionConfig().disableUniqueChecks();

        if (mySQLTable.getAutoIncrementColumn() == null) mySQLTable.addAutoIncrementColumn();

        // begin shuffling
        for (int i = 0; i < mySQLTable.getNumberOfRows(); i++) {
            // if we are unable to swap rows - that's ok, continue with execution
            try {
                // two random rows to be swapped
                String[] columnNamesArray = rule.getShuffle().getColumnArray();
                ResultSet randomRow1 = mySQLTable.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
                ResultSet randomRow2 = mySQLTable.getRandomRow(new ArrayList(Arrays.asList(columnNamesArray)));
                mySQLTable.swap(randomRow1, randomRow2, new ArrayList(Arrays.asList(columnNamesArray)));
            } catch (SQLException exc) {
                logger.error(exc + System.lineSeparator() + StackTrace.getStringFromStackTrace(exc));
            }
        }
        // get the result set that has already been shuffled

        mySQLTable.deleteAutoIncrementColumn();

        mySQLTable.getConnectionConfig().enableForeignKeyConstraints();
        mySQLTable.getConnectionConfig().enableUniqueChecks();

        // clean all the resources
        mySQLTable.cleanResourses();
    }

    private void runSubstitution(Database database) throws FileNotFoundException, SQLException {

        SubstitutionDataType.Enum dataType = rule.getSubstitute().getSubstitutionDataType();

        if (dataType == SubstitutionDataType.DATE) new DateSubstitutionRuleReader(rule, database).call();
        else if (dataType == SubstitutionDataType.NUMERIC) new NumericSubstitutionRuleReader(rule, database).call();
        else if (dataType == SubstitutionDataType.STRING) new StringSubstitutionRuleReader(rule, database).call();
    }

    @Override
    public Rule call() throws SQLException, InterruptedException, FileNotFoundException {

        while (true) {

            if (!SetReader.isTableOccupied(rule.getTarget())) {
                // if we succeeded in adding table to the tablesOccupied(if no other thread submitted it before us)
                if (SetReader.tablesOccupied.add(rule.getTarget())) {
                    run(database);
                    break;
                }
            } else {
                Thread.sleep(500);
            }
        }

        return rule;
    }
}
