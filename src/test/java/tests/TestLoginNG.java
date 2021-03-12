package test.java.tests;

import helper.LoggerHelper;
import io.qameta.allure.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.pageobjects.Login;


@Listeners({Listener.class})
public class TestLoginNG extends BaseClass {

    public WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestLoginNG.class);
    private Login login;
    private BaseClass baseClass;

    @BeforeClass
    public void setUp() {
        baseClass = new BaseClass();
        baseClass.initDriver();
        logger.info("Setting up Chrome driver.");
        System.out.println(BaseClass.getDriver());
        login = new Login(BaseClass.getDriver());
    }

    @Test(priority = 1, description = "testng description.")
    @Description("allure description. I'll check the pass test behavior.")
    @Epic("EP-001")
    @Feature("Feature 1: Login")
    @Story("Story: tbd")
    @Step("Step: Enter valid username and password and click on Login button.")
    @Severity(SeverityLevel.BLOCKER)
    public void pass() {
        login.with("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(login.successMessagePresent(), "success message not present");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.MINOR)
    @Description("I'll skip the test to check skip test behavior.")
    public void skip() {
        throw new SkipException("Skipping Test");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.TRIVIAL)
    @Description("I'll validate with invalid page title to the fail test behavior.")
    public void fail() {
        Assert.assertEquals(BaseClass.getDriver().getTitle(), "invalid title");
    }

    @AfterClass
    public void tearDown() throws Exception {
        logger.info("Cleaning up driver instance.");
        BaseClass.getDriver().quit();
    }

}
