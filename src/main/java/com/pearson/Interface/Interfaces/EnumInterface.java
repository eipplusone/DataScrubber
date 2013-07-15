package com.pearson.Interface.Interfaces;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Rules.SubstitutionTypes.DateSubstitutionTypes;
import com.pearson.Rules.SubstitutionTypes.NumericSubstitutionTypes;
import com.pearson.Rules.SubstitutionTypes.StringSubstitutionTypes;
import noNamespace.SubstitutionActionType;
import noNamespace.SubstitutionDataType;

/**
 * @author Ruslan Kiselev
 *         Date: 7/12/13
 *         Time: 1:15 PM
 *         Project Name: DataScrubber
 */
public class EnumInterface {

    public static SubstitutionActionType.Enum getSubstitutionActionType(String substitutionType){

        if(substitutionType.equals(StringSubstitutionTypes.SET_TO_NULL) ||
                substitutionType.equals(DateSubstitutionTypes.SET_TO_NULL) ||
                substitutionType.equals(NumericSubstitutionTypes.SET_TO_NULL)){
            return SubstitutionActionType.SET_TO_NULL;
        }
        else if(substitutionType.equals(StringSubstitutionTypes.SET_TO_VALUE.toString())){
            return SubstitutionActionType.SET_TO_VALUE;
        }
        else if(substitutionType.equals(StringSubstitutionTypes.RANDOM_STRING) ||
                substitutionType.equals(DateSubstitutionTypes.RANDOM_DATE_WITHING_RANGE) ||
                substitutionType.equals(NumericSubstitutionTypes.RANDOM_NUMBER)){
            return SubstitutionActionType.SET_TO_RANDOM;
        }
        else if(substitutionType.equals(StringSubstitutionTypes.FROM_A_LIST)){
            return SubstitutionActionType.SET_FROM_FILE;
        }
        else {
            throw new IllegalArgumentException("Not Part of Substitution Type: " + substitutionType);
        }
    }

    public static SubstitutionDataType.Enum getSubstitutionDataType(MySQLDataType type) {

        if(MySQLDataType.isDateType(type)) return SubstitutionDataType.DATE;
        else if (MySQLDataType.isNumericType(type)) return SubstitutionDataType.NUMERIC;
        else if (MySQLDataType.isStringType(type)) return SubstitutionDataType.STRING;
        else throw new IllegalArgumentException("Data Type is not supported : " + type.toString());
    }
}
