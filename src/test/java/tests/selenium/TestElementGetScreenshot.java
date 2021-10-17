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
import test.java.helper.ReportAllureHelper;
import test.java.helper.Retry;

import java.io.IOException;

@Listeners({Listener.class})
public class TestElementGetScreenshot extends Base {

    private ReportAllureHelper allureHelper = new ReportAllureHelper();

    @Test(enabled = false, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the element capture as an image using getScreenshotAs() method Selenium 4 feature.")
    public void testElementGetScreenshot() throws IOException {
        getDriver().get("http://the-internet.herokuapp.com/login");
        WebElement submitButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        WebElement usernameInputField = getDriver().findElement(By.xpath("//*[@id='username']"));
        allureHelper.report("1. Find Username field.", usernameInputField);
        allureHelper.report("2. Find Login button.", submitButton);
    }

//    @Test(enabled = true, retryAnalyzer = Retry.class)
//    @Severity(SeverityLevel.NORMAL)
//    @Description("Verify the element capture as an image using getScreenshotAs() method Selenium 4 feature.")
//    public void testPlayerElementGetScreenshot() throws IOException {
//        getDriver().get("https://platform-staging.wurl.com/main/episode/1102754/builder");
//        getDriver().findElement(By.xpath("//*[@id='sign-in-button']")).click();
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofMinutes(8));
//
//        getDriver().findElement(By.cssSelector(".panel #signInFormUsername")).sendKeys("wurl.automation.2+user1@gmail.com");
//        getDriver().findElement(By.cssSelector(".panel #signInFormPassword")).sendKeys("x");
//        getDriver().findElement(By.cssSelector(".panel .cognito-asf > .btn")).click();
//        sleep(4);
//        getDriver().get("https://platform-staging.wurl.com/main/episode/1102754/builder");
//        getDriver().findElement(By.xpath("//*[contains(@class,'roxOverideButton')]")).click();
//        getDriver().findElement(By.xpath("//*[@placeholder='Search Flags']")).sendKeys("preview");
//        sleep(4);
//        getDriver().findElement(By.xpath("//*[@class='roxFlags']//*[text()='Original (false)']")).click();
//        getDriver().findElement(By.xpath("//*[@class='roxFlags']//*[@value='true']")).click();
//        getDriver().navigate().refresh();
//        sleep(8);
//
//        WebElement previewPlayerContainer = getDriver().findElement(By.xpath("//*[@id='content-episode-wide-builder-container']"));
//        Allure.addAttachment("Preview Player Container", new FileInputStream(previewPlayerContainer.getScreenshotAs(OutputType.FILE)));
//
//        WebElement previewPlayer = getDriver().findElement(By.xpath("(//*[contains(@id,'html5_api')])[2]"));
//        Allure.addAttachment("Preview Player", new FileInputStream(previewPlayer.getScreenshotAs(OutputType.FILE)));
//
//        allureHelper.report("1. Preview Player Container.", previewPlayerContainer);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='signInFormUsername']")));
//    }

}
