/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.Interface;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author UKISERU
 */
public class ItemsSelectedTableModel extends AbstractTableModel {
    
    public ArrayList<String> itemNames;
    
    public ItemsSelectedTableModel(ArrayList<String> tableNames){
        this.itemNames = tableNames;
    }

    @Override
    public int getRowCount() {
        return itemNames.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return itemNames.get(rowIndex);
    }
    
}
