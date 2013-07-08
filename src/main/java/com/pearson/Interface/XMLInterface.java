/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.Interface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pearson.Interface.Models.RulesTreeTableModel;
import noNamespace.MaskingSetDocument;
import noNamespace.MaskingSetDocument.MaskingSet;
import noNamespace.Rule;
import noNamespace.RuleType;
import noNamespace.RulesDocument;
import org.apache.xmlbeans.XmlOptions;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.xmlbeans.XmlException;

/**
 * @author Ruslan Kiselev
 */
public class XMLInterface {

    private static File xmlFile = new File("temp_set.xml");
    private static MaskingSetDocument setDocument;
    public final static XmlOptions options = initialiseOptions();

    public static File getXmlFile() {
        return xmlFile;
    }

    public static MaskingSetDocument getSetDocument() {
        return setDocument;
    }

    /**
     * Default constructor assumes that we just created a new set and have not
     * specified a name for it yet
     */
    public static void setXMLFile(File xmlFile_) {

        xmlFile = xmlFile_;


        try {
            ArrayList validationErrors = new ArrayList<>();
            XmlOptions validationOptions = new XmlOptions();
            validationOptions.setErrorListener(validationErrors);
            setDocument = MaskingSetDocument.Factory.parse(xmlFile);
            if (!setDocument.validate(validationOptions)) {
                Iterator itr = validationErrors.iterator();
                while (itr.hasNext()) {
                    System.out.println(">> " + itr.next() + "\n");
                }
                throw new XmlException("XML file is invalid");
            }
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new representation of the XML rule file internally.
     */
    public static void createNewFile() {

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
    public static RuleNode getRulesTree() {

        MaskingSet maskingSet = setDocument.getMaskingSet();
        RulesDocument.Rules rules = maskingSet.getRules();

        Rule rootRule = RulesDocument.Factory.newInstance().addNewRules().addNewRule();

        // a dummy root rule; it is not displayed inside main window
        rootRule.setId("Root");
        rootRule.setTarget("Root");
        rootRule.setRuleType(RuleType.SHUFFLE);
        rootRule.addNewShuffle();

        // root is not displayed; it only creates number of columns TODO make it more generic
        RuleNode root = new RuleNode(rootRule);

        for (Rule rule : rules.getRuleArray()) {
            appendNode(rule, root);
        }
        return root;
    }

    public static void saveFile() throws IOException {
        setDocument.save(xmlFile, options);
    }

/**
 * A recursive method-helpers that initialises tree with the information from masking set
 *
 * @param rule Rule from the first level of masking set
 * @param root Pointer to the root of the tree
 */
    private static void appendNode(Rule rule, RuleNode root) {

        RuleNode ruleNode = new RuleNode(rule);
        root.add(ruleNode);

        if (rule.getDependencies() == null) { // if root is leaf
            return;
        }
        for (Rule ruleDummy : rule.getDependencies().getRuleArray()) {
            appendNode(ruleDummy, ruleNode);
        }
    }

    // static initializer for xmlOptions
    private static XmlOptions initialiseOptions(){

        XmlOptions options = new XmlOptions();

        options.setSavePrettyPrint();
        options.setSavePrettyPrintIndent(4);
        options.setUseDefaultNamespace();

        return options;
    }
}
