package com.pearson.Database.SQL;

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
    public boolean autoIncrement = false;

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

    public MySQLDataType getType() {
        return type;
    }
}
