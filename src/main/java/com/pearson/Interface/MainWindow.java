package com.pearson.Interface;

import com.pearson.Interface.Models.RulesTreeTableModel;
import com.pearson.Rules.Rule;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import noNamespace.MaskingSetDocument;
import noNamespace.MaskingSetDocument.MaskingSet;
import noNamespace.RulesDocument;
import noNamespace.RulesDocument.Rules;
import noNamespace.ShuffleRule;
import org.apache.xmlbeans.XmlException;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.tree.DefaultXTreeCellRenderer;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UMA99J5
 */
public class MainWindow extends javax.swing.JFrame {
    
    private RulesTreeTableModel rulesInSetTreeModel;
    MaskingSet maskingSet;
    XMLInterface xmlInterface;


    // public void windowClosed(WindowEvent e){
    //     dispose();
    //  }
    // private Component jMenuItem2;
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

        UIManager.setMainWindow(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RulesInSetTree.setModel(rulesInSetTreeModel);
        jScrollPane1.setViewportView(RulesInSetTree);
		jScrollPane1.setViewportView(TestTree);
        RulesInSetPane.addTab("Rules in Set", jScrollPane1);

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
        maskingSetMenuItem.add(saveSetMenuButton);

        saveSetAsMenuButton.setText("Save Set As...");
        maskingSetMenuItem.add(saveSetAsMenuButton);

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
    }// </editor-fold>//GEN-END:initComponents

    private void newMaskingSetMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMaskingSetMenuButtonActionPerformed

        // need to do checks if a masking set has already been created and if the user wants
        // to save it
        xmlInterface.createNewFile();
    }//GEN-LAST:event_newMaskingSetMenuButtonActionPerformed

    private void openMaskingSetMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMaskingSetMenuButtonActionPerformed
        // create a file chooser
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(openMaskingSetMenuButton);
        File file = null;

        if (evt.getSource() == openMaskingSetMenuButton) {
            //handle open button action

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();

            }
        }

            XMLInterface.setXMLFile(file);

        LinkedList<String> columnNames = new LinkedList<>();
        columnNames.add("Rule ID");
        columnNames.add("Rule Type");
        columnNames.add("Target");
        columnNames.add("Columns");

        updateTreeModel();
        TestTree.expandAll();

    }//GEN-LAST:event_openMaskingSetMenuButtonActionPerformed

    private void newRuleMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRuleMenuButtonActionPerformed

        DatabaseConnectionInfoWindow iw = new DatabaseConnectionInfoWindow();
        iw.setVisible(true);
        iw.setDefaultCloseOperation(MainWindow.HIDE_ON_CLOSE);

    }//GEN-LAST:event_newRuleMenuButtonActionPerformed
    private void maskingSetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maskingSetMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maskingSetMenuItemActionPerformed

    public JXTreeTable getTestTree() {
        return TestTree;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane RulesInSetPane;
    private javax.swing.JTree RulesInSetTree;
    private org.jdesktop.swingx.JXTreeTable TestTree;
    
    
    
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JMenu maskingSetMenuItem;
    private javax.swing.JMenuItem newMaskingSetMenuButton;
    private javax.swing.JMenuItem newRuleMenuButton;
    private javax.swing.JMenuItem openMaskingSetMenuButton;
    private javax.swing.JMenu ruleMenuItem;
    private javax.swing.JMenuItem saveSetAsMenuButton;
    private javax.swing.JMenuItem saveSetMenuButton;
    private javax.swing.JPanel settings;
    // End of variables declaration//GEN-END:variables

    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Updates mainWindow with the new rule information
     *
     * @param newRule rule to add to mainWindow UI
     *
     */
    public void updateRule(ShuffleRule newRule) {
    }

    /**
     * Updates mainWIndow with maskingSet from a file TODO - do a validity check
     * of the xml file
     *
     * @param updateFile file to update window with
     */
    public void updateFromFile(File updateFile) {

        MaskingSetDocument updateDocument = null;

        try {
            updateDocument = MaskingSetDocument.Factory.parse(updateFile);
        } catch (XmlException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        Rules rules = updateDocument.getMaskingSet().getRules();

    }

    /**
     * Parses through masking set file and returns a hashmap of rules with their
     * types
     *
     * @param xmlFile
     * @return
     */
    private HashMap<String, Rule> getRuleHashMap(File xmlFile) {
        
        MaskingSetDocument updateDocument = null;
        HashMap<String, Rule> returnMap = null;
        
        try {
            updateDocument = MaskingSetDocument.Factory.parse(xmlFile);
        } catch (XmlException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        Rules rules = updateDocument.getMaskingSet().getRules();
        
//        for (ShuffleRule rule: rules) {
//            //Rule ruleToAdd = new Rule(database.rule.getTarget()) {
//           // }
//            //returnMap.put(null, null)
//        }

        return null;
    }

    public RulesTreeTableModel getRulesInSetTreeModel() {
        return rulesInSetTreeModel;
    }

    /**
     * Updates the rules in set view
     */
    public void updateTreeModel() {

        rulesInSetTreeModel = new RulesTreeTableModel(xmlInterface.getRulesTree());
        TestTree.setTreeTableModel(rulesInSetTreeModel);
    }
}

