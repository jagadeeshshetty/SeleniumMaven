package test.java.helper;

public class GlobalConfig {

    public static final String browser = System.getProperty("browser", "firefox");
    public static final String maxRetryCount = System.getProperty("maxRetryCount", "2");
    public static final int pageLoadTimeoutInSec = 48;
}
