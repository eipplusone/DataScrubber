package com.pearson.Rules;

import com.pearson.SQL.MySQLTable;

/**
 * Created with IntelliJ IDEA.
 * User: UKISERU
 * Date: 6/7/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Rule {


    MySQLTable mySQLTable;

    public Rule(MySQLTable mySQLTable){
        this.mySQLTable = mySQLTable;
    }



}
