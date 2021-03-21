package test.java.tests;

import helper.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.java.pageobjects.Login;

import static org.junit.Assert.assertTrue;

public class TestLoginGA {

    private WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestLoginGA.class);
    private Login login;

    @BeforeTest
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
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            if (driver != null) logger.info(driver.toString());
        } catch (Exception e) {
            logger.error("Chrome driver init failed.", e);
        }
        login = new Login(driver);
    }

    @Test
    public void succeeded() {
        login.with("tomsmith", "SuperSecretPassword!");
        assertTrue("success message not present", login.successMessagePresent());
    }

    @Test
    public void succeeded2() {
        login.with("tomsmith", "SuperSecretPassword!");
        assertTrue("success message not present", login.successMessagePresent());
    }

    @Test
    public void failed() {
        login.with("tomsmith", "bad password");
        assertTrue("failure message wasn't present after providing bogus credentials",
                login.failureMessagePresent());
    }

    @AfterTest
    public void tearDown() throws Exception {
        logger.info("Cleaning up driver instance.");
        driver.quit();
    }
}
