/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.Interface;

import com.pearson.Interface.Windows.MainWindow;
import noNamespace.Rule;

/**
 *
 * @author Ruslan Kiselev
 */
public class UIManager {

    public static final int CLOSED = -1;
    private static MainWindow mainWindow;
    private static String username = "dbadmin";
    private static String password = "Pw123";
    private static String url = "10.25.98.121";
    private static String defaultSchema = "core";
    private static String port = null;
    private static boolean userEnteredLogInInformation = true;
    private static Rule parentRule;

    static public MainWindow getMainWindow() {
        return mainWindow;
    }

    static public void setMainWindow(MainWindow mainWindowLink) {
        mainWindow = mainWindowLink;
    }

    private UIManager() {
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UIManager.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UIManager.password = password;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        UIManager.url = url;
    }

    public static String getDefaultSchema() {
        return defaultSchema;
    }

    public static void setDefaultSchema(String defaultSchema) {
        UIManager.defaultSchema = defaultSchema;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        UIManager.port = port;
    }


    public static void update() {
        mainWindow.updateTreeModel();
    }

    public static void setUserEnteredLogInInformation(boolean b) {
        userEnteredLogInInformation = true;
    }

    public static boolean getUserEnteredLogInInformation() {
        return userEnteredLogInInformation;
    }

    /**
     * Returns the parent rule if a dependent rule was specified.
     * @return
     */
    public static Rule getParentRule() {

        // since we allow only one window to be open, there is only one rule in
        // construction
        parentRule = null;

        return parentRule;
    }

    public static void setParentRule(Rule parentRule_){
        parentRule = parentRule_;
    }
}
