/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pearson.Interface.Interfaces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import com.pearson.Interface.RuleNode;
import com.pearson.Readers.SetReader;
import noNamespace.MaskingSetDocument;
import noNamespace.MaskingSetDocument.MaskingSet;
import noNamespace.Rule;
import noNamespace.RulesDocument;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlException;


/**
 * @author Ruslan Kiselev
 */
public class XMLInterface{

    public final static XmlOptions options = initialiseOptions();
    private static File xmlFile; // todo several applications running
    private static MaskingSetDocument setDocument;
    private static boolean isFileSaved = false;

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

        setDocument = MaskingSetDocument.Factory.newInstance();
        MaskingSet maskingSet = setDocument.addNewMaskingSet();

        maskingSet.setDateCreated(new GregorianCalendar());
        maskingSet.addNewRules();
    }

    /**
     * Static initialiser for options
     *
     * @return
     */
    private static XmlOptions initialiseOptions() {

        XmlOptions options = new XmlOptions();

        options.setSavePrettyPrint();
        options.setSavePrettyPrintIndent(4);
        options.setUseDefaultNamespace();

        return options;
    }

    /**
     * Returns rule from current document
     *
     * @param id - rule of id
     */
    public static Rule getRule(String id) {

        String[] ids = id.split("-");
        RulesDocument.Rules rules = setDocument.getMaskingSet().getRules();
        // get the rule with the first index
        Rule rule = rules.getRuleArray(Integer.valueOf(ids[0]) - 1); // -1 since we display rules ids start counting from 1
        // if it has dependencies, go through dependencies to get the rule
        for (int i = 1; i < ids.length; i++) {
            rule = rule.getDependencies().getRuleArray(Integer.valueOf(ids[i]) - 1);
        }

        return rule;
    }

    public static void generateIDs() {

        RulesDocument.Rules rules = setDocument.getMaskingSet().getRules();

        for (int i = 0; i < rules.getRuleArray().length; i++) {
            Rule rule = rules.getRuleArray(i);
            // set id to it position within array plus 1
            rule.setId((i + 1) + "");
            generateIDs(rule);
        }
    }

    private static void generateIDs(Rule root) {

        if (isLeaf(root)) {
            return;
        }

        Rule[] dependencyArray = root.getDependencies().getRuleArray();
        for (int i = 0; i < dependencyArray.length; i++) {
            Rule childRule = dependencyArray[i];
            childRule.setId(root.getId() + "-" + (i + 1));
            generateIDs(childRule);
        }
    }

    public static void addDependencyToRule(Rule rule) {
        rule.addNewDependencies();
    }

    public static void removeRule(Rule ruleToDelete) {

        RulesDocument.Rules rules = setDocument.getMaskingSet().getRules();

        if (XMLInterface.isFirstLevelRule(ruleToDelete)) {
            rules.removeRule(Integer.valueOf(ruleToDelete.getId()) - 1);
        } else {
            // if it's not the first-level rule, we would have to get the parent rule in order to delete it
            Rule parentRule = getParent(ruleToDelete);
            String id = ruleToDelete.getId();

            // last index of id minus one is the index of that rule inside it's parent rule
            int childIndex = id.charAt(id.length() - 1) - '0' - 1;

            parentRule.getDependencies().removeRule(childIndex);
            if (parentRule.getDependencies().getRuleArray() == null) {
                // if there are no more rules
                parentRule.unsetDependencies();
            }

        }

        // since we deleted rule, ids mush be changed (bad design, todo: improve rule-delete logic; might need architecture difference)
        generateIDs();
    }

    private static boolean isFirstLevelRule(Rule ruleToDelete) {

        return !ruleToDelete.getId().contains("-");
    }

    public static Rule getParent(Rule childRule) {

        String childID = childRule.getId();
        // if there are no "-" is it the first level rule - it's parent is the first and only root dummy rule
        if (isFirstLevelRule(childRule)) throw new IllegalArgumentException("The rule is first-level rule");

        // get everything before last '-' - that is the parent id
        String parentID = childID.substring(0, childID.lastIndexOf("-"));

        return getRule(parentID);
    }

    public static boolean isLeaf(Rule rule) {

        if (rule.getDependencies() == null) return true;
        else return false;
    }

    /**
     * Saves the current masking set under the xmlFile specified inside XMLInterface
     */
    public static void saveCurrentFile() throws IOException {

        setDocument.save(xmlFile, options);
    }

    public static RuleNode getRulesTree() {
        return SetReader.getRulesTree(setDocument);
    }

    public static boolean isFileSaved() {

        if(setDocument == null || xmlFile == null) throw new IllegalArgumentException("File was not chosen");

        try {
            // compare the texts of xml files - if no changes were made, they should be the same
            return setDocument.xmlText().equals(MaskingSetDocument.Factory.parse(xmlFile).xmlText());
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

