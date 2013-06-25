package com.pearson.SQL;

import com.pearson.Database.DatabaseManager;
import com.pearson.Database.MySQL.MySQLDataType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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





}
