/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.DataScrubber;


import com.pearson.Interface.Windows.MainWindow;

import java.sql.SQLException;

/**
 *
 * @author voltecrus
 */
public class Launcher {

    public static void main(String [] args) throws SQLException {
        
//        // I want to create a connection to the database and request data from a table and column using password and
//        // username from a file
//
//        // reading in log in information from a file
//
//        // is used to read xml file in order to get log in info
//        BufferedReader br;
//
//        // log in information
//        String username = null;
//        String password = null;
//        String url = null;
//        Database database;
//
//        try {
//            String currentLine;
//
//            File xmlFile = new File("login_info.pwd");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(xmlFile);
//
//
//            username = doc.getElementsByTagName("username").item(0).getTextContent();
//            password = doc.getElementsByTagName("password").item(0).getTextContent();
//            url = doc.getElementsByTagName("url").item(0).getTextContent();
//            //testing
//            //System.out.println(username + password + url);
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//        assert(username != null && password != null && url != null);
//        // make a new database so we can start accessing data from it
//        database = new Database("core", username, password, url); 
//        // fill it up with data
//        database.fillTables();
//
//
//
//        String tableName = "view";
//
//        StringSubstitution substitution = new StringSubstitution(database.getTable(tableName), "change_user");
//
//        try {
//            substitution.setToNull();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
////        ArrayList<String> arrayList = new ArrayList<>();
////
////        arrayList.add("change_user");
////
////        Shuffle shuffleRule = new Shuffle(arrayList,database.getTable(tableName));
////        try {
////            shuffleRule.run();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainWindow().setVisible(true);
//            }
//        });
        
        new MainWindow().setVisible(true);
    }




}

