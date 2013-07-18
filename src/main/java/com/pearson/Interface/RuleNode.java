package com.pearson.Interface;

import com.pearson.Interface.Models.RulesTreeTableModel;
import noNamespace.MaskingSetDocument;
import noNamespace.Rule;
import noNamespace.RuleType;
import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author Ruslan Kiselev
 *         Date: 7/3/13
 *         Time: 10:07 AM
 *         Project Name: DataScrubber
 */
public class RuleNode extends AbstractMutableTreeTableNode {


    public RuleNode(Rule rule){

        super(rule);
    }

    /**
     *
     *
     * @param column
     * @return
     */
    @Override
    public Object getValueAt(int column){

        Rule rule = (Rule) getUserObject();

        switch (column) {
            case RulesTreeTableModel.RULE_ID_COLUMN:
                return rule.getId();
            case RulesTreeTableModel.RULE_TYPE:
                return rule.getRuleType();
            case RulesTreeTableModel.TARGET_COLUMN:
                return rule.getTarget();
            case RulesTreeTableModel.COLUMN_NAMES_COLUMN:
                if (rule.getRuleType() == RuleType.SHUFFLE) {
                    String returnString = Arrays.toString(rule.getShuffle().getColumnArray());
                    returnString = returnString.substring(1, returnString.length() - 1); // delete the braces from array.toString
                    return returnString;
                } else if (rule.getRuleType() == RuleType.SUBSTITUTION) {
                    return rule.getSubstitute().getColumn();
                }
        }
        throw new IllegalArgumentException("Column out of range");
    }

    @Override
    public int getColumnCount() {

        return RulesTreeTableModel.COLUMN_NAMES.length;
    }

    public Rule getRule(){
        return (Rule) getUserObject();
    }

}