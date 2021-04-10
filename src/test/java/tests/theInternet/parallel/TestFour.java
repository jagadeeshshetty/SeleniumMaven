package test.java.tests.theInternet.parallel;

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

import java.util.Random;

import static io.qameta.allure.Allure.step;

@Listeners({Listener.class})
public class TestFour extends Base {

    private LoginPage loginPage;

    @Test(priority = 2, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify the successful login with valid username and password.")
    public void testFourValidLoginOne() {
        long id = Thread.currentThread().getId();
        System.out.println("testFourValidLoginOne() Thread id is: " + id + ". Driver instance: " + getDriver().toString());
        loginPage = new LoginPage(getDriver(), logger);

        step("1. Login with valid username and password.");
        loginPage.with("tomsmith", "SuperSecretPassword!");

        step("2. Verify success message present");
        Assert.assertTrue(loginPage.successMessagePresent(), "success message not present");
    }

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify the successful login with valid username and password.")
    public void testFourValidLoginTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("testFourValidLoginTwo() Thread id is: " + id + ". Driver instance: " + getDriver().toString());
        loginPage = new LoginPage(getDriver(), logger);

        step("1. Login with valid username and password.");
        loginPage.with("tomsmith", "SuperSecretPassword!");

        step("2. Verify success message present");
        Assert.assertTrue(loginPage.successMessagePresent(), "success message not present");
    }

    @Test(priority = 3, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify the error message with invalid username and password.")
    public void testFourUnstableTest() {
        long id = Thread.currentThread().getId();
        System.out.println("testFourUnstableTest() Thread id is: " + id);
        Random random = new Random();
        int rem = random.nextInt(10) % 2;
        step(String.valueOf(rem));
        Assert.assertTrue(rem == 0);
        Assert.assertTrue(false);
    }

    @Test(priority = 4, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify the successful login with valid username and password.")
    public void testFourExpectedFailure() {
        long id = Thread.currentThread().getId();
        System.out.println("testFourExpectedFailure() Thread id is: " + id + ". Driver instance: " + getDriver().toString());
        loginPage = new LoginPage(getDriver(), logger);

        step("1. Login with valid username and password.");
        loginPage.with("tomsmith", "SuperSecretPassword!");

        step("2. Verify success message present");
        Assert.assertFalse(loginPage.successMessagePresent(), "success message not present");
    }
}
