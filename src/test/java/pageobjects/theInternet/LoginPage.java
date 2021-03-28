package test.java.pageobjects.theInternet;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test.java.pageobjects.BasePage;

import static io.qameta.allure.Allure.step;

public class LoginPage extends BasePage {
    By usernameLocator = By.id("username");
    By passwordLocator = By.id("password");
    By submitButton = By.cssSelector("button");
    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");
    By loginFormLocator = By.id("login");
    String URL = "http://the-internet.herokuapp.com/login";

    public LoginPage(WebDriver driver, Logger logger) {
        super(driver, logger);
        step("Access " + URL + " URL.");
        visit(URL);
        step("Verify login form present or not.");
        Assert.assertTrue(isDisplayed(loginFormLocator), "The login form is not present");
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
