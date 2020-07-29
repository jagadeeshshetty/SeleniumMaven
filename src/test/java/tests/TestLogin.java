package tests;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestLogin {

    private WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestLogin.class);

    @Before
    public void setUp() {
        logger.info("Setting up Chrome driver.");
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/vendor/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
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
    }

    @After
    public void tearDown() {
        if (driver != null) {
            logger.warn("Driver object not null. So Cleaning up.");
            driver.close();
            driver.quit();
        }

    }
}
