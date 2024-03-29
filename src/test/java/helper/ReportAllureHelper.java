package test.java.helper;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ReportAllureHelper extends Base {

    public void focusElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'outline: 4px solid yellow; outline-offset: 0px;');", element);
    }

    public void resetFocusElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'outline: 0px; outline-offset: 0px;');", element);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void report(String title, WebElement element) throws IOException {
        focusElement(element);
//        step(title);
        Allure.addAttachment(title, new FileInputStream(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE)));
        resetFocusElement(element);
    }

    public void report(String title) throws IOException {
        WebElement element = getDriver().findElement(By.xpath("//body"));
        focusElement(element);
        Allure.addAttachment(title, new FileInputStream(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE)));
        resetFocusElement(element);
    }

}
