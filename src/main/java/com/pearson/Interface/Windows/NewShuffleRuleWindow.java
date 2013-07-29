package com.pearson.Interface.Windows;

import com.pearson.Interface.Interfaces.XMLInterface;
import com.pearson.Interface.Models.ItemsSelectedTableModel;
import com.pearson.SQL.Database;
import com.pearson.SQL.Column;
import noNamespace.*;
import com.pearson.SQL.MySQLTable;

import java.awt.event.ActionEvent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import noNamespace.RulesDocument.Rules;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author UMA99J5
 */
public class NewShuffleRuleWindow extends JDialog {

    Database database;
    ArrayList<String> tableNames = new ArrayList<>();
    DefaultListModel<String> listModel;
    boolean firstTimeSelected = true;

    // Variables declaration - do not modify                     
    private javax.swing.JList ColumnsSelectedList;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox columnsComboBox;
    private javax.swing.JButton createShuffleRuleButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel newShuffleRuleLabel;
    private javax.swing.JLabel pseudoCodeLabel;
    private javax.swing.JTextField pseudoCodeTextfield;
    private javax.swing.JLabel selectTableLabel;
    private javax.swing.JLabel shortDescriptionLabel;
    private javax.swing.JPanel shuffleColumnsPane;
    private javax.swing.JTabbedPane shuffleTabbedPane;
    private javax.swing.JCheckBox skipCheckBox;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JLabel tableSelected;
    private javax.swing.JTable tablesSelectedTable;

