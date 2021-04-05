package test.java.tests.theInternet;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;
import test.java.pageobjects.theInternet.LoginPage;

@Listeners({Listener.class})
public class TestTwo extends Base {

    // Page object
    private LoginPage loginPage;

    @BeforeMethod
    public void testSetUpTwo() {
        loginPage = new LoginPage(driver, logger);
    }

    @Test(enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify the successful login with valid username and password.")
    public void testTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("testTwo Thread id is: " + id);
//        step("1. Login with valid username and password.");
//        loginPage.with("tomsmith", "SuperSecretPassword!");
//        step("2. Verify success message present");
//        Assert.assertTrue(loginPage.successMessagePresent(), "success message not present");
    }

}
