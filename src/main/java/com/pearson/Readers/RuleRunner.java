package com.pearson.Readers;

import javax.management.modelmbean.XMLParseException;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Created by ruslankiselev on 12/6/14.
 * <p/>
 * May the force be with you.
 */
public interface RuleRunner {

    public void runRule() throws SQLException, FileNotFoundException, XMLParseException;
}
