package tests.chromeDevTools;

import helper.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.log.Log;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.Login;
import tests.TestLogin;

public class TestChromeDevTools {
    private WebDriver driver;
    private static DevTools chromeDevTools;
    private static final Logger logger = LoggerHelper.getLogger(TestLogin.class);
    private Login login;

    @Before
    public void setUp() {
        logger.info("Setting up Chrome driver.");
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.gecko.driver",
                        System.getProperty("user.dir") + "/vendor/geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (System.getProperty("os.name").contains("Mac OS X")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/vendor/macOS/85/chromedriver");
                driver = new ChromeDriver();
                driver.get("http://the-internet.herokuapp.com/login");
                chromeDevTools = ((ChromeDriver) driver).getDevTools();
                chromeDevTools.createSession();
                chromeDevTools.send(Log.enable());
                chromeDevTools.addListener(Log.entryAdded(), logEntry -> System.out.println(logEntry.asSeleniumLogEntry()));
            } else {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }
            if (driver != null) logger.info(driver.toString());
        } catch (Exception e) {
            logger.error("Chrome driver init failed.", e);
        }
        login = new Login(driver);
    }

    @After
    public void tearDown() throws Exception {
        logger.info("Cleaning up driver instance.");
        driver.quit();
    }

    @Test
    public void emulateNetworkConditionTest() {

    }

}
