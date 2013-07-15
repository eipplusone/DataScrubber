package com.pearson.Interface.Models;

import com.pearson.Interface.RuleNode;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

/**
 * @author Ruslan Kiselev
 *         Date: 7/2/13
 *         Time: 11:30 AM
 *         Project Name: DataScrubber
 */
public class RulesTreeTableModel extends AbstractTreeTableModel {

    public final static String[] COLUMN_NAMES = new String[]{"RuleID", "Rule Type", "Target", "Column Names"};
    public final static int RULE_ID_COLUMN = 0;
    public final static int RULE_TYPE = 1;
    public final static int TARGET_COLUMN = 2;
    public final static int COLUMN_NAMES_COLUMN = 3;

    public RulesTreeTableModel(){

    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    public RulesTreeTableModel(RuleNode ruleNode){
        super(ruleNode);
    }

    @Override
    public int getColumnCount() {

        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(Object node, int column) {

        return ((RuleNode) node).getValueAt(column);
    }

    @Override
    public Object getChild(Object parent, int index) {

        return ((RuleNode) parent).getChildAt(index);
    }

    @Override
    public int getChildCount(Object parent) {

        return ((RuleNode) parent).getChildCount();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {

        return ((RuleNode) parent).getIndex((RuleNode)child);
    }

//    public RulesTreeTableModel() {
//
//        super();
//    }
//
//    public RulesTreeTableModel(TreeTableNode root) {
//
//        super(root);    //To change body of overridden methods use File | Settings | File Templates.
//    }
//
//    public RulesTreeTableModel(TreeTableNode root, List<?> COLUMN_NAMES) {
//
//        super(root, COLUMN_NAMES);    //To change body of overridden methods use File | Settings | File Templates.
//
//        for (TreeTableNode node : getAllChildren(root)){
//            generateColumnInfo(node.getUserObject(), node);
//        }
//    }
//
//    /**
//     * Gets all the children inside a tree as an arrayList
//     * @param root
//     * @return an arrayList containing all the children but not the root(since root is just masking set)
//     */
//    private ArrayList<TreeTableNode> getAllChildren(TreeTableNode root) {
//
//        if (root == null) throw new IllegalArgumentException("Root is null");
//
//        ArrayList<TreeTableNode> returnArray = new ArrayList<>();
//        getAllChildrenHelper(root, returnArray);
//        return returnArray;
//    }
//
//    /**
//     * Fills up all the information required inside the TreeTable columns
//     * @param value
//     * @param node
//     */
//    private void generateColumnInfo(Object value, Object node){
//
//        for (int i = 0; i < getColumnCount(); i++){
//            setValueAt(value, node, i);
//        }
//    }
//
//    /**
//     * Helper that iterates through the entire tree
//     * @param root
//     * @param arrayList
//     */
//    private void getAllChildrenHelper(TreeTableNode root, ArrayList<TreeTableNode> arrayList) {
//
//        if (root.isLeaf()){
//            return;
//        }
//        else {
//            for(int i = 0; i < root.getChildCount(); i++){
//                TreeTableNode child = root.getChildAt(i);
//
//                arrayList.add(child);
//                getAllChildrenHelper(child, arrayList);
//            }
//        }
//    }
//
//    /**
//     * Depending on the column, sets the value that is appropriate for that column.
//     * Bad design, better create DefaultRenderer and go through each column and call
//     * defaultRenderer
//     * @param value
//     * @param node
//     * @param column
//     */
//    @Override
//    public void setValueAt(Object value, Object node, int column) {
//
//        // We assume that the only type of values that value is Rule
//        Rule rule = (Rule) value;
//        if (column == 0) {
//            super.setValueAt(rule.getId(), node, column);
//        } else if (column == 1) {
//            super.setValueAt(rule.getTarget(), node, column);
//        } else if (column == 2) {
//            if (rule.getRuleType() == RuleType.SHUFFLE) {
//                super.setValueAt(rule.getShuffle().getColumnArray().toString(), node, column);
//            } else if (rule.getRuleType() == RuleType.SUBSTITUTION) {
//                super.setValueAt(rule.getSubstitute().getColumn(), node, column);
//            }
//            //TODO add search and replace rule
//        }
//    }



}
