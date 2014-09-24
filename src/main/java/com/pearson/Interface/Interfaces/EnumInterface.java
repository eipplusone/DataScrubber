package com.pearson.Interface.Interfaces;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Rules.SubstitutionTypes.DateSubstitutionTypes;
import com.pearson.Rules.SubstitutionTypes.NumericSubstitutionTypes;
import com.pearson.Rules.SubstitutionTypes.StringSubstitutionTypes;
import noNamespace.SubstitutionActionType;
import noNamespace.SubstitutionDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ruslan Kiselev
 *         Date: 7/12/13
 *         Time: 1:15 PM
 *         Project Name: DataScrubber
 */
public class EnumInterface {

    public static Logger logger = LoggerFactory.getLogger(EnumInterface.class.getName());

    public static SubstitutionActionType.Enum getSubstitutionActionType(String substitutionType){

        if(substitutionType.equals(StringSubstitutionTypes.SET_TO_NULL.toString()) ||
                substitutionType.equals(DateSubstitutionTypes.SET_TO_NULL.toString()) ||
                substitutionType.equals(NumericSubstitutionTypes.SET_TO_NULL.toString())){
            return SubstitutionActionType.SET_TO_NULL;
        }
        else if(substitutionType.equals(StringSubstitutionTypes.SET_TO_VALUE.toString()) ||
                substitutionType.equals(DateSubstitutionTypes.SET_TO_VALUE.toString())){
            return SubstitutionActionType.SET_TO_VALUE;
        }
        else if(substitutionType.equals(StringSubstitutionTypes.RANDOM_STRING.toString()) ||
                substitutionType.equals(DateSubstitutionTypes.RANDOM_DATE.toString()) ||
                substitutionType.equals(NumericSubstitutionTypes.RANDOM_NUMBER.toString())){
            return SubstitutionActionType.SET_TO_RANDOM;
        }
        else if(substitutionType.equals(StringSubstitutionTypes.FROM_A_LIST.toString())){
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
