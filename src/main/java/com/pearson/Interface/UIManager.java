/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.Interface;

import com.pearson.Interface.Windows.MainWindow;
import noNamespace.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project: DataScrubber
 * @author Ruslan Kiselev
 *
 * Date: 08/31/2013
 */

public class UIManager {

    public static Logger logger = LoggerFactory.getLogger(UIManager.class.getName());

    public static final int CLOSED = -1;
    private static MainWindow mainWindow;
    private static String username;
    private static String password;
    private static String url;
    private static String defaultSchema;
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

    public static void setUsername(String username_) {
        username = username_;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password_) {
        password = password_;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url_) {
        url = url_;
    }

    public static String getDefaultSchema() {
        return defaultSchema;
    }

    public static void setDefaultSchema(String defaultSchema_) {
        defaultSchema = defaultSchema_;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port_) {
        port = port_;
    }


    public static void update() {
        mainWindow.updateTreeModel();
    }

    public static boolean isUserInformationSet() {
        return username != null && password != null && url != null && defaultSchema != null;
    }

    /**
     * Returns the parent rule if a dependent rule was specified.
     * @return
     */
    public static Rule getParentRule() {

        // since we allow only one window to be open, there is only one rule in
        // construction
        Rule ruleToReturn = parentRule;
        parentRule = null;

        return ruleToReturn;
    }

    public static void setParentRule(Rule parentRule_){
        parentRule = parentRule_;
    }

    public static void cleanConnectionInformation() {
        username = null;
        password = null;
        url = null;
        defaultSchema = null;
        port = null;
    }
}
