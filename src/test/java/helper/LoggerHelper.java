package helper;

import org.apache.log4j.*;

import java.io.IOException;

public class LoggerHelper {
    private static PatternLayout layout = null;
    private static ConsoleAppender console = null;
    private static RollingFileAppender rolling = null;
    private static Logger rootLogger = null;
    private static Logger logger = null;
    private static final String pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c : %L - [%M] %m%n";
    private static boolean isLoggerInitialized = false;

    public static void initLogger() {
        layout = new PatternLayout(pattern);

        // Console Appender
        console = new ConsoleAppender(layout);
        console.setName("STDOUT");
        console.setTarget("System.out");
        console.setThreshold(Level.INFO);
        console.activateOptions();

        // Rolling File Appender
        try {
            rolling = new RollingFileAppender(layout, "log/selenium.log", false);
            rolling.setName("RFILE");
            rolling.setThreshold(Level.INFO);
            rolling.setMaxFileSize("28MB");
            rolling.setMaxBackupIndex(100);
            rolling.activateOptions();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Root Logger
        rootLogger = Logger.getRootLogger();
        rootLogger.addAppender(console);
        rootLogger.addAppender(rolling);
    }

    public static Logger getLogger(Class aClass) {
        if (!isLoggerInitialized) {
            initLogger();
            isLoggerInitialized = true;
            LoggerHelper.logger = logger.getLogger(aClass);
            return LoggerHelper.logger;
        } else {
            LoggerHelper.logger = logger.getLogger(aClass);
            return LoggerHelper.logger;
        }
    }
}
