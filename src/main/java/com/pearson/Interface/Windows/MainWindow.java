// todo : clean off database connection window and make it ask for connection information and when it does so
// save for the current session and don't ask for the information again.

// for database connection make sure that when you cancel databse connection window you return a value that can be checked in the main window
// so we know that user cancelled windo
package com.pearson.Interface.Windows;

import com.pearson.Interface.*;
import com.pearson.Interface.Interfaces.XMLInterface;
import com.pearson.Interface.Windows.Controllers.MainWindowController;
import com.pearson.Interface.Windows.Models.RulesTreeTableModel;
import com.pearson.Utilities.CleanUp;
import com.pearson.Utilities.Constants;
import com.pearson.Utilities.StackTrace;
import noNamespace.Rule;
import org.apache.xmlbeans.XmlException;
import org.jdesktop.swingx.JXTreeTable;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Project: DataScrubber
 *
 * @author Ruslan Kiselev
 *         <p/>
 *         Date: 08-30-2013
 */
public class MainWindow extends javax.swing.JFrame {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MainWindow.class.getName());
    MainWindowController mainWindowController = new MainWindowController();

    private JTabbedPane RulesInSetPane = new JTabbedPane();
    private JTree RulesInSetTree = new JTree();
    private org.jdesktop.swingx.JXTreeTable TestTree = new JXTreeTable();
    private JCheckBox jCheckBox1 = new JCheckBox();
    private JCheckBox jCheckBox3 = new JCheckBox();
    private JMenuBar topMenuBar = new JMenuBar();
    private JPanel jPanel1 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JMenu maskingSetMenuItem = new JMenu();
    private JMenuItem newMaskingSetMenuButton = new JMenuItem();
    private JMenuItem runMaskingSetMenuButton = new JMenuItem();
    private JMenuItem newRuleMenuButton = new JMenuItem();
    private JMenuItem openMaskingSetMenuButton = new JMenuItem();
    private JMenu ruleMenuItem = new JMenu();
    private JMenu connectionMenuItem = new JMenu();
    private JMenuItem saveSetAsMenuButton = new JMenuItem();
    private JMenuItem saveSetMenuButton = new JMenuItem();
    private JMenuItem rightClickMenuItem, disableMenuItem, enableMenuItem = new JMenuItem();
    private JPopupMenu rulesInSetRightClickMenu = new JPopupMenu();
    private JMenuItem setConnectionMenuButton = new JMenuItem();
    private JMenuItem disconnectMenuButton = new JMenuItem();
    private JMenuItem cleanUpMenuButton = new JMenuItem();

    /**
     * Creates new form DataMaskFrontEndGUI
     */
    public MainWindow(String[] args) {

        initComponents();
        TestTree.setComponentPopupMenu(new JPopupMenu("label"));

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) RulesInSetTree.getCellRenderer();

        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);

        TestTree.setLeafIcon(null);
        TestTree.setClosedIcon(null);
        TestTree.setOpenIcon(null);
        TestTree.getTreeSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        LinkedList<String> columnNames = new LinkedList();
        columnNames.add("Rule ID");
        columnNames.add("Rule Type");
        columnNames.add("Target");
        columnNames.add("Columns");

        setMaskingSetLogic(true);

        com.pearson.Interface.UIManager.setMainWindow(this);

        Set<String> argSet = new HashSet<String>(Arrays.asList(args));

        if (argSet.contains("debug")) {
            setFile(new File("testingMasterSet.xml"));
            Constants.IN_DEVELOPMENT = true;
            try {
                mainWindowController.runSet();
            } catch (IOException | SQLException e) {
                logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            }
        }
    }


    private void initComponents() {

        // initialise right click menu
        rightClickMenuItem = new JMenuItem("Create a new dependent rule");
        rightClickMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewDependentRuleRightClickMenuActionPerformed(evt);
            }
        });
        rulesInSetRightClickMenu.add(rightClickMenuItem);


        rightClickMenuItem = new JMenuItem("Delete");
        rightClickMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRightClickMenuActionPerformed(evt);
            }
        });
        rulesInSetRightClickMenu.add(rightClickMenuItem);

        disableMenuItem = new JMenuItem("Disable");
        disableMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disableRuleActionPerformed(evt);
            }
        });
        rulesInSetRightClickMenu.add(disableMenuItem);

        enableMenuItem = new JMenuItem("Enable");
        enableMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableRuleActionPerformed(evt);
            }
        });
        rulesInSetRightClickMenu.add(enableMenuItem);

        TestTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // if the right button was clicked and user clicked inside a rule
                if (SwingUtilities.isRightMouseButton(e) && TestTree.getPathForLocation(e.getX(), e.getY()) != null) {
                    //select the rule and display a context menu
                    TestTree.getTreeSelectionModel().setSelectionPath(TestTree.getPathForLocation(e.getX(), e.getY()));
                    rulesInSetRightClickMenu.show(TestTree, e.getX(), e.getY());
                }
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RulesInSetTree.setModel(mainWindowController.getRulesInSetTreeModel());
        jScrollPane1.setViewportView(RulesInSetTree);
        jScrollPane1.setViewportView(TestTree);
        RulesInSetPane.addTab("Rules In Set", jScrollPane1);

        jCheckBox1.setText("New Disable FK Constraints Rule...");

        jCheckBox3.setText("New Disable Triggers Rule...");


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(RulesInSetPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(RulesInSetPane))
        );

        maskingSetMenuItem.setText("Masking Set");
        maskingSetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maskingSetMenuItemActionPerformed(evt);
            }
        });

        newMaskingSetMenuButton.setText("New Masking Set...");
        newMaskingSetMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMaskingSetMenuButtonActionPerformed(evt);
            }
        });
        maskingSetMenuItem.add(newMaskingSetMenuButton);

        openMaskingSetMenuButton.setText("Open Masking Set...");
        openMaskingSetMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMaskingSetMenuButtonActionPerformed(evt);
            }
        });
        maskingSetMenuItem.add(openMaskingSetMenuButton);


        saveSetMenuButton.setText("Save Set...");
        saveSetMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSetMenuButtonActionPerformed(evt);
            }
        });
        maskingSetMenuItem.add(saveSetMenuButton);

        saveSetAsMenuButton.setText("Save Set As...");
        saveSetAsMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSetAsMenuButtonActionPerformed(evt);
            }
        });
        maskingSetMenuItem.add(saveSetAsMenuButton);

        maskingSetMenuItem.addSeparator();

        runMaskingSetMenuButton.setText("Run Set");
        runMaskingSetMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runMaskingSetMenuButtonActionPerformed(e);
            }
        });
        maskingSetMenuItem.add(runMaskingSetMenuButton);

        topMenuBar.add(maskingSetMenuItem);
        topMenuBar.add(ruleMenuItem);
        // This functionality isn't required in the base
