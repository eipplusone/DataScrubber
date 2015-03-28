/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.Interface.Windows;

import com.pearson.Database.MySQL.MySQLDataType;
import com.pearson.Database.MySQL.MySQLTableWorker;
import com.pearson.Database.SQL.Column;
import com.pearson.Database.SQL.Database;
import com.pearson.Database.SQL.MySQLTable;
import com.pearson.Interface.*;
import com.pearson.Interface.Interfaces.EnumInterface;
import com.pearson.Interface.Interfaces.XMLInterface;
import com.pearson.Interface.Windows.Models.ItemsSelectedTableModel;
import com.pearson.Rules.SubstitutionTypes.DateSubstitutionTypes;
import com.pearson.Rules.SubstitutionTypes.NumericSubstitutionTypes;
import com.pearson.Rules.SubstitutionTypes.StringSubstitutionTypes;
import com.pearson.Utilities.Constants;
import com.pearson.Utilities.StackTrace;
import noNamespace.*;
import noNamespace.RulesDocument.Rules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * @author : Ruslan Kiselev
 */
public class NewSubstitutionRuleWindow extends JDialog {

    private static Logger logger = LoggerFactory.getLogger(NewSubstitutionRuleWindow.class.getName());

    /**
     * Creates new form NewSubstitutionRule
     */
    Database database;
    ArrayList<String> tableNames = new ArrayList();
    boolean isTriggersIsolated = true;
    boolean isTableSelected = false;
    String tableSelected;
    Column columnSelected;
    File setFromFile;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NewSubstitutionRuleLabel;
    private javax.swing.JButton browseButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel columnLabel;
    private javax.swing.JComboBox columnsComboBox;
    private javax.swing.JButton createSubstitutionRule;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JPanel selectOptionsPanel;
    private javax.swing.JLabel selectedColumnLabel;
    private javax.swing.JLabel selectedTypeOfSubstitutionLabel;
    private javax.swing.JLabel selectedValueLabel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JTable tablesSelectedTable;
    private javax.swing.JComboBox typeOfSubstitutionComboBox;
    private javax.swing.JLabel typeOfSubstitutionLabel;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JLabel valueOrBrowseLabel;
    private javax.swing.JTextField valueTextField;

    /**
     * Similar to new shuffle rule window, this method throws an exception,
     * by the time when users open the new substitution rule window,
     * database information has been passed over. *
     */

