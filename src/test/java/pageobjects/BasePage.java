package test.java.pageobjects;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.qameta.allure.Allure.step;

public class BasePage {
    private static Logger logger;
    private WebDriver driver;

    public BasePage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = LoggerHelper.getLogger(BasePage.class);
    }

    public void visit(String url) {
        step("Access '" + url + "' URL");
        driver.get(url);
        logger.info("'" + url + "' URL");
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void click(By locator) {
        step("Click on '" + locator + "' locator");
        find(locator).click();
        logger.info("on '" + locator + "' locator");
    }

    public void type(String inputText, By locator) {
        step("Type '" + inputText + "' text into '" + locator + "' locator");
        find(locator).sendKeys(inputText);
        logger.info("'" + inputText + "' into '" + locator + "' locator");
    }

    public Boolean isDisplayed(By locator) {
        step("Check Element [" + find(locator) + "] Present or Not.");
        try {
            logger.info("Element [" + find(locator) + "] Present.");
            step("Element [" + find(locator) + "] Present.");
            return find(locator).isDisplayed();
        } catch (NoSuchElementException exception) {
            logger.error("Element [" + find(locator) + "] Not Present.");
            step("Element [" + find(locator) + "] Not Present.");
            return false;
        }
    }

    /**
     * ExpectedConditions:<p>
     * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html
     *
     * @param locator
     * @param timeout
     * @return
     */
    public Boolean isDisplayed(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException exception) {
            logger.error("Element [" + locator + "] Not Present.");
            step("Element [" + locator + "] Not Present.");
            return false;
        }
        logger.info("Element [" + locator + "] Present.");
        step("Element [" + locator + "] Present.");
        return true;
    }

}
