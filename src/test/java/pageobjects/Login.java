package test.java.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static io.qameta.allure.Allure.step;
import static org.junit.Assert.assertTrue;

public class Login extends BasePage {
    By usernameLocator = By.id("username");
    By passwordLocator = By.id("password");
    By submitButton = By.cssSelector("button");
    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");
    By loginFormLocator = By.id("login");

    public Login(WebDriver driver, Logger logger) {
        super(driver, logger);
        visit("http://the-internet.herokuapp.com/login");
        assertTrue("The login form is not present", isDisplayed(loginFormLocator));
    }

    public void with(String username, String password) {
        step("Enter " + username + " username");
        type(username, usernameLocator);
        step("Enter " + password + " password");
        type(password, passwordLocator);
        step("Click on Submit button");
        click(submitButton);
    }

    public Boolean successMessagePresent() {
        step("Verify success message present or not.");
        return isDisplayed(successMessageLocator);
    }

    public Boolean failureMessagePresent() {
        step("Verify failure message present or not.");
        return isDisplayed(failureMessageLocator);
    }
}
