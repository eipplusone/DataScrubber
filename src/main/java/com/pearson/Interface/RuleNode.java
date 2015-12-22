package com.pearson.Interface;

import com.pearson.Interface.Windows.Models.RulesTreeTableModel;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.SubstitutionRule;
import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Ruslan Kiselev
 *         Date: 7/3/13
 *         Time: 10:07 AM
 *         Project Name: DataScrubber
 */
public class RuleNode extends AbstractMutableTreeTableNode {

    public static Logger logger = LoggerFactory.getLogger(RuleNode.class.getName());

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
            case RulesTreeTableModel.ACTION_TYPE:
            if (rule.getRuleType() == RuleType.SHUFFLE) {
                return "";
            } else if (rule.getRuleType() == RuleType.SUBSTITUTION) {
                return rule.getSubstitute().getSubstitutionActionType().toString();
            }
            case RulesTreeTableModel.VALUE:
                if (rule.getRuleType() == RuleType.SHUFFLE) {
                    return "";
                } else if (rule.getRuleType() == RuleType.SUBSTITUTION) {
                    SubstitutionRule substitutionRule = rule.getSubstitute();
                    if (substitutionRule.isSetDateValue1()) return substitutionRule.getDateValue1();
                    else if (substitutionRule.isSetNumericValue()) return substitutionRule.getNumericValue();
                    else if (substitutionRule.isSetStringValue1()) return substitutionRule.getStringValue1();
                    else return "";
                }
            case RulesTreeTableModel.DISABLED:
                if(!rule.isSetDisabled()) return false;
                return rule.getDisabled();
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