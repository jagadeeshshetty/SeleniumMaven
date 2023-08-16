package test.java.tests.junit;

import helper.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class TestException {
    /**
     * Reference:
     * https://www.softwaretestinghelp.com/exception-handling-framework-selenium-tutorial-19/
     */

    private WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestException.class);

    @Before
    public void setUp() {
        logger.info("Setting up WebDriver...");

        // Firefox
        // System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
        // "/vendor/geckodriver.exe");
        // FirefoxOptions options = new FirefoxOptions();
        // options.setAcceptInsecureCerts(true).setHeadless(true).setLogLevel(FirefoxDriverLogLevel.ERROR);
        // driver = new FirefoxDriver(options);

        // Chrome
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Test
    public void verifyPageTitle() {
        logger.info("Running " + new Throwable().getStackTrace()[0].getMethodName() + "() method.");
        try {
            driver.get("http://google.com");
            assertEquals("Expecting 'Google' title.", driver.getTitle(), "Google");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unhandledNoAlertPresentException() {
        try {
            // org.openqa.selenium.NoAlertPresentException: no such alert
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            logger.error("No such alert.");
        }
    }

    @Test
    public void handledNoAlertPresentException() {
        // Avoiding-And-Handling:
        // Always use explicit or fluent wait for a particular time in all cases where
        // an alert is expected.
        // If the alert is available and still there is an exception, then it is caught.
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            try {
                driver.switchTo().alert().accept();
            } catch (NoAlertPresentException e) {
                logger.info("An exceptional case");
            }
        } catch (TimeoutException e) {
            logger.info("WebDriver couldn't locate the Alert");
        }
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            logger.warn("Closing up WebDriver...");
            driver.quit();
        }
    }
}