    public NewSubstitutionRuleWindow() throws SQLException {
        database = new Database(com.pearson.Interface.UIManager.getCurrentConnection());

        // end of preparing database structure
        for (MySQLTable table : database) {
            tableNames.add(table.getTableName());
        }

        initComponents();
        // set the third step be invisible for the user
        valueOrBrowseLabel.setVisible(false);
        valueTextField.setVisible(false);
        browseButton.setVisible(false);
        typeOfSubstitutionComboBox.setEnabled(false);
        columnsComboBox.setEnabled(false);
        tablesSelectedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.setModalityType(ModalityType.APPLICATION_MODAL);

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        buttonGroup1 = new javax.swing.ButtonGroup();
        selectOptionsPanel = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        tablesSelectedTable = new javax.swing.JTable();
        buttonsPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        createSubstitutionRule = new javax.swing.JButton();
        columnsComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        typeOfSubstitutionComboBox = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        valueTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        valueOrBrowseLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        NewSubstitutionRuleLabel = new javax.swing.JLabel();
        columnLabel = new javax.swing.JLabel();
        typeOfSubstitutionLabel = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        selectedColumnLabel = new javax.swing.JLabel();
        selectedTypeOfSubstitutionLabel = new javax.swing.JLabel();
        selectedValueLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        selectOptionsPanel.setLayout(new java.awt.GridBagLayout());

        tablesSelectedTable.setModel(new ItemsSelectedTableModel(tableNames));
        tablesSelectedTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablesSelectedTableMouseClicked(evt);
            }
        });
        tableScrollPane.setViewportView(tablesSelectedTable);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        createSubstitutionRule.setText("Create Substitution Rule");
        createSubstitutionRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createSubstitutionRuleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
                buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(createSubstitutionRule)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                .addComponent(cancelButton)
                                .addContainerGap())
        );
        buttonsPanelLayout.setVerticalGroup(
                buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(buttonsPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(createSubstitutionRule)))
        );

        columnsComboBox.setModel(new javax.swing.DefaultComboBoxModel());
        columnsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                columnsComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("2) Please Select Type Of Substitution");

        jLabel3.setText("1) Please Select A Column");

        typeOfSubstitutionComboBox.setModel(new javax.swing.DefaultComboBoxModel());
        typeOfSubstitutionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeOfSubstitutionComboBoxActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        valueTextField.setPreferredSize(columnsComboBox.getPreferredSize());
        valueTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valueTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(valueTextField);
        valueTextField.setVisible(true);

        browseButton.setText("Browse...");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });
        jPanel1.add(browseButton);
        valueTextField.setVisible(true);

        valueOrBrowseLabel.setText("ThirdStepLabel(Should be Hidden)");

        NewSubstitutionRuleLabel.setText("New Substitution Rule");

        columnLabel.setText("Column: ");

        typeOfSubstitutionLabel.setText("Type Of Substitution: ");

        valueLabel.setText("Value: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(selectOptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jSeparator1)
                                        .addComponent(typeOfSubstitutionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(valueOrBrowseLabel)
                                                        .addComponent(NewSubstitutionRuleLabel)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(typeOfSubstitutionLabel)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(selectedTypeOfSubstitutionLabel))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(valueLabel)
                                                                                .addGap(83, 83, 83)
                                                                                .addComponent(selectedValueLabel))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(columnLabel)
                                                                                .addGap(74, 74, 74)
                                                                                .addComponent(selectedColumnLabel))))
                                                        .addComponent(jLabel3))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(columnsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(columnsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(typeOfSubstitutionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(valueOrBrowseLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(23, 23, 23)
                                                .addComponent(selectOptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(NewSubstitutionRuleLabel)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(columnLabel)
                                                        .addComponent(selectedColumnLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(typeOfSubstitutionLabel)
                                                        .addComponent(selectedTypeOfSubstitutionLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(valueLabel)
                                                        .addComponent(selectedValueLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
                                .addContainerGap())
        );

        valueTextField.setVisible(true);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Exit
        dispose();
    }

    private void tablesSelectedTableMouseClicked(java.awt.event.MouseEvent evt) {
        // select table from the window
        isTriggersIsolated = true;
        isTableSelected = true;
        typeOfSubstitutionComboBox.setEnabled(false);

        columnsComboBox.removeAllItems();
        typeOfSubstitutionComboBox.removeAllItems();

        int row = tablesSelectedTable.rowAtPoint(evt.getPoint());
        tableSelected = tableNames.get(row);
        try {
            for (Column column : database.getTable(tableSelected).columns.values()) {
                columnsComboBox.addItem(column.name + "(" + column.getType() + ")");
            }
        } catch (SQLException e) {
            logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
        }
        isTriggersIsolated = false;
        columnsComboBox.setEnabled(true);
    }

    /**
     * Once the user has entered all the relevant info for creating a rule, this code runs.
     * @param evt
     */
    private void createSubstitutionRuleActionPerformed(java.awt.event.ActionEvent evt) {

        Rules rulesInSet = XMLInterface.getSetDocument().getMaskingSet().getRules();

        // build the rule according to information from this window
        // parentRule would be passed by the mainWindow in case it is a dependent rule
        Rule parentRule = com.pearson.Interface.UIManager.getParentRule();
        Rule newRule;

        // depending on whether is an independent rule or dependent
        if (parentRule == null) {
            newRule = rulesInSet.addNewRule();
            newRule.setId(rulesInSet.getRuleArray().length + "");
        } else {
            newRule = parentRule.getDependencies().addNewRule();
            newRule.setId(parentRule.getId().concat("-" + parentRule.getDependencies().getRuleArray().length) + "");
        }

        // Upload all the relevant info to the rule
        addRuleInformation(newRule);

        // let other windows know that masking set has change
        com.pearson.Interface.UIManager.update();

        try {
            database.cleanUp();
        } catch (SQLException e) {
            logger.debug("Weren't able to clean up database");
            logger.error(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
        }
        dispose();
    }

    /**
     * Runs when user decides which column he would like to scrub. Populates
     * the substitution types combo box.
     * @param evt
     */
    private void columnsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {

        // navigate user through window
        if (isTriggersIsolated) return;

        isTriggersIsolated = true;
        String columnSelectedString = columnsComboBox.getSelectedItem().toString();
        typeOfSubstitutionComboBox.removeAllItems();

        try {
            columnSelected = database.getTable(tableSelected).columns.get(
                    columnSelectedString.substring(0, columnSelectedString.indexOf("(")));
        } catch (SQLException e) {
            logger.debug(e + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
        }

        // populate the substitution type combo box depending on the type of column it is
        if (MySQLDataType.isNumericType(columnSelected.getType())) addNumericToSubstitutionType();
        else if (MySQLDataType.isDateType(columnSelected.getType())) addDateToSubstitutionType();
        else if (MySQLDataType.isStringType(columnSelected.getType())) addStringToSubstitutionType();

        // let the user know the selected column
        selectedColumnLabel.setText(columnSelectedString);
        typeOfSubstitutionComboBox.setEnabled(true);
        isTriggersIsolated = false;
    }

    private void addDateToSubstitutionType() {
        for (DateSubstitutionTypes type : DateSubstitutionTypes.values())
            typeOfSubstitutionComboBox.addItem(type.toString());
    }

    private void addNumericToSubstitutionType() {

        for (NumericSubstitutionTypes type : NumericSubstitutionTypes.values())
            typeOfSubstitutionComboBox.addItem(type.toString());
    }

    private void addStringToSubstitutionType() {

        for (StringSubstitutionTypes type : StringSubstitutionTypes.values())
            typeOfSubstitutionComboBox.addItem(type.toString());
    }

    private void addRuleInformation(Rule newRule) {

        SubstitutionRule newRuleSubstitution = newRule.addNewSubstitute();
        // add new column
        String columnString = columnsComboBox.getSelectedItem().toString().substring(0, columnsComboBox.getSelectedItem().toString().indexOf("("));
        newRuleSubstitution.setColumn(columnString);

        String targetTable = tableNames.get(tablesSelectedTable.getSelectedRow());
        newRule.setTarget(targetTable);
        newRule.setRuleType(RuleType.SUBSTITUTION);

        String substitutionType = typeOfSubstitutionComboBox.getSelectedItem().toString();
        newRule.getSubstitute().setSubstitutionActionType(EnumInterface.getSubstitutionActionType(substitutionType));
        newRule.getSubstitute().setSubstitutionDataType(EnumInterface.getSubstitutionDataType(columnSelected.getType()));

        setValueInformation(EnumInterface.getSubstitutionActionType(substitutionType), EnumInterface.getSubstitutionDataType(columnSelected.getType()), newRule);

    }

    private void setValueInformation(SubstitutionActionType.Enum actionType, SubstitutionDataType.Enum dateType, Rule newRule) {

        String value = valueTextField.getText();
        logger.debug("Received value from text field: " + value);

        if (dateType == SubstitutionDataType.DATE) {
            if (actionType == SubstitutionActionType.SET_TO_VALUE) {
                SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
                Date enteredDate;
                try {
                    enteredDate = format.parse(value);
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(this, "Invalid Date format. Please enter" +
                            "date in format mm/dd/yyyy");
                    return;
                }
                newRule.getSubstitute().setDateValue1(BigInteger.valueOf(enteredDate.getTime()));
            }
        } else if (dateType == SubstitutionDataType.NUMERIC) {
            if (actionType == SubstitutionActionType.SET_TO_VALUE) {
                newRule.getSubstitute().setNumericValue(BigDecimal.valueOf(Integer.parseInt(value)));
            }
        } else if (dateType == SubstitutionDataType.STRING) {
            if (actionType == SubstitutionActionType.SET_TO_RANDOM) {
                newRule.getSubstitute().setNumericValue(BigDecimal.valueOf(Integer.parseInt(value)));
            } else if (actionType == SubstitutionActionType.SET_TO_VALUE) {
                newRule.getSubstitute().setStringValue1(value);
            } else if (actionType == SubstitutionActionType.SET_FROM_FILE) {
                // since we displayed the text inside the label we can use it
                String filePathRaw = selectedValueLabel.getText();
                String filePath = filePathRaw.substring(filePathRaw.indexOf('/'));
                logger.debug("Received filePath: " + filePath);
                newRule.getSubstitute().setStringValue1(filePath);
            }
        }
    }

    /**
     * Responsible for setting the right parameters for the user to enter depending on the kind of
     * substitution he/she performed. Reruns every time a user performs that action
     * @param evt
     */
    private void typeOfSubstitutionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {

        if (isTriggersIsolated) return;

        selectedValueLabel.setText("");

        isTriggersIsolated = true;
        String selectedType = typeOfSubstitutionComboBox.getSelectedItem().toString();
        if (selectedType.equals(StringSubstitutionTypes.SET_TO_VALUE.toString()) ||
                selectedType.equals(DateSubstitutionTypes.SET_TO_VALUE.toString()) ||
                selectedType.equals(NumericSubstitutionTypes.SET_TO_VALUE.toString())) {
            valueOrBrowseLabel.setText("Please Enter Value You Want To Set To");
            valueOrBrowseLabel.setVisible(true);
            valueTextField.setVisible(true);
        } else if (selectedType.equals(StringSubstitutionTypes.FROM_A_LIST.toString())) {
            valueOrBrowseLabel.setText("Please Select A File");
            valueOrBrowseLabel.setVisible(true);
            browseButton.setVisible(true);
        } else if (selectedType.equals(DateSubstitutionTypes.SET_TO_VALUE.toString())) {
            valueOrBrowseLabel.setText("Please Enter Value In Millis since Jan 1 1970");
            valueOrBrowseLabel.setVisible(true);
            valueTextField.setVisible(true);
        } else if (selectedType.equals(StringSubstitutionTypes.RANDOM_STRING.toString())) {
            valueOrBrowseLabel.setText("Please Enter Max String Size");
            valueOrBrowseLabel.setVisible(true);
            valueTextField.setVisible(true);
        } else {
            valueTextField.setVisible(false);
            valueOrBrowseLabel.setVisible(false);
            browseButton.setVisible(false);
        }

        selectedTypeOfSubstitutionLabel.setText(selectedType);
        isTriggersIsolated = false;

        // todo version 2.0: add random within range specified by the user
    }

    private void valueTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        selectedValueLabel.setText(valueTextField.getText());
    }

    /**
     * Runs when user wants to substitute string from a list of strings
     * @param evt
     */
    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {

        // create a file chooser
        JFileChooser fc = new JFileChooser(Constants.WORKING_DIRECTORY);
        int returnVal = fc.showOpenDialog(browseButton);
        File file = null;

        if (evt.getSource() == browseButton) {
            //handle open button action

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();

            }
        }

        setFromFile = file;
        selectedValueLabel.setText("File: " + file.getAbsolutePath());
    }
}
