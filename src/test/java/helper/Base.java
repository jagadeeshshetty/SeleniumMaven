package test.java.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;
import static test.java.helper.GlobalConfig.browser;

public class Base {

    public static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static ReportAllureHelper allureHelper = new ReportAllureHelper();

    ChromeOptions chromeOptions;

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setup(ITestContext context) {
        step("Start time: " + getDateTime("d MMM uuuu HH:mm:ss"));
        step("Running Test on '" + System.getProperty("os.name") + "' OS.");
        step("JVM Version: " + System.getProperty("java.version"));
        step("Browser: " + browser.toUpperCase());
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "chromeHeadless":
//                WebDriverManager.chromedriver().setup();
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--headless");
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "chrome":
//                WebDriverManager.chromedriver().setup();
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver.set(new InternetExplorerDriver());
                break;
            case "safari":
                driver.set(new SafariDriver());
                break;
            default:
                step("No proper browser specified.");
                logger.info("No proper browser specified.");
        }
        if (driver != null) {
            step("Driver instance: " + getDriver().toString());

            getDriver().manage().timeouts().pageLoadTimeout(GlobalConfig.pageLoadTimeoutInSec, TimeUnit.SECONDS);
            getDriver().manage().timeouts().implicitlyWait(GlobalConfig.implicitlyWaitTimeoutInSec, TimeUnit.SECONDS);

            context.setAttribute("WebDriver", getDriver());
            context.setAttribute("logger", logger);
        }
    }

    @AfterMethod
    public void teardown() {
        step("Cleaning up driver instance: " + getDriver().toString());
        getDriver().quit();
        step("Complete time: " + getDateTime("d MMM uuuu HH:mm:ss"));
    }

    public String getDateTime(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void sleep(int sec) {
        logger.info("For " + sec + " sec.");
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
