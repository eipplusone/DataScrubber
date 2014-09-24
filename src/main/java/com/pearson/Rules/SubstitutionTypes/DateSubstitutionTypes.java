package com.pearson.Rules.SubstitutionTypes;

/**
 * @author Ruslan Kiselev
 *         Date: 6/14/13
 *         Time: 10:25 AM
 *         Project Name: DataScrubber
 */
public enum DateSubstitutionTypes {
    RANDOM_DATE, SET_TO_NULL, SET_TO_VALUE;

    public String toString(){
       if(this == RANDOM_DATE) return "Random Date from 1970/0/0";
       else if (this == SET_TO_VALUE) return "Set To Value(Format: mm/dd/yyyy)";
       else return "Set To Null";
    }
}
