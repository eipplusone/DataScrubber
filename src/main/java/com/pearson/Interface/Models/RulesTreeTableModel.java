package com.pearson.Interface.Models;

import noNamespace.Rule;
import noNamespace.RuleType;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruslan Kiselev
 *         Date: 7/2/13
 *         Time: 11:30 AM
 *         Project Name: DataScrubber
 */
public class RulesTreeTableModel extends DefaultTreeTableModel {

    public RulesTreeTableModel() {
        super();
    }

    public RulesTreeTableModel(TreeTableNode root) {
        super(root);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public RulesTreeTableModel(TreeTableNode root, List<?> columnNames) {
        super(root, columnNames);    //To change body of overridden methods use File | Settings | File Templates.

        for (TreeTableNode node : getAllChildren(root)){
            generateColumnInfo(node.getUserObject(), node);
        }
    }

    /**
     * Gets all the children inside a tree as an arrayList
     * @param root
     * @return an arrayList containing all the children but not the root(since root is just masking set)
     */
    private ArrayList<TreeTableNode> getAllChildren(TreeTableNode root) {

        if (root == null) throw new IllegalArgumentException("Root is null");

        ArrayList<TreeTableNode> returnArray = new ArrayList<>();
        getAllChildrenHelper(root, returnArray);
        return returnArray;
    }

    /**
     * Fills up all the information required inside the TreeTable columns
     * @param value
     * @param node
     */
    private void generateColumnInfo(Object value, Object node){

        for (int i = 0; i < getColumnCount(); i++){
            setValueAt(value, node, i);
        }
    }

    /**
     * Helper that iterates through the entire tree
     * @param root
     * @param arrayList
     */
    private void getAllChildrenHelper(TreeTableNode root, ArrayList<TreeTableNode> arrayList) {

        if (root.isLeaf()){
            return;
        }
        else {
            while (root.children().hasMoreElements()) {
                TreeTableNode child = root.children().nextElement();

                arrayList.add(child);
                getAllChildrenHelper(child, arrayList);
            }
        }
    }

    /**
     * Depending on the column, sets the value that is appropriate for that column.
     * Bad design, better create DefaultRenderer and go through each column and call
     * defaultRenderer
     * @param value
     * @param node
     * @param column
     */
    @Override
    public void setValueAt(Object value, Object node, int column) {

        // We assume that the only type of values that value is Rule
        Rule rule = (Rule) value;
        if (column == 0) {
            super.setValueAt(rule.getId(), node, column);
        } else if (column == 1) {
            super.setValueAt(rule.getTarget(), node, column);
        } else if (column == 2) {
            if (rule.getRuleType() == RuleType.SHUFFLE) {
                super.setValueAt(rule.getShuffle().getColumn().toString(), node, column);
            } else if (rule.getRuleType() == RuleType.SUBSTITUTION) {
                super.setValueAt(rule.getSubstitute().getColumnArray(), node, column);
            }
            //TODO add search and replace rule
        }
    }

}
