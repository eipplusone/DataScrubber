/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.DataScrubber;


import com.pearson.Rules.Shuffle;
import com.pearson.Rules.StringSubstitution;
import com.pearson.SQL.Database;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author voltecrus
 */
public class Launcher {

    public static void main(String [] args) throws SQLException {
        // I want to create a connection to the database and request data from a table and column using password and
        // username from a file

        // reading in log in information from a file

        // is used to read xml file in order to get log in info
        BufferedReader br;

        // log in information
        String username = null;
        String password = null;
        String url = null;
        Database database;

        try {
            String currentLine;

            File xmlFile = new File("login_info.pwd");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);


            username = doc.getElementsByTagName("username").item(0).getTextContent();
            password = doc.getElementsByTagName("password").item(0).getTextContent();
            url = doc.getElementsByTagName("url").item(0).getTextContent();
            //testing
            //System.out.println(username + password + url);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        assert(username != null && password != null && url != null);
        // make a new database so we can start accessing data from it
        database = new Database("core", username, password, url); 
        // fill it up with data
        database.fillTables();



        String tableName = "view";

        StringSubstitution substitution = new StringSubstitution(database.getTable(tableName), "change_user");

        try {
            substitution.setToNull();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        ArrayList<String> arrayList = new ArrayList<>();
//
//        arrayList.add("change_user");
//
//        Shuffle shuffleRule = new Shuffle(arrayList,database.getTable(tableName));
//        try {
//            shuffleRule.run();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }




}

