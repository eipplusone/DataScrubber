package pearson.Database.MySQL;


public enum MySQLDataType {
    BIT, TINYINT, SMALLINT, MEDIUMINT, INT, BIGINT, DECIMAL, DEC, FLOAT, DOUBLE, //Numeric Types
    DATE, DATETIME, TIMESTAMP, TIME, YEAR, // Date and Time Types
    CHAR, VARCHAR, BINARY, VARBINARY, BLOB, LONGBLOB, TEXT, MEDIUMTEXT, LONGTEXT, ENUM, SET; // String Types

    public static boolean isNumericType(MySQLDataType type){
        if(type.equals(BIT) || type == TINYINT || type == SMALLINT || type == MEDIUMINT || type == INT || type == BIGINT || type == DECIMAL || type == DEC
                || type == FLOAT || type == DOUBLE) return true;
        else return false;
    }

    public static boolean isDateType(MySQLDataType type){
        if(type == DATE || type == DATETIME || type == TIMESTAMP || type == TIME || type == YEAR) return true;
        else return false;
    }

    // little cheating
    public static boolean isStringType(MySQLDataType type){
        if(!isDateType(type) && !isNumericType(type)) return true;
        else return false;
    }
}