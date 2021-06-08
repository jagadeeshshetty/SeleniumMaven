package test.java.tests.selenium;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;

import static io.qameta.allure.Allure.step;

@Listeners({Listener.class})
public class TestExceptions extends Base {
    // Page object
//    private LoginPage loginPage;

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify the successful login with valid username and password.")
    public void testValidLogin() {
        step("1. Login with valid username and password.");
        getDriver().get("http://the-internet.herokuapp.com/");
        getDriver().findElement(By.xpath("//*[.='A/B Testing']")).click();

        step("2. Verify success message present");
    }

}
