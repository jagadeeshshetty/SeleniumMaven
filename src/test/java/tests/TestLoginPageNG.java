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
import test.java.helper.BaseRemove;
import test.java.helper.Listener;
import test.java.pageobjects.theInternet.LoginPage;


@Listeners({Listener.class})
public class TestLoginPageNG extends BaseRemove {

    public WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestLoginPageNG.class);
    private LoginPage loginPage;
    private BaseRemove baseRemove;

    @BeforeClass
    public void setUp() {
        baseRemove = new BaseRemove();
        baseRemove.initDriver();
        logger.info("Setting up Chrome driver.");
        System.out.println(BaseRemove.getDriver());
        loginPage = new LoginPage(BaseRemove.getDriver(), logger);
    }

    @Test(priority = 1, description = "testng description.")
    @Description("allure description. I'll check the pass test behavior.")
    @Epic("EP-001")
    @Feature("Feature 1: Login")
    @Story("Story: tbd")
    @Step("Step: Enter valid username and password and click on Login button.")
    @Severity(SeverityLevel.BLOCKER)
    public void pass() {
        loginPage.with("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(loginPage.successMessagePresent(), "success message not present");
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
        Assert.assertEquals(BaseRemove.getDriver().getTitle(), "invalid title");
    }

    @AfterClass
    public void tearDown() throws Exception {
        logger.info("Cleaning up driver instance.");
        BaseRemove.getDriver().quit();
    }

}
