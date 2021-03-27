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

import static io.qameta.allure.Allure.step;

@Listeners({Listener.class})
public class TestLogin extends Base {

    // Page object
    private test.java.pageobjects.Login login;

    @BeforeMethod
    public void testSetUp() {
        login = new test.java.pageobjects.Login(driver, logger);
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test will verify the successful login with valid username and password.")
    public void validLogin() {
        step("Login with valid username and password.");
        login.with("tomsmith", "SuperSecretPassword!");
        step("Verify success message present");
        Assert.assertTrue(login.successMessagePresent(), "success message not present");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Test will verify the error message with invalid username and password.")
    public void inValidLogin() {
        step("Login with invalid username and password.");
        login.with("invalid", "invalid");
        step("Verify failure message present");
        Assert.assertTrue(login.failureMessagePresent(), "failure message wasn't present after providing bogus credentials");
    }


}
