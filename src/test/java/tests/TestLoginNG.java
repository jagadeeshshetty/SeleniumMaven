package test.java.tests;

import helper.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.pageobjects.Login;

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

    @Test(priority = 1)
    public void pass() {
        login.with("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(login.successMessagePresent(), "success message not present");
    }

    @Test(priority = 2)
    public void skip() {
        throw new SkipException("Skipping Test");
    }

    @Test(priority = 3)
    public void fail() {
        Assert.assertEquals(driver.getTitle(), "invalid title");
    }

    @AfterClass
    public void tearDown() throws Exception {
        logger.info("Cleaning up driver instance.");
        driver.quit();
    }

}