//        topMenuBar.add(connectionMenuItem);

        ruleMenuItem.setText("Rule");
        connectionMenuItem.setText("Connection");

        newRuleMenuButton.setText("New Rule...");
        newRuleMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRuleMenuButtonActionPerformed(evt);
            }
        });
        ruleMenuItem.add(newRuleMenuButton);

        setConnectionMenuButton.setText("Set Connection");
        setConnectionMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setConnectionMenuButtonActionPerformed(e);
            }
        });
        connectionMenuItem.add(setConnectionMenuButton);

        disconnectMenuButton.setText("Disconnect");
        disconnectMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnectMenuButtonActionPerformed(e);
            }
        });
        connectionMenuItem.add(disconnectMenuButton);

        setJMenuBar(topMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())

        );

        pack();
        setSize(Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
        setLocationRelativeTo(null);
    } // please don't look here, it's so ugly

    private void disableRuleActionPerformed(ActionEvent evt) {
        int row = TestTree.getSelectedRow();
        String ruleID = TestTree.getValueAt(row, RulesTreeTableModel.RULE_ID_COLUMN).toString();
        XMLInterface.setDisabledRule(ruleID, true);
    }

    private void enableRuleActionPerformed(ActionEvent evt) {
        int row = TestTree.getSelectedRow();
        String ruleID = TestTree.getValueAt(row, RulesTreeTableModel.RULE_ID_COLUMN).toString();

        XMLInterface.setDisabledRule(ruleID, false);
    }

    private void cleanUpMenuButtonActionPerformed(ActionEvent e) {
        CleanUp.fixTriggers();

        try {
            CleanUp.deleteRowId();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void disconnectMenuButtonActionPerformed(ActionEvent e) {
        com.pearson.Interface.UIManager.cleanConnectionInformation();
        JOptionPane.showMessageDialog(null, "Connection has been closed");

        disconnectMenuButton.setEnabled(false);
    }

    private void setConnectionMenuButtonActionPerformed(ActionEvent e) {
        mainWindowController.setConnectionInformation();
    }

    private void runMaskingSetMenuButtonActionPerformed(ActionEvent e) {
        try {
            if (com.pearson.Interface.UIManager.isUserInformationSet()) {
                mainWindowController.runSet();
            }
        } catch (IOException | SQLException e1) {
            JOptionPane.showMessageDialog(null, "Please name and save the set before running");
            logger.error(e1 + System.lineSeparator() + StackTrace.getStringFromStackTrace(e1));
        }
    }

    private void saveSetMenuButtonActionPerformed(ActionEvent evt) {
        // if we have not chosen save directory yet
        if (XMLInterface.getXmlFile() == null) {
            saveAsFile();
        } else {
            try {
                mainWindowController.saveCurrentFile();
            } catch (IOException e) {
                logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            }
        }

    }

    private void saveSetAsMenuButtonActionPerformed(ActionEvent evt) {
        saveAsFile();
    }

    private void createNewDependentRuleRightClickMenuActionPerformed(ActionEvent evt) {

        int row = TestTree.getSelectedRow();
        String ruleID = TestTree.getValueAt(row, RulesTreeTableModel.RULE_ID_COLUMN).toString();

        Rule newRule = XMLInterface.getRule(ruleID);

        if (!newRule.isSetDependencies()) {
            XMLInterface.addDependencyToRule(newRule);
        }

        com.pearson.Interface.UIManager.setParentRule(newRule);

        DatabaseConnectionInfoWindow iw = new DatabaseConnectionInfoWindow();

        if (!com.pearson.Interface.UIManager.isUserInformationSet()) {
            iw.setVisible(true);
        }
        if (!(iw.getReturnValue() == com.pearson.Interface.UIManager.CLOSED)) {
            CreateNewRuleWindow newRuleWindow = new CreateNewRuleWindow();
            newRuleWindow.setVisible(true);
        }
    }

    private void deleteRightClickMenuActionPerformed(ActionEvent evt) {

        int row = TestTree.getSelectedRow();
        String ruleID = TestTree.getValueAt(row, RulesTreeTableModel.RULE_ID_COLUMN).toString();

        Rule ruleToDelete = mainWindowController.getRule(ruleID);

        if (!XMLInterface.isLeaf(ruleToDelete)) {
            Object[] options = {"Delete all child rules", "Cancel"};
            int userChoice = JOptionPane.showOptionDialog(null, "Deleting parent rule will result in deleting all child rules." +
                            " Please choose one of the following", "Are you sure you want to delete this rule?",
                    JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

            if (userChoice != JOptionPane.YES_OPTION) {
                return;
            }
        }

        mainWindowController.removeRule(ruleToDelete);

        updateTreeModel();
    }

    private void newMaskingSetMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {

        // need to do checks if a masking set has already been created and if the user wants
        // to save it
        if (mainWindowController.getXmlFile() != null && !mainWindowController.isFileSaved()) {
            Object[] options = {"Save", "Discard", "Cancel"};
            int saveOption = JOptionPane.showOptionDialog(null, "Please save or discard your changes", "Create New Masking Set", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            if (saveOption == JOptionPane.YES_OPTION) {
                saveAsFile();
            } else if (saveOption == JOptionPane.NO_OPTION) {
                mainWindowController.createNewFile("New Masking Set");
                RulesInSetPane.setTitleAt(0, "New Masking Set");
                updateTreeModel();
            }
        } else {
            mainWindowController.createNewFile("New Masking Set");
            TestTree.setTreeTableModel(new RulesTreeTableModel());

            setMaskingSetLogic(true);
            RulesInSetPane.setTitleAt(0, "New Masking Set");
        }
    }

    /**
     * Runs once the "Open Masking Set" button is pressed in the drop down meny of the main window.
     *
     * @param evt
     */
    private void openMaskingSetMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // create a file chooser

        if (mainWindowController.getXmlFile() != null && !mainWindowController.isFileSaved()) {
            Object[] options = {"Save", "Discard", "Cancel"};
            int saveOption = JOptionPane.showOptionDialog(null, "Please save or discard your changes", "Create New Masking Set", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            if (saveOption == JOptionPane.YES_OPTION) {
                saveAsFile();
                return;
            } else if (saveOption == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }


        // start off in the DataScrubber directory
        JFileChooser fc = new JFileChooser(Constants.WORKING_DIRECTORY);
        int returnVal = fc.showOpenDialog(openMaskingSetMenuButton);
        File openFile = null;

        if (evt.getSource() == openMaskingSetMenuButton) {
            //handle open button action

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                openFile = fc.getSelectedFile();
                setFile(openFile);
            }
        }

    }

    private void newRuleMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {

        DatabaseConnectionInfoWindow iw = new DatabaseConnectionInfoWindow();

        if (!com.pearson.Interface.UIManager.isUserInformationSet()) {
            iw.setVisible(true);
        }

        if (!(iw.getReturnValue() == com.pearson.Interface.UIManager.CLOSED)) {
            CreateNewRuleWindow newRuleWindow = new CreateNewRuleWindow();
            newRuleWindow.setVisible(true);
        }

        disconnectMenuButton.setEnabled(true);
    }

    private void maskingSetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * Sets the Visible parameter for all the GUI components
     *
     * @param isVisible
     */
    public void setMaskingSetLogic(boolean isVisible) {

        RulesInSetPane.setVisible(isVisible);
        ruleMenuItem.setEnabled(isVisible);
        saveSetMenuButton.setEnabled(isVisible);
        saveSetAsMenuButton.setEnabled(isVisible);
        connectionMenuItem.setEnabled(isVisible);
        runMaskingSetMenuButton.setEnabled(isVisible);
    }

    /**
     * Saves the currently open masking set. If no file is registered, it creates a new one and
     * saved the current set that is being worked on.
     */
    public void saveAsFile() {

        JFileChooser chooser = new JFileChooser(Constants.WORKING_DIRECTORY);

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File newFile = chooser.getSelectedFile();
            logger.debug("Saving to a file: " + newFile.getAbsolutePath());
            try {
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                mainWindowController.saveSetToAFile(newFile);
                mainWindowController.setXMLFile(newFile);
            } catch (IOException e) {
                logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            } catch (XmlException e) {
                logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            }
            RulesInSetPane.setTitleAt(0, XMLInterface.getXmlFile().getName());
        }
    }

    /**
     * Updates the rules in set view
     */
    public void updateTreeModel() {

        TestTree.setTreeTableModel(mainWindowController.getRulesInSetTreeModel());
        TestTree.expandAll();
    }

    public void setFile(File openFile) {
        // load the file
        try {
            mainWindowController.setXMLFile(openFile);
        } catch (XmlException e) {
            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            JOptionPane.showMessageDialog(this, "XML file is invalid." +
                    " Please load a valid file(see logs for details)");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File isn't available. Please see the logs");
            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
        }

        LinkedList<String> columnNames = new LinkedList();
        columnNames.add("Rule ID");
        columnNames.add("Rule Type");
        columnNames.add("Target");
        columnNames.add("Columns");

        setMaskingSetLogic(true);

        RulesInSetPane.setTitleAt(0, openFile.getName());
        updateTreeModel();
        TestTree.expandAll();
    }
}
