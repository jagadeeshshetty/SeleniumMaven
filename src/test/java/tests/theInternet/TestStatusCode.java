package test.java.tests.theInternet;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;
import test.java.pageobjects.theInternet.LoginPage;

import static io.qameta.allure.Allure.step;

@Listeners({Listener.class})
public class TestStatusCode extends Base {

    private LoginPage loginPage;

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify the successful login with valid username and password.")
    public void testStatusCode() {
        long id = Thread.currentThread().getId();
        System.out.println("testOneValidLoginTwo() Thread id is: " + id + ". Driver instance: " + getDriver().toString());
        loginPage = new LoginPage(getDriver(), logger);

        step("1. Login with valid username and password.");
        loginPage.with("tomsmith", "SuperSecretPassword!");

        step("2. Verify success message present");
        Assert.assertTrue(loginPage.successMessagePresent(), "success message not present");
    }
}
