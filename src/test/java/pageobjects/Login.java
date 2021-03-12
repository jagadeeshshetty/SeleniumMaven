package test.java.pageobjects;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.java.tests.TestLogin;

import static org.junit.Assert.assertTrue;

public class Login extends BasePage {
    private static final Logger logger = LoggerHelper.getLogger(TestLogin.class);
    By usernameLocator = By.id("username");
    By passwordLocator = By.id("password");
    By submitButton = By.cssSelector("button");
    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");
    By loginFormLocator = By.id("login");
    private WebDriver driver;

    public Login(WebDriver driver) {
        super(driver);
        visit("http://the-internet.herokuapp.com/login");
        assertTrue("The login form is not present", isDisplayed(loginFormLocator));
    }

    public void with(String username, String password) {
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(submitButton);
    }

    public Boolean successMessagePresent() {
        return isDisplayed(successMessageLocator);
    }

    public Boolean failureMessagePresent() {
        return isDisplayed(failureMessageLocator);
    }
}
