/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.Interface;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import noNamespace.MaskingSetDocument;
import noNamespace.MaskingSetDocument.MaskingSet;
import noNamespace.Rule;
import noNamespace.RulesDocument;
import org.apache.xmlbeans.XmlOptions;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.xmlbeans.XmlException;

/**
 *
 * @author Ruslan Kiselev
 */
public class XMLInterface {

    File xmlFile;
    MaskingSetDocument setDocument;
    XmlOptions options;

    /**
     * Default constructor assumes that we just created a new set and have not
     * specified a name for it yet
     */
    public XMLInterface(File xmlFile) throws XmlException, IOException {

        this.xmlFile = xmlFile;
        
        options = new XmlOptions();
        options.setSavePrettyPrint();
        options.setSavePrettyPrintIndent(4);
        options.setUseDefaultNamespace();
        
        setDocument = MaskingSetDocument.Factory.parse(xmlFile);
    }

    /**
     * Creates a new representation of the XML rule file internally.
     */
    public void createNewFile() {

        MaskingSetDocument setDocument = MaskingSetDocument.Factory.newInstance();
        MaskingSet maskingSet = setDocument.addNewMaskingSet();

        maskingSet.setName("New masking set");
        maskingSet.setDateCreated(new GregorianCalendar());
        maskingSet.addNewRules();
        
        try {
            setDocument.save(xmlFile, options);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a Tree composed of rules inside the rules set. The first level of rules are children of root
     * the root itself is masking set object
     */
    public DefaultMutableTreeTableNode getRulesTree(){

        MaskingSet maskingSet = setDocument.getMaskingSet();
        RulesDocument.Rules rules = maskingSet.getRules();
        DefaultMutableTreeTableNode root = new DefaultMutableTreeTableNode(maskingSet);

        for(Rule rule : rules.getRuleArray()){
            appendNode(rule, root);
        }
        return root;
    }

    /**
     * A recursive method-helpers that initialises tree with the information from masking set
     * @param rule Rule from the first level of masking set
     * @param root Pointer to the root of the tree
     */
    private void appendNode(Rule rule, DefaultMutableTreeTableNode root){

        DefaultMutableTreeTableNode ruleNode = new DefaultMutableTreeTableNode(rule);

        root.add(ruleNode);

        if (rule.getDependencies() == null){ // if root is leaf
            return;
        }
        for(Rule ruleDummy : rule.getDependencies().getRuleArray()){
            appendNode(ruleDummy, ruleNode);
        }
    }
}
