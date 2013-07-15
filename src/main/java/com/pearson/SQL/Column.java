package com.pearson.SQL;

import com.pearson.Database.MySQL.MySQLDataType;

public class Column {

    public final String name;

    public boolean nullable = false;
    public MySQLDataType type = null;
    // long because longtext supports more characters than fit into int.
    // warning: might be memory problems because of this.
    public long character_max_length;
    public int numeric_precision;   // Total number of places
    public int numeric_scale;       // Number after decimal .#####
    boolean autoIncrement = false;

    public Column(String name) {
        this.name = name;
    }

    @Override
         public String toString() {
        return name + ":[" + nullable + "," + ((type == null) ? "null" : type) + "," + character_max_length + "," + numeric_precision + "," + numeric_scale + "]";
    }


    public String getName() {
        return name;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public MySQLDataType getType() {
        return type;
    }

    public void setType(MySQLDataType type) {
        this.type = type;
    }

    public long getCharacter_max_length() {
        return character_max_length;
    }

    public void setCharacter_max_length(long character_max_length) {
        this.character_max_length = character_max_length;
    }

    public int getNumeric_precision() {
        return numeric_precision;
    }

    public void setNumeric_precision(int numeric_precision) {
        this.numeric_precision = numeric_precision;
    }

    public int getNumeric_scale() {
        return numeric_scale;
    }

    public void setNumeric_scale(int numeric_scale) {
        this.numeric_scale = numeric_scale;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
}
