package test.java.tests;

import helper.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.Login;

import java.io.File;

public class TestLoginNG {

    private WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestLoginNG.class);
    private Login login;

    @BeforeClass
    public void setUp() {
        logger.info("Setting up Chrome driver.");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println(driver.toString());
        login = new Login(driver);
    }

    @Test
    public void succeeded() {
        login.with("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(login.successMessagePresent(), "success message not present");
    }

    @Test
    public void failed() {
        login.with("tomsmith", "bad password");
        Assert.assertTrue(login.failureMessagePresent(), "failure message wasn't present after providing bogus credentials");
    }

    @Test
    public void knownFailure() {
        login.with("tomsmith", "bad password");
        Assert.assertTrue(login.successMessagePresent(), "success message not present");
    }

    @AfterClass
    public void tearDown() throws Exception {
        logger.info("Cleaning up driver instance.");
        driver.quit();
    }

    /**
     * Based on the OS, we get browser driver location.
     *
     * @param driverName a string. ex: "chromedriver"
     * @return the relative (partial) path of the driver. ex: driver/chromedriver
     */
    private static File getDriver(String driverName) {
        if (System.getProperty("os.name").contains("Mac OS X")) {
            return new File(new File("driver"), driverName);
        } else {
            return new File(new File("driver"), driverName + ".exe");
        }
    }
}
