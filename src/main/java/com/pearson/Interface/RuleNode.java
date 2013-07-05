package com.pearson.Interface;

import com.pearson.Interface.Models.RulesTreeTableModel;
import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;

/**
 * @author Ruslan Kiselev
 *         Date: 7/3/13
 *         Time: 10:07 AM
 *         Project Name: DataScrubber
 */
public class RuleNode extends AbstractMutableTreeTableNode {

    public RuleNode(Object[] data) {

        super(data);
    }

    @Override
    public Object getValueAt(int column) {

        return getUserObject()[column];
    }

    @Override
    public void setValueAt(Object aValue, int column) {

        getUserObject()[column] = aValue;
    }

    @Override
    public int getColumnCount() {
        
        return RulesTreeTableModel.COLUMN_NAMES.length;
    }

    @Override
    public Object[] getUserObject() {

        return (Object[]) super.getUserObject();
    }

}