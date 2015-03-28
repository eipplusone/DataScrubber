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
 *
 * @author Ruslan Kiselev
 *         <p/>
 *         Date: 08/31/2013
 */

public class UIManager {

    public static Logger logger = LoggerFactory.getLogger(UIManager.class.getName());

    public static final int CLOSED = -1;
    private static DatabaseConnectionInfo currentConnection;
    private static MainWindow mainWindow;
    private static boolean userEnteredLogInInformation = true;
    private static Rule parentRule;

    static public MainWindow getMainWindow() {
        return mainWindow;
    }

    static public void setMainWindow(MainWindow mainWindowLink) {
        mainWindow = mainWindowLink;
    }

    static public DatabaseConnectionInfo getCurrentConnection() {
        return currentConnection;
    }

    static public void setCurrentConnection(DatabaseConnectionInfo currentConnection) {
        UIManager.currentConnection = currentConnection;
    }

    // only one UIManager for application
    private UIManager() {
    }

    public static void update() {
        mainWindow.updateTreeModel();
    }

    public static boolean isUserInformationSet() {
        return currentConnection != null;
    }

    /**
     * Returns the parent rule if a dependent rule was specified.
     *
     * @return
     */
    public static Rule getParentRule() {
        // since we allow only one window to be open, there is only one rule in
        // construction
        Rule ruleToReturn = parentRule;
        parentRule = null;

        return ruleToReturn;
    }

    public static void setParentRule(Rule parentRule_) {
        parentRule = parentRule_;
    }

    public static void cleanConnectionInformation() {
        currentConnection = null;
    }
}
