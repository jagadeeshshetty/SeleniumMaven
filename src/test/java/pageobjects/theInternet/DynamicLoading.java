package test.java.pageobjects.theInternet;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.java.pageobjects.BasePage;

public class DynamicLoading extends BasePage {
    By startButton = By.cssSelector("#start button");
    By finishText = By.id("finish");

    public DynamicLoading(WebDriver driver, Logger logger) {
        super(driver, logger);
    }

    public void loadExample(String exampleNumber) {
        visit("http://the-internet.herokuapp.com/dynamic_loading/" + exampleNumber);
        click(startButton);
    }

    public Boolean finishTextPresent() {
        return isDisplayed(finishText, 10);
    }

}
