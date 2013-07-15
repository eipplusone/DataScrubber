package com.pearson.Readers;

import com.pearson.SQL.Database;

import java.sql.SQLException;

/**
 * @author Ruslan Kiselev
 *         Date: 7/15/13
 *         Time: 3:00 PM
 *         Project Name: DataScrubber
 */
public interface Reader {

    public void run(Database database) throws SQLException;

}