    /**
     * Default constructor; creates new form NewShuffleRule
     * <p/>
     * This method throws an exception, but by the time we have allowed the user
     * to enter that window he already should have passed checks on correct
     * connection
     */
    public NewShuffleRuleWindow() throws SQLException {

        database = new Database(com.pearson.Interface.UIManager.getDefaultSchema(), com.pearson.Interface.UIManager.getUsername(),
                com.pearson.Interface.UIManager.getPassword(), "jdbc:mysql://" + com.pearson.Interface.UIManager.getUrl()
                + ":" + com.pearson.Interface.UIManager.getPort());

        for (MySQLTable table : database.tables.values()) {
            tableNames.add(table.getTableName());
        }

        listModel = new DefaultListModel();
        initComponents();
        // allow only one table to be selected inside table list
        tablesSelectedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setModalityType(ModalityType.APPLICATION_MODAL);
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
            java.util.logging.Logger.getLogger(NewShuffleRuleWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewShuffleRuleWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewShuffleRuleWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewShuffleRuleWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new NewShuffleRuleWindow().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NewShuffleRuleWindow.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectTableLabel = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        tablesSelectedTable = new javax.swing.JTable();
        newShuffleRuleLabel = new javax.swing.JLabel();
        tableSelected = new javax.swing.JLabel();
        shortDescriptionLabel = new javax.swing.JLabel();
        shuffleTabbedPane = new javax.swing.JTabbedPane();
        shuffleColumnsPane = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ColumnsSelectedList = new javax.swing.JList();
        pseudoCodeLabel = new javax.swing.JLabel();
        pseudoCodeTextfield = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        createShuffleRuleButton = new javax.swing.JButton();
        columnsComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        selectTableLabel.setText("Select Table");

        tablesSelectedTable.setModel(new ItemsSelectedTableModel(tableNames));
        tablesSelectedTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablesSelectedTableMouseClicked(evt);
            }
        });
        tablesSelectedTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tablesSelectedTableComponentShown(evt);
            }
        });
        tableScrollPane.setViewportView(tablesSelectedTable);

        newShuffleRuleLabel.setText("New Shuffle Rule");
        shortDescriptionLabel.setText("Select Columns");

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        ColumnsSelectedList.setBorder(new javax.swing.border.MatteBorder(null));
        ColumnsSelectedList.setModel(listModel);
        jScrollPane1.setViewportView(ColumnsSelectedList);

        javax.swing.GroupLayout shuffleColumnsPaneLayout = new javax.swing.GroupLayout(shuffleColumnsPane);
        shuffleColumnsPane.setLayout(shuffleColumnsPaneLayout);
        shuffleColumnsPaneLayout.setHorizontalGroup(
                shuffleColumnsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(shuffleColumnsPaneLayout.createSequentialGroup()
                                .addComponent(deleteButton)
                                .addGap(0, 399, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        shuffleColumnsPaneLayout.setVerticalGroup(
                shuffleColumnsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(shuffleColumnsPaneLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        shuffleTabbedPane.addTab("List of Columns", shuffleColumnsPane);

        pseudoCodeLabel.setText("Pseudo code");

        /**enableWhereClauseCheckbox.setText("Enable Where Clause");
        enableWhereClauseCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableWhereClauseCheckboxActionPerformed(evt);
            }
        });

        whereClauseTextArea.setColumns(20);
        whereClauseTextArea.setRows(5);
        whereClauseTextArea.setText("where...\n");
        whereClauseScrollpane.setViewportView(whereClauseTextArea);
        whereClauseTextArea.setVisible(false);

        javax.swing.GroupLayout whereClausePaneLayout = new javax.swing.GroupLayout(whereClausePane);
        whereClausePane.setLayout(whereClausePaneLayout);
        whereClausePaneLayout.setHorizontalGroup(
                whereClausePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(whereClausePaneLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(whereClausePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(whereClauseScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                                        .addComponent(enableWhereClauseCheckbox)
                                        .addComponent(pseudoCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pseudoCodeTextfield))
                                .addContainerGap(38, Short.MAX_VALUE))
        );
        whereClausePaneLayout.setVerticalGroup(
                whereClausePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(whereClausePaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pseudoCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pseudoCodeTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(enableWhereClauseCheckbox)
                                .addGap(18, 18, 18)
                                .addComponent(whereClauseScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(28, Short.MAX_VALUE))
        );

        shuffleTabbedPane.addTab("Where Clause", whereClausePane);

        ignoreErrorsCheckbox.setText("Ignore listed errors");
        ignoreErrorsCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ignoreErrorsCheckboxActionPerformed(evt);
            }
        });

        errorTextArea.setColumns(20);
        errorTextArea.setRows(5);
        errorTextArea.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }

            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                errorTextAreaAncestorAdded(evt);
            }

            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        errorScrollPane.setViewportView(errorTextArea);
        errorTextArea.setVisible(false);

        skipCheckBox.setText("If error is ignored, skip all dependent rules");

        javax.swing.GroupLayout errorMgrPaneLayout = new javax.swing.GroupLayout(errorMgrPane);
        errorMgrPane.setLayout(errorMgrPaneLayout);
        errorMgrPaneLayout.setHorizontalGroup(
                errorMgrPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(errorMgrPaneLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(errorMgrPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(skipCheckBox)
                                        .addComponent(errorScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ignoreErrorsCheckbox))
                                .addContainerGap(212, Short.MAX_VALUE))
        );
        errorMgrPaneLayout.setVerticalGroup(
                errorMgrPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(errorMgrPaneLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(ignoreErrorsCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(errorScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(skipCheckBox)
                                .addContainerGap(139, Short.MAX_VALUE))
        );

        shuffleTabbedPane.addTab("Error Mgr", errorMgrPane); **/

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        createShuffleRuleButton.setText("Create Shuffle Rule");
        createShuffleRuleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createShuffleRuleButtonActionPerformed(evt);
            }
        });

//helpButton.setText("Help");

        columnsComboBox.setModel(new javax.swing.DefaultComboBoxModel(

        )
        );
        columnsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                columnsComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(selectTableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(497, 497, 497))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(251, 251, 251)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tableSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(newShuffleRuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(254, 254, 254)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(columnsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(shuffleTabbedPane)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGap(60, 60, 60)
                                                                                .addComponent(createShuffleRuleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGap(45, 45, 45)
                                                                        .addComponent(shortDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(117, 117, 117))))
        ))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(newShuffleRuleLabel)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(tableSelected))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(selectTableLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shortDescriptionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(columnsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(22, 22, 22)
                                                .addComponent(shuffleTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(createShuffleRuleButton)
        )));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //private void enableWhereClauseCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableWhereClauseCheckboxActionPerformed
        // TODO add your handling code here:
        //whereClauseTextArea.setVisible(enableWhereClauseCheckbox.isSelected());
   // }//GEN-LAST:event_enableWhereClauseCheckboxActionPerformed

    //private void errorTextAreaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_errorTextAreaAncestorAdded
        // TODO add your handling code here:
  //  }//GEN-LAST:event_errorTextAreaAncestorAdded

    private void columnsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_columnsComboBoxActionPerformed

        // mouse clicked on the tables selected triggers this event
        // we want to make sure not to update it
        if (!firstTimeSelected) {
            String column = (String) columnsComboBox.getSelectedItem();
                if(!listModel.contains(column)) listModel.addElement(column);
        }
    }//GEN-LAST:event_columnsComboBoxActionPerformed

    //private void ignoreErrorsCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ignoreErrorsCheckboxActionPerformed
        // TODO add your handling code here:
      //  errorTextArea.setVisible(ignoreErrorsCheckbox.isSelected());
   // }//GEN-LAST:event_ignoreErrorsCheckboxActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // Exit
        dispose();

    }//GEN-LAST:event_cancelButtonActionPerformed

    private void tablesSelectedTableComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tablesSelectedTableComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_tablesSelectedTableComponentShown

    private void tablesSelectedTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablesSelectedTableMouseClicked

        // need to check if a new table was selected for the first time
        // otherwise it triggers actionPerformed on comboBox for some reason
        firstTimeSelected = true;
        columnsComboBox.removeAllItems(); // remove columns from previoulsy selected table
        listModel.removeAllElements();

        int row = tablesSelectedTable.rowAtPoint(evt.getPoint());

        String tableSelected = tableNames.get(row);
        for (Column column : database.tables.get(tableSelected).columns.values()) {
            columnsComboBox.addItem(column.name);
        }
        firstTimeSelected = false;
    }//GEN-LAST:event_tablesSelectedTableMouseClicked

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        listModel.removeElement(ColumnsSelectedList.getSelectedValue().toString());
    }//GEN-LAST:event_deleteButtonActionPerformed

    // this code works only if we create a rule without being dependent on another rule todo version 1.0 make create new rule dependent on another rule
    private void createShuffleRuleButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_createShuffleRuleButtonActionPerformed

        ArrayList<String> columns = new ArrayList<>();

        // get all column names user has entered
        for (Object column : listModel.toArray()) {
            columns.add((String) column);
        }

        Rules rulesInSet = XMLInterface.getSetDocument().getMaskingSet().getRules();

        // build the rule according to information from this window
        Rule parentRule = com.pearson.Interface.UIManager.getParentRule();
        Rule newRule;
        // depending on whether is an independent rule or dependent
        if (parentRule == null) {
            newRule = rulesInSet.addNewRule();
            newRule.setId(rulesInSet.getRuleArray().length + "");
        }
        else {
            newRule = parentRule.getDependencies().addNewRule();
            newRule.setId(parentRule.getId().concat("-" + parentRule.getDependencies().getRuleArray().length) + "");
        }
        ShuffleRule newRuleShuffle = newRule.addNewShuffle();
        // add new columns
        for(String column : columns){
            newRuleShuffle.addColumn(column);
        }
        String targetTable = tableNames.get(tablesSelectedTable.getSelectedRow());
        newRule.setTarget(targetTable);
        // get the id of the parent rule and concat it with incremented number of rules inside parent rule dependency
        newRule.setRuleType(RuleType.SHUFFLE);

        com.pearson.Interface.UIManager.update();

        dispose();
    }//GEN-LAST:event_createShuffleRuleButtonActionPerformed
    // End of variables declaration                   
}
