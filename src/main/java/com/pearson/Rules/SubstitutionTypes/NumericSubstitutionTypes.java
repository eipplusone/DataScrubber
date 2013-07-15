package com.pearson.Rules.SubstitutionTypes;

/**
 * @author Ruslan Kiselev
 *         Date: 6/14/13
 *         Time: 10:27 AM
 *         Project Name: DataScrubber
 */
public enum NumericSubstitutionTypes {
    RANDOM_NUMBER, SET_TO_NULL;

   @Override
   public String toString(){
       if (this == RANDOM_NUMBER) return "Replace With Random Number";
       else return "Set To Null";
   }
}
