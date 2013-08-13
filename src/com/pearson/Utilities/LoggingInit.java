package pearson.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class LoggingInit {
    
    private static Logger logger = LoggerFactory.getLogger(LoggingInit.class.getName());
    
    /**
     * Set logging configuration file.
     * @param filePath file path without file separator
     * @param fileName file name
     * @return
     */
//    public static boolean readAndSetLogbackConfiguration(String filePath, String fileName) {
//
//        boolean doesConfigFileExist = LoggerUtils.doesFileExist(filePath, fileName);
//
//        if (doesConfigFileExist) {
//            File loggerConfigFile = new File(filePath + File.separator + fileName);
//
//            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//
//            try {
//                JoranConfigurator configurator = new JoranConfigurator();
//                configurator.setContext(context);
//                context.reset();
//                configurator.doConfigure(loggerConfigFile);
//                StatusPrinter.printInCaseOfErrorsOrWarnings(context);
//                return true;
//            }
//            catch (Exception e) {
//                StatusPrinter.printInCaseOfErrorsOrWarnings(context);
//                return false;
//            }
//        }
//        else {
//            return false;
//        }
//    }

}
