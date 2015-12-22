package com.pearson.Interface.Windows.Controllers;

import com.pearson.Interface.Interfaces.XMLInterface;
import com.pearson.Interface.UIManager;
import com.pearson.Interface.Windows.DatabaseConnectionInfoWindow;
import com.pearson.Interface.Windows.Models.RulesTreeTableModel;
import com.pearson.Readers.SetReader;
import noNamespace.Rule;
import org.apache.xmlbeans.XmlException;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ruslankiselev on 10/25/14.
 * <p/>
 * May the force be with you.
 */
public class MainWindowController {

    private RulesTreeTableModel rulesInSetTreeModel = new RulesTreeTableModel();
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MainWindowController.class.getName());



    public void runSet() throws IOException, SQLException {
        // if user connection info isn't set
        if (!UIManager.isUserInformationSet()) {
            setConnectionInformation();
            logger.info("Connection information was set");
        }

        SetReader setReader;
        if (XMLInterface.getXmlFile() != null) {
            setReader = new SetReader(XMLInterface.getSetDocument(), UIManager.getCurrentConnection(), XMLInterface.getXmlFile().getName());
        } else {
            throw new IOException("MaskingSet file wasn't set; Please open/create new MaskingSet");
        }

        setReader.execute();
    }

    /**
     * Creates a window that lets user populate the connection information. That in turn is passed to UIManager
     * to store as the main connection inforamtion.
     *
     * @return false if user closed the window, true if the information is uploaded to UIManager
     */
    public void setConnectionInformation() {
        DatabaseConnectionInfoWindow connectionInfoWindow = new DatabaseConnectionInfoWindow();
        connectionInfoWindow.setVisible(true);
    }

    public RulesTreeTableModel getRulesInSetTreeModel() {
        rulesInSetTreeModel = new RulesTreeTableModel(XMLInterface.getRulesTree());
        return rulesInSetTreeModel;
    }

    public void setXMLFile(File openFile) throws IOException, XmlException {
        XMLInterface.setXMLFile(openFile);
    }

    public void saveSetToAFile(File newFile) throws IOException {
        XMLInterface.saveSetToAFile(newFile);
    }

    public void saveCurrentFile() throws IOException {
        XMLInterface.saveCurrentFile();
    }

    public Rule getRule(String ruleID) {
        return XMLInterface.getRule(ruleID);
    }

    public void removeRule(Rule ruleToDelete) {
        XMLInterface.removeRule(ruleToDelete);
    }

    public File getXmlFile() {
        return XMLInterface.getXmlFile();
    }

    public boolean isFileSaved() {
        return XMLInterface.isFileSaved();
    }

    public void createNewFile(String fileName) {
        XMLInterface.createNewFile(fileName);
    }
}
