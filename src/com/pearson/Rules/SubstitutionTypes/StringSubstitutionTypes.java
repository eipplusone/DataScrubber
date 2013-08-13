package pearson.Rules.SubstitutionTypes;

/**
 * @author Ruslan Kiselev
 *         Date: 6/14/13
 *         Time: 10:27 AM
 *         Project Name: DataScrubber
 */
public enum StringSubstitutionTypes {
    SET_TO_NULL, SET_TO_VALUE, RANDOM_STRING, FROM_A_LIST;

    public String toString(){
       if (this == SET_TO_NULL) return "Set To Null";
       else if (this == SET_TO_VALUE) return "Set To Value";
       else if (this == RANDOM_STRING) return "Set To Random String";
       else return "Set From A List";
    }
}
