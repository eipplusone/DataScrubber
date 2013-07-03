package com.pearson.Rules;


/**
 * Created with IntelliJ IDEA.
 * User: UKISERU
 * Date: 6/7/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Rule {


    String mySQLTable;

    public Rule(String mySQLTable){
        this.mySQLTable = mySQLTable;
    }



}
