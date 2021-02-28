package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import helper.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.helper.GenerateScreenshot;
import test.java.pageobjects.Login;

import java.io.File;
import java.io.IOException;

/**
 * https://howtodoinjava.com/testng/how-to-execute-testng-tests-with-maven-build/
 */
public class TestLoginNG {

    private WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestLoginNG.class);
    private Login login;

    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/MyOwnReport.html");
    ExtentReports extent = new ExtentReports();
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", "Mac High Sierra");
        extent.setSystemInfo("HostName", "Krishna");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Author", "Krishna");
//        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting Demo Report");
        htmlReporter.config().setReportName("My Own Report");
//        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

        logger.info("Setting up Chrome driver.");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println(driver.toString());
        login = new Login(driver);
    }

    @Test
    public void succeeded() {
        test = extent.createTest("succeeded", "Verify login with valid credentials.");
        test.log(Status.INFO, MarkupHelper.createLabel("Verifying login with valid credentials.", ExtentColor.BLUE));
        login.with("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(login.successMessagePresent(), "success message not present");
        test.log(Status.INFO, MarkupHelper.createLabel("Login with valid credentials passed.", ExtentColor.GREEN));
    }

    @Test
    public void failed() {
        test = extent.createTest("failed", "Verify login with invalid credentials.");
        login.with("tomsmith", "bad password");
        Assert.assertTrue(login.failureMessagePresent(), "failure message wasn't present after providing bogus credentials");
    }

    @Test
    public void knownFailure() {
        test = extent.createTest("knownFailure", "Expected failure to check screenshot.");
        login.with("tomsmith", "bad password");
        Assert.assertTrue(login.successMessagePresent(), "success message not present");
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = GenerateScreenshot.capture(driver, "SampleScreenshot");
            test.fail(result.getThrowable());
            test.fail("Snapshot of screenshot below:" + test.addScreenCaptureFromPath(screenshotPath));
        } else {
            test.pass(result.getName() + " Testcase Passed");
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        logger.info("Cleaning up driver instance.");
        driver.quit();
        extent.flush();
    }

    /**
     * Based on the OS, we get browser driver location.
     *
     * @param driverName a string. ex: "chromedriver"
     * @return the relative (partial) path of the driver. ex: driver/chromedriver
     */
    private static File getDriver(String driverName) {
        if (System.getProperty("os.name").contains("Mac OS X")) {
            return new File(new File("driver"), driverName);
        } else {
            return new File(new File("driver"), driverName + ".exe");
        }
    }
}
