package com.pearson.Utilities;

import java.util.ArrayList;

/**
 * @author Ruslan Kiselev
 *         Date: 6/14/13
 *         Time: 9:16 AM
 *         Project Name: DataScrubber
 *
 *         This class is implemented in order to make query building easier. It meant to be used with prepared statements.
 */
public class Query {

    String query;

    public Query update(String tableName){
        query = "UPDATE " + tableName;

        return this;
    }

    public Query select(String tableName) {
        query = "SELECT " + tableName;

        return this;
    }

    public Query select(ArrayList<String> columnNames){
        query = "SELECT";

        for (int i = 0; i < columnNames.size() -1; i++) {
            query = query.concat(" " + columnNames.get(i) + ",");
        }

        // concat the last column without the comma
        String concatString = columnNames.get(columnNames.size() - 1);
        query = query.concat(" " + concatString);

        return this;

    }


    public Query update(ArrayList<String> columnNames){
        query = "UPDATE";

        for (int i = 0; i < columnNames.size() -1; i++) {
            query = query.concat(" " + columnNames.get(i) + ",");
        }

        // concat the last column without the comma
        String concatString = columnNames.get(columnNames.size() - 1);
        query = query.concat(" " + concatString);

        return this;

    }

    public Query set(String columName){
        query = query + " SET " + columName + " = ?";
        return this;
    }



    public Query where(String columnName){
        query = query + " WHERE " + columnName + " = ?";
        return this;
    }

    @Override
    public String toString() {
        return this.query;
    }
}
