package test.java.tests.selenium.exceptions;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;

@Listeners({Listener.class})
public class TestExceptions extends Base {
    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the default state of checkboxes.")
    void testWebDriverException() {
        try {
            getDriver().get("http://the-internet.herokuapp.com/login");
            WebElement usernameWebElement = getDriver().findElement(By.id("username"));             // valid locator
            usernameWebElement.sendKeys("jagadeesh");
            WebElement passwordWebElement = getDriver().findElement(By.id("invalid-password"));     // invalid locator
            passwordWebElement.sendKeys("password");
        } catch (NoSuchElementException e) {
            stepError("Element not found: " + e.getMessage());
        }
//        try {
//            getDriver().get("http://the-internet.herokuapp.com/login");
//            WebElement usernameWebElement = getDriver().findElement(By.id("username"));             // valid locator
//            usernameWebElement.sendKeys("jagadeesh");
//            WebElement passwordWebElement = getDriver().findElement(By.id("invalid-password"));     // invalid locator
//            passwordWebElement.sendKeys("password");
//        } catch (WebDriverException e) {
//            stepError("Caught a WebDriverException: " + e.getMessage());
//        }
    }
}
