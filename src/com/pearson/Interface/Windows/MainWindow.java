// todo : clean off database connection window and make it ask for connection information and when it does so
// save for the current session and don't ask for the information again.

// for database connection make sure that when you cancel databse connection window you return a value that can be checked in the main window
// so we know that user cancelled window and don't bring new rule window if so. Yo.
package pearson.Interface.Windows;

import pearson.Database.SQL.Database;
import pearson.Interface.Interfaces.XMLInterface;
import pearson.Interface.Windows.Models.RulesTreeTableModel;
import pearson.Readers.SetReader;
import pearson.Utilities.CleanUp;
import pearson.Utilities.StackTrace;
import noNamespace.Rule;
import org.jdesktop.swingx.JXTreeTable;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

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

    private RulesTreeTableModel rulesInSetTreeModel = new RulesTreeTableModel();
    private javax.swing.JTabbedPane RulesInSetPane = new JTabbedPane();
    private javax.swing.JTree RulesInSetTree = new JTree();
    private org.jdesktop.swingx.JXTreeTable TestTree = new JXTreeTable();
    private javax.swing.JCheckBox jCheckBox1 = new JCheckBox();
    private javax.swing.JCheckBox jCheckBox3 = new JCheckBox();
    private javax.swing.JMenuBar topMenuBar = new JMenuBar();
    private javax.swing.JPanel jPanel1 = new JPanel();
    private javax.swing.JScrollPane jScrollPane1 = new JScrollPane();
    private javax.swing.JMenu maskingSetMenuItem = new JMenu();
    private javax.swing.JMenuItem newMaskingSetMenuButton = new JMenuItem();
    private JMenuItem runMaskingSetMenuButton = new JMenuItem();
    private javax.swing.JMenuItem newRuleMenuButton = new JMenuItem();
    private javax.swing.JMenuItem openMaskingSetMenuButton = new JMenuItem();
    private javax.swing.JMenu ruleMenuItem = new JMenu();
    private JMenu connectionMenuItem = new JMenu();
    private javax.swing.JMenuItem saveSetAsMenuButton = new JMenuItem();
    private javax.swing.JMenuItem saveSetMenuButton = new JMenuItem();
    private javax.swing.JPanel settings = new JPanel();
    private JMenuItem rightClickMenuItem = new JMenuItem();
    private JPopupMenu rulesInSetRightClickMenu = new JPopupMenu();
    private JMenuItem setConnectionMenuButton = new JMenuItem();
    private JMenuItem disconnectMenuButton = new JMenuItem();
    private JMenuItem cleanUpMenuButton = new JMenuItem();

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

        pearson.Interface.UIManager.setMainWindow(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        final MainWindow window = new MainWindow();

        // not allow user to close the window until saved or explicitly discarded
        window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (XMLInterface.getXmlFile() != null && !XMLInterface.isFileSaved()) {
                    Object[] options = {"Discard", "Cancel"};
                    int userChoice = JOptionPane.showOptionDialog(null, "Please Save Or Discard New Changes", "Are you sure you want to exit?",
                            JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                    if (userChoice == JOptionPane.YES_OPTION) window.dispose();
                } else window.dispose();
            }
        });
        window.setVisible(true);
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

        topMenuBar.add(maskingSetMenuItem);
        topMenuBar.add(ruleMenuItem);
        topMenuBar.add(connectionMenuItem);

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

        cleanUpMenuButton.setText("Set Connection");
        cleanUpMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanUpMenuButtonActionPerformed(e);
            }
        });
        connectionMenuItem.add(cleanUpMenuButton);

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cleanUpMenuButtonActionPerformed(ActionEvent e) {
        CleanUp.fixTriggers();

        try {
            CleanUp.deleteRowId();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void disconnectMenuButtonActionPerformed(ActionEvent e) {

        pearson.Interface.UIManager.cleanConnectionInformation();
        JOptionPane.showMessageDialog(null, "Connection has been closed");

        disconnectMenuButton.setEnabled(false);
    }

    private void setConnectionMenuButtonActionPerformed(ActionEvent e) {

        DatabaseConnectionInfoWindow connectionInfoWindow = new DatabaseConnectionInfoWindow();

        if (!pearson.Interface.UIManager.isUserInformationSet()) {
            connectionInfoWindow.setVisible(true);
        }

        if(connectionInfoWindow.getReturnValue() == pearson.Interface.UIManager.CLOSED) return;

        disconnectMenuButton.setEnabled(true);

    }

    private void runMaskingSetMenuButtonActionPerformed(ActionEvent e) {

        setConnectionMenuButtonActionPerformed(e);

        Database database = null;
        try {
            database = new Database(pearson.Interface.UIManager.getDefaultSchema(),
                    pearson.Interface.UIManager.getUsername(),
                    pearson.Interface.UIManager.getPassword(),
                    "jdbc:mysql://" + pearson.Interface.UIManager.getUrl()
                            + ":" + pearson.Interface.UIManager.getPort());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        SetReader setReader = new SetReader(XMLInterface.getSetDocument(), database);
        setReader.run(50);

        try {
            database.cleanUp();
        } catch (SQLException exc) {
            logger.error(exc + System.lineSeparator() + StackTrace.getStringFromStackTrace(exc));
        }
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
            File newFile = chooser.getSelectedFile();
            if (!newFile.exists()) {
                try {
                    newFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            XMLInterface.setXMLFile(newFile);
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

        pearson.Interface.UIManager.setParentRule(newRule);

        DatabaseConnectionInfoWindow iw = new DatabaseConnectionInfoWindow();

        if (!pearson.Interface.UIManager.isUserInformationSet()) {
            iw.setVisible(true);
        }
        if (!(iw.getReturnValue() == pearson.Interface.UIManager.CLOSED)) {
            CreateNewRuleWindow newRuleWindow = new CreateNewRuleWindow();
            newRuleWindow.setVisible(true);
        }
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

    }

    private void newRuleMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {

        DatabaseConnectionInfoWindow iw = new DatabaseConnectionInfoWindow();

        if (!pearson.Interface.UIManager.isUserInformationSet()) {
            iw.setVisible(true);
        }

        if(!(iw.getReturnValue() == pearson.Interface.UIManager.CLOSED)) {
            CreateNewRuleWindow newRuleWindow = new CreateNewRuleWindow();
            newRuleWindow.setVisible(true);
        }

        disconnectMenuButton.setEnabled(true);
    }

    private void maskingSetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void setMaskingSetLogic(boolean isVisible) {

        RulesInSetPane.setVisible(isVisible);
        ruleMenuItem.setEnabled(isVisible);
        saveSetMenuButton.setEnabled(isVisible);
        saveSetAsMenuButton.setEnabled(isVisible);
        connectionMenuItem.setEnabled(isVisible);
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

