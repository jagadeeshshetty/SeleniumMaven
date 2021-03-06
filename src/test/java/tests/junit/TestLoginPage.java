package test.java.tests.junit;

import helper.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.java.pageobjects.theInternet.LoginPage;

import static org.junit.Assert.assertTrue;

public class TestLoginPage {

    private WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestLoginPage.class);
    private LoginPage loginPage;

    @Before
    public void setUp() {
        logger.info("Setting up Chrome driver.");
        /**
         * In order for the instantiation of Selenium to work with Browsers, we need to specify the path to the
         * browser driver we downloaded into the vendor directory. We're able to do this by specifying a
         * system property (e.g., System.setProperty("webdriver.gecko.driver" ) and providing the full path
         * to the file which we find by using the project directory path and appending /vendor/geckodriver
         * to it.
         */
        try {
            System.out.println(System.getProperty("os.name"));
            if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
                // { "webdriver.gecko.driver" : "C:\Users\jagadeesh\Documents\SeleniumMaven/vendor/geckodriver.exe" }
                driver = new FirefoxDriver();
            } else if (System.getProperty("os.name").contains("Mac OS X")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/vendor/macOS/89/chromedriver");
                driver = new ChromeDriver();
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
        loginPage = new LoginPage(driver, logger);
    }

    @Test
    public void succeeded() {
        loginPage.with("tomsmith", "SuperSecretPassword!");
        assertTrue("success message not present", loginPage.successMessagePresent());
    }

    @Test
    public void failed() {
        loginPage.with("tomsmith", "bad password");
        assertTrue("failure message wasn't present after providing bogus credentials",
                loginPage.failureMessagePresent());
    }

    @After
    public void tearDown() throws Exception {
        logger.info("Cleaning up driver instance.");
        driver.quit();
    }
}
