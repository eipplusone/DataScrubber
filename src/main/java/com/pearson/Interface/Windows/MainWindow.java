package com.pearson.Interface.Windows;

import com.pearson.Database.DatabaseManager;
import com.pearson.Interface.*;
import com.pearson.Interface.Interfaces.XMLInterface;
import com.pearson.Interface.Models.RulesTreeTableModel;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import com.pearson.Readers.SetReader;
import com.pearson.SQL.Database;
import noNamespace.Rule;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author UMA99J5
 */
public class MainWindow extends javax.swing.JFrame {

    private RulesTreeTableModel rulesInSetTreeModel;
    private javax.swing.JTabbedPane RulesInSetPane;
    private javax.swing.JTree RulesInSetTree;
    private org.jdesktop.swingx.JXTreeTable TestTree;
    private javax.swing.JMenuItem clearMaskingSetMenuButton;
    private javax.swing.JMenuItem deleteRuleMenuButton;
    private javax.swing.JMenuItem disableRuleMenuButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu maskingSetMenuItem;
    private javax.swing.JMenuItem newMaskingSetMenuButton;
    private JMenuItem runMaskingSetMenuButton;
    private javax.swing.JMenuItem newRuleMenuButton;
    private javax.swing.JMenuItem openMaskingSetMenuButton;
    private javax.swing.JMenu ruleMenuItem;
    private javax.swing.JMenuItem saveSetAsMenuButton;
    private javax.swing.JMenuItem saveSetMenuButton;
    private javax.swing.JPanel settings;
    private JMenuItem rightClickMenuItem;
    private JPopupMenu rulesInSetRightClickMenu;

    /**
     * Creates new form DataMaskFrontEndGUI
     */
    public MainWindow() {

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

        setMaskingSetLogic(false);

        com.pearson.Interface.UIManager.setMainWindow(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

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

        final MainWindow window = new MainWindow();

        // not allow user to close the window untill saved or explicitly discarded
        window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(XMLInterface.getXmlFile() != null && !XMLInterface.isFileSaved()){
                    Object[] options = {"Discard", "Cancel"};
                    int userChoice = JOptionPane.showOptionDialog(null, "Please Save Or Discard New Changes", "Are you sure you want to exit?",
                            JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                    if (userChoice == JOptionPane.YES_OPTION) window.dispose();
                }
                else window.dispose();
            }
        });
        window.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        RulesInSetPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        RulesInSetTree = new javax.swing.JTree();
        settings = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();

        TestTree = new org.jdesktop.swingx.JXTreeTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        maskingSetMenuItem = new javax.swing.JMenu();
        newMaskingSetMenuButton = new javax.swing.JMenuItem();
        openMaskingSetMenuButton = new javax.swing.JMenuItem();

        saveSetMenuButton = new javax.swing.JMenuItem();
        saveSetAsMenuButton = new javax.swing.JMenuItem();
        ruleMenuItem = new javax.swing.JMenu();
        newRuleMenuButton = new javax.swing.JMenuItem();
        deleteRuleMenuButton = new javax.swing.JMenuItem();
        disableRuleMenuButton = new javax.swing.JMenuItem();
        rulesInSetRightClickMenu = new JPopupMenu();
        runMaskingSetMenuButton = new JMenuItem();

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

        RulesInSetTree.setModel(rulesInSetTreeModel);
        jScrollPane1.setViewportView(RulesInSetTree);
        jScrollPane1.setViewportView(TestTree);
        RulesInSetPane.addTab("Rules In Set", jScrollPane1);

        jCheckBox1.setText("New Disable FK Constraints Rule...");

        jCheckBox3.setText("New Disable Triggers Rule...");

