package test.java.helper;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment
    public byte[] saveFailureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("\n[😎 START] I am in onTestStart() method " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("[🚩 DONE] I am in onTestSuccess() method " + getTestMethodName(iTestResult) + "\n");
    }

    WebDriver driver = null;
    Logger logger = null;

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure() method " + getTestMethodName(iTestResult));
        Object testClass = iTestResult.getInstance();
        ITestContext context = iTestResult.getTestContext();
        driver = (WebDriver) context.getAttribute("WebDriver");
        logger = (Logger) context.getAttribute("logger");
        if (driver instanceof WebDriver) {
            logger.info("Screenshot captured for test case: " + getTestMethodName(iTestResult));
            saveFailureScreenshot(driver);
        }
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped() method " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("I am in onTestFailedButWithinSuccessPercentage() method " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult iTestResult) {
        System.out.println("I am in onTestFailedWithTimeout() method " + getTestMethodName(iTestResult));
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println(">>>>>>>> I am in onStart() method " + iTestContext.getName() + "<<<<<<<<");
        iTestContext.setAttribute("WebDriver", BaseRemove.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println(">>>>>>>> I am in onFinish() method " + iTestContext.getName() + "<<<<<<<<");
    }
}
