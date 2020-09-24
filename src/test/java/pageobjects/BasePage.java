package pageobjects;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.TestLogin;

public class BasePage {
    private static final Logger logger = LoggerHelper.getLogger(TestLogin.class);
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void visit(String url) {
        driver.get(url);
        logger.info("'" + url + "' URL");
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void click(By locator) {
        find(locator).click();
        logger.info("on '" + locator + "' locator");
    }

    public void type(String inputText, By locator) {
        find(locator).sendKeys(inputText);
        logger.info("'" + inputText + "' into '" + locator + "' locator");
    }

    public Boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (NoSuchElementException exception) {
            logger.error("Element '" + find(locator) + "' Not Present.");
            logger.error(exception);
            return false;
        }
    }
}