        javax.swing.GroupLayout settingsLayout = new javax.swing.GroupLayout(settings);
        settings.setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
                settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(settingsLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox3)
                                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(280, Short.MAX_VALUE))
        );
        settingsLayout.setVerticalGroup(
                settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(settingsLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jCheckBox1)
                                .addGap(23, 23, 23)
                                .addComponent(jCheckBox3)
                                .addContainerGap(417, Short.MAX_VALUE))
        );

        RulesInSetPane.addTab("Settings", settings);


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

        jMenuBar1.add(maskingSetMenuItem);

        ruleMenuItem.setText("Rule");

        newRuleMenuButton.setText("New Rule...");
        newRuleMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRuleMenuButtonActionPerformed(evt);
            }
        });
        ruleMenuItem.add(newRuleMenuButton);


        jMenuBar1.add(ruleMenuItem);

        setJMenuBar(jMenuBar1);

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void runMaskingSetMenuButtonActionPerformed(ActionEvent e) {

        DatabaseConnectionInfoWindow connectionInfoWindow = new DatabaseConnectionInfoWindow();
        connectionInfoWindow.setVisible(true);

        // if the window was just closed
        if (connectionInfoWindow.getReturnValue() == com.pearson.Interface.UIManager.CLOSED) return;

        Database database = null;
        try {
            database = new Database(com.pearson.Interface.UIManager.getDefaultSchema(),
                    com.pearson.Interface.UIManager.getUsername(),
                    com.pearson.Interface.UIManager.getPassword(),
                    "jdbc:mysql://" + com.pearson.Interface.UIManager.getUrl()
                            + ":" + com.pearson.Interface.UIManager.getPort());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        SetReader setReader = new SetReader(XMLInterface.getSetDocument(), database);
        setReader.run(database);
    }

    private void saveSetMenuButtonActionPerformed(ActionEvent evt) {

        // if we have not chosen save directory yet
        if (XMLInterface.getXmlFile() == null) {
            saveAsFile();
            return;
        }

        try {
            XMLInterface.saveCurrentFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSetAsMenuButtonActionPerformed(ActionEvent evt) {

        saveAsFile();
    }

    private void saveAsFile() {

        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);


        int returnOption = chooser.showSaveDialog(null);
        if (returnOption == JFileChooser.APPROVE_OPTION) {
            XMLInterface.setXMLFile(chooser.getSelectedFile());
            RulesInSetPane.setTitleAt(0, XMLInterface.getXmlFile().getName());
        }

        try {
            XMLInterface.saveCurrentFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewDependentRuleRightClickMenuActionPerformed(ActionEvent evt) {

        int row = TestTree.getSelectedRow();
        String ruleID = TestTree.getValueAt(row, RulesTreeTableModel.RULE_ID_COLUMN).toString();

        Rule newRule = XMLInterface.getRule(ruleID);

        if (!newRule.isSetDependencies()) {
            XMLInterface.addDependencyToRule(newRule);
        }

        com.pearson.Interface.UIManager.setParentRule(newRule);
        DatabaseConnectionInfoWindow newWindow = new DatabaseConnectionInfoWindow();
        newWindow.setVisible(true);
        CreateNewRuleWindow newRuleWindow = new CreateNewRuleWindow();
        newRuleWindow.setVisible(true);
    }

    private void deleteRightClickMenuActionPerformed(ActionEvent evt) {

        int row = TestTree.getSelectedRow();
        String ruleID = TestTree.getValueAt(row, RulesTreeTableModel.RULE_ID_COLUMN).toString();

        Rule ruleToDelete = XMLInterface.getRule(ruleID);

        if (!XMLInterface.isLeaf(ruleToDelete)) {
            Object[] options = {"Delete all child rules", "Cancel"};
            int userChoice = JOptionPane.showOptionDialog(null, "Deleting parent rule will result in deleting all child rules." +
                    " Please choose one of the following", "Are you sure you want to delete this rule?",
                    JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

            if (userChoice != JOptionPane.YES_OPTION) {
                return;
            }
        }

        XMLInterface.removeRule(ruleToDelete);

        try {
            XMLInterface.saveCurrentFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateTreeModel();
    }

    private void newMaskingSetMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {

        // need to do checks if a masking set has already been created and if the user wants
        // to save it
        if (XMLInterface.getXmlFile() != null && !XMLInterface.isFileSaved()) {
            Object[] options = {"Save", "Discard", "Cancel"};
            int saveOption = JOptionPane.showOptionDialog(null, "Please save or discard your changes", "Create New Masking Set", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            if (saveOption == JOptionPane.YES_OPTION) {
                saveAsFile();
            } else if (saveOption == JOptionPane.NO_OPTION) {
                XMLInterface.createNewFile();
                RulesInSetPane.setTitleAt(0, "New Masking Set");
                updateTreeModel();
            }
        } else {
            XMLInterface.createNewFile();
            TestTree.setTreeTableModel(new RulesTreeTableModel());

            setMaskingSetLogic(true);

            RulesInSetPane.setTitleAt(0, "New Masking Set");
        }
    }

    // openmskset
    private void openMaskingSetMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // create a file chooser

        if (XMLInterface.getXmlFile() != null && !XMLInterface.isFileSaved()) {
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

        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(openMaskingSetMenuButton);
        File openFile = null;

        if (evt.getSource() == openMaskingSetMenuButton) {
            //handle open button action

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                openFile = fc.getSelectedFile();

                XMLInterface.setXMLFile(openFile);

                LinkedList<String> columnNames = new LinkedList<>();
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

    }

    private void newRuleMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {

        DatabaseConnectionInfoWindow iw = new DatabaseConnectionInfoWindow();
        iw.setVisible(true);
        CreateNewRuleWindow newRuleWindow = new CreateNewRuleWindow();
        newRuleWindow.setVisible(true);

    }

    private void maskingSetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void setMaskingSetLogic(boolean isVisible) {

        RulesInSetPane.setVisible(isVisible);
        ruleMenuItem.setEnabled(isVisible);
        saveSetMenuButton.setEnabled(isVisible);
        saveSetAsMenuButton.setEnabled(isVisible);
        runMaskingSetMenuButton.setEnabled(isVisible);
    }

    /**
     * Updates the rules in set view
     */
    public void updateTreeModel() {

        rulesInSetTreeModel = new RulesTreeTableModel(XMLInterface.getRulesTree());
        TestTree.setTreeTableModel(rulesInSetTreeModel);
        TestTree.expandAll();
    }

}

