package test.java.tests.theInternet;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;
import test.java.pageobjects.theInternet.LoginPage;

import java.util.Random;

import static io.qameta.allure.Allure.step;

@Listeners({Listener.class})
public class TestLoginPage extends Base {

    // Page object
    private LoginPage loginPage;

    @BeforeMethod
    public void testSetUp() {
        loginPage = new LoginPage(driver, logger);
    }

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify the successful login with valid username and password.")
    public void validLogin() {
        step("1. Login with valid username and password.");
        loginPage.with("tomsmith", "SuperSecretPassword!");
        step("2. Verify success message present");
        Assert.assertTrue(loginPage.successMessagePresent(), "success message not present");
    }

    @Test(priority = 2, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the error message with invalid username and password.")
    public void inValidLogin() {
        step("1. Login with invalid username and password.");
        loginPage.with("invalid", "invalid");
        step("2. Verify failure message present");
        Assert.assertTrue(loginPage.failureMessagePresent(), "failure message wasn't present after providing bogus credentials");
    }

    @Test(priority = 3, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify the error message with invalid username and password.")
    public void unstableTest() {
        Random random = new Random();
        int rem = random.nextInt(10) % 2;
        step(String.valueOf(rem));
        Assert.assertTrue(rem == 0);
        Assert.assertTrue(false);
    }

}
