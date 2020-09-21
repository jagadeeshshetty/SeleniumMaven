package tests;

import helper.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class TestLogin {

    private WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestLogin.class);

    @Before
    public void setUp() {
        logger.info("Setting up Chrome driver.");
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.gecko.driver",
                        System.getProperty("user.dir") + "/vendor/geckodriver.exe");
                driver = new FirefoxDriver();
            } else {
                // System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/vendor/chromedriver");
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
    }

    @Test
    public void succeeded() {
        driver.get("http://the-internet.herokuapp.com/login");
        logger.info("Invoked URL.");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        logger.info("Entered username.");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        logger.info("Entered password.");
        driver.findElement(By.cssSelector("button")).click();
        logger.info("Clicked on button.");
        assertTrue("success message not present", driver.findElement(By.cssSelector(".flash.success")).isDisplayed());
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            logger.warn("Driver object not null. So Cleaning up.");
            driver.quit();
        }
    }
}
