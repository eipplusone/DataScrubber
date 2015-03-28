package com.pearson.Utilities;

import noNamespace.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeffrey Schmidt
 */
public class LoggerUtils {
    
    private static Logger logger = LoggerFactory.getLogger(LoggerUtils.class.getName());
    
    public static boolean doesFileExist(String filePathAndName) {
        
        if ((filePathAndName == null) || filePathAndName.isEmpty()) {
            return false;
        }

        File file = new File(filePathAndName);
        
        boolean doesFileExist;
        
        try {
            doesFileExist = file.exists();
        }
        catch (Exception e) {
            logger.trace(e.toString() + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
            doesFileExist = false;
        }

        return doesFileExist;
    }
    
    public static boolean doesFileExist(String filePath, String filename) {
        if ((filePath == null) || filePath.isEmpty() || (filename == null) || filename.isEmpty()) {
            return false;
        }
        
        return doesFileExist(filePath + File.separator + filename);
    }

    public static void printRuleArray(List<Rule> rulesRunning) {
        System.out.print("Rules running: ");
        for (Rule rule : rulesRunning) {
            System.out.print(rule.getId() + " ");
        }
        System.out.println();
    }
}
