package com.pearson.Utilities;

import com.pearson.SQL.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: UKISERU
 * Date: 6/7/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class SQLStatements {

    private static Logger logger = LoggerFactory.getLogger(SQLStatements.class.getName());

    public final static String GET_NUMBER_OF_ROWS = "SELECT COUNT(*) FROM ";
    public final static String GET_TABLES = "SHOW TABLES";
    public final static String GET_COLUMNS = "SELECT column_name, is_nullable, data_type, character_maximum_length, numeric_precision, extra, numeric_scale from information_schema.columns WHERE table_name = ?";
    public static final String GET_NUMBER_OF_ROWS_IN_A_COLUMN = "SELECT COUNT(?) FROM ";
    public static final String SET_CHARACTER_SET_CLIENT = "SET character_set_client = ?";


    /**
     * Creates a statement with a list of columns in a from of SELECT column1, column2, column3(no space after)
     * @param columnNames ArrayList of columns
     * @return String in the specified form
     */
    public static String appendColumns(ArrayList<String> columnNames){
        String selectRowQuery = "SELECT";

        for (int i = 0; i < columnNames.size() - 1; i++) {
            selectRowQuery = selectRowQuery.concat(" " + columnNames.get(i) + ",");
        }

        // concat the last column without the comma
        String concatString = columnNames.get(columnNames.size() - 1);
        selectRowQuery = selectRowQuery.concat(" " + concatString);

        return selectRowQuery;

    }
}
