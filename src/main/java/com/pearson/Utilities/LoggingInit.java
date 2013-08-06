package com.pearson.Utilities;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Hello world!
 *
 */
public class LoggingInit {
    
    private static Logger logger = LoggerFactory.getLogger(LoggingInit.class.getName());
    
    public static void main(String[] args) {
        readAndSetLogbackConfiguration("H:\\Projects\\Logback-example", "logback_config.xml"); 
        
        logger.info("lol 1"); //goes to cat.log
        MDC.put("animal", "dog");
        logger.info("lol 2"); //goes to dog.log
        logger.info("lol 3"); //goes to dog.log
        MDC.clear();
        logger.info("lol 4"); //goes to cat.log
    }
    
    public static boolean readAndSetLogbackConfiguration(String filePath, String fileName) {
        
        boolean doesConfigFileExist = LoggerUtils.doesFileExist(filePath, fileName);
        
        if (doesConfigFileExist) {
            File loggerConfigFile = new File(filePath + File.separator + fileName);

            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

            try {
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(context);
                context.reset(); 
                configurator.doConfigure(loggerConfigFile);
                StatusPrinter.printInCaseOfErrorsOrWarnings(context);
                return true;
            } 
            catch (Exception e) {
                StatusPrinter.printInCaseOfErrorsOrWarnings(context);
                return false;
            }
        }
        else {
            return false;
        }
    }

}
