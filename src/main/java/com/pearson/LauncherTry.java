package com.pearson;

import com.pearson.Interface.Windows.MainWindow;
import org.apache.commons.lang.math.RandomUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Ruslan Kiselev
 *         Date: 6/17/13
 *         Time: 4:48 PM
 *         Project Name: DataScrubber
 */
public class LauncherTry {

    public static void main(String[] args) {
        System.out.println(new Date(Math.abs(System.currentTimeMillis() - RandomUtils.nextLong())).toString());


    }

    /**
     *
     * @author voltecrus
     */
    public static class Launcher {

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

    public static class xmlParsing {


      public static void main(String argv[]) {

        try {


            File fXmlFile = new File("dataMaskerRules.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);


        doc.getDocumentElement().normalize();

        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("rule");

        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                System.out.println("rule id : " + eElement.getAttribute("id"));
                System.out.println("dependency : " + eElement.getElementsByTagName("dependency").item(0).getTextContent());
                System.out.println("column : " + eElement.getElementsByTagName("column").item(0).getTextContent());
                System.out.println("type : " + eElement.getElementsByTagName("type").item(0).getTextContent());


            }
        }
        } catch (Exception e) {
        e.printStackTrace();
        }
      }

    }
}
