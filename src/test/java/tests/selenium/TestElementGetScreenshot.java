package test.java.tests.selenium;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;
import test.java.pageobjects.theInternet.LoginPage;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

@Listeners({Listener.class})
public class TestElementGetScreenshot extends Base {

    private LoginPage loginPage;

    @Test(enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the element capture as an image using getScreenshotAs() method Selenium 4 feature.")
    public void testElementGetScreenshot() throws IOException {
        step("Will capture Submit button element image.");
        getDriver().get("http://the-internet.herokuapp.com/login");
        WebElement submitButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        captureElement(submitButton, "submitButton");
        capturePage();
    }
}
