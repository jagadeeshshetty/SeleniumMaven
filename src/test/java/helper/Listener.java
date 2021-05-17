package test.java.helper;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileInputStream;

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
        System.out.println("I am in onTestStart() method " + getTestMethodName(iTestResult) + " start");
        try {
            System.out.println("---- START '" + getTestMethodName(iTestResult) + "' TEST EXECUTION RECORD ----");
            CaptureTestExecutionHelper.startRecording(getTestMethodName(iTestResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess() method " + getTestMethodName(iTestResult));
        try {
            System.out.println("---- STOP '" + getTestMethodName(iTestResult) + "' TEST EXECUTION RECORD ----");
            CaptureTestExecutionHelper.stopRecording();
            Allure.addAttachment(getTestMethodName(iTestResult) + " Screencast: ", new FileInputStream("./recordings/testOneValidLoginOne-2021_05_17_19_12_07.avi"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    WebDriver driver = null;
    Logger logger = null;

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure() method " + getTestMethodName(iTestResult));
        try {
            System.out.println("---- STOP '" + getTestMethodName(iTestResult) + "' TEST EXECUTION RECORD ----");
            CaptureTestExecutionHelper.stopRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        System.out.println("I am in onStart() method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", BaseRemove.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish() method " + iTestContext.getName());
    }
}
