package com.pearson.Database.SQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * @author Ruslan Kiselev
 *         Date: 6/19/13
 *         Time: 11:54 AM
 *         Project Name: DataScrubber
 */
public class Table {
    
    private static Logger logger = LoggerFactory.getLogger(Table.class.getName());

    public HashMap<String, Column> columns = new HashMap();
    protected String tableName;

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

}
