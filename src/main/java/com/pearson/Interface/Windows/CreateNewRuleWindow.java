/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.Interface.Windows;

import com.pearson.Utilities.StackTrace;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.SQLException;


/**
 * @author Ruslan Kiselev
 */
public class CreateNewRuleWindow extends JDialog {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(CreateNewRuleWindow.class.getName());

    private javax.swing.JButton cancelButton = new JButton();
    private javax.swing.JLabel chooseTypeLabel = new JLabel();
    private javax.swing.JLabel createLable = new JLabel();
    private javax.swing.JPanel jPanel1 = new JPanel();
    private javax.swing.JButton newShuffleRuleButton = new JButton();
    private javax.swing.JButton newSubstitutionRuleButton = new JButton();

    /**
     * Creates new form CreateNewRuleWindow
     */
    public CreateNewRuleWindow() {
        initComponents();
        setModalityType(ModalityType.APPLICATION_MODAL);
    }

    private void initComponents() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        createLable.setText("Create a New Masking Rule");

        chooseTypeLabel.setText("Choose the Masking Rule Type");

        newSubstitutionRuleButton.setText("New Substitution Rule...");
        newSubstitutionRuleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSubstitutionRuleButtonActionPerformed(evt);
            }
        });

        newShuffleRuleButton.setText("New Shuffle Rule...");
        newShuffleRuleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newShuffleRuleButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(132, 132, 132)
                                                .addComponent(createLable))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(124, 124, 124)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(newSubstitutionRuleButton)
                                                        .addComponent(chooseTypeLabel)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(136, 136, 136)
                                                .addComponent(newShuffleRuleButton))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(158, 158, 158)
                                                .addComponent(cancelButton)))
                                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(createLable)
                                .addGap(18, 18, 18)
                                .addComponent(chooseTypeLabel)
                                .addGap(18, 18, 18)
                                .addComponent(newSubstitutionRuleButton)
                                .addGap(18, 18, 18)
                                .addComponent(newShuffleRuleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(cancelButton)
                                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void newSubstitutionRuleButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // open the new substitution rule window

        NewSubstitutionRuleWindow rw = null;
        try {
            rw = new NewSubstitutionRuleWindow();
        } catch (SQLException ex) {
            logger.error("Couldn't create a new SubstitutionWindow");
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());
            logger.error(ex + System.lineSeparator() + StackTrace.getStringFromStackTrace(ex));
            System.exit(1);
        }
        dispose();
        rw.setVisible(true);
        rw.setDefaultCloseOperation(MainWindow.HIDE_ON_CLOSE);
    }

    private void newShuffleRuleButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // open the new shuffle rule window
        NewShuffleRuleWindow srw = null;
        try {
            srw = new NewShuffleRuleWindow();
        } catch (SQLException ex) {
            logger.error("Couldn't createa a new SubstitutionWindow");
            logger.error(ex + System.lineSeparator() + StackTrace.getStringFromStackTrace(ex));
        }
        dispose();
        srw.setVisible(true);
        srw.setDefaultCloseOperation(MainWindow.HIDE_ON_CLOSE);
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // action event for cancle button
        dispose();
    }
}
