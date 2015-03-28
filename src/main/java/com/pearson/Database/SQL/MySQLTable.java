package com.pearson.Database.SQL;

import com.pearson.Database.DatabaseManager;
import com.pearson.Database.MySQL.MySQLTableWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


/**
 * Created by ruslankiselev on 10/26/14.
 * <p/>
 * May the force be with you.
 */
public class MySQLTable {
    private static Logger logger = LoggerFactory.getLogger(MySQLTable.class.getName());

    public HashMap<String, Column> columns = new HashMap();
    protected Database database;
    protected String tableName;

    public MySQLTable(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}