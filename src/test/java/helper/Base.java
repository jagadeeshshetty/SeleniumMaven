package test.java.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;
import static test.java.helper.GlobalConfig.browser;

public class Base {

    public static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    ChromeOptions chromeOptions;
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setup(ITestContext context) {
        System.out.println(">>>>>>>>> before");
//        WebDriverManager.chromedriver().setup();
//        driver.set(new ChromeDriver());
        step("Running Test on '" + System.getProperty("os.name") + "' OS.");
        logger.info("Running Test on '" + System.getProperty("os.name") + "' OS.");
        step("Browser: " + browser.toUpperCase());
        logger.info("Browser: " + browser.toUpperCase());

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "chromeHeadless":
                step("Browser: Chrome Headless");
                logger.info("Browser: Chrome Headless");
                WebDriverManager.chromedriver().setup();
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--headless");
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
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
            case "opera":
                WebDriverManager.operadriver().setup();
                driver.set(new OperaDriver());
                break;
            case "safari":
                driver.set(new SafariDriver());
                break;
            default:
                step("No proper browser specified.");
                logger.info("No proper browser specified.");
        }
        if (driver != null) {
            step("Driver instance: " + driver.toString());
            logger.info("Driver instance: " + driver.toString());
            getDriver().manage().timeouts().pageLoadTimeout(48, TimeUnit.SECONDS);
            context.setAttribute("WebDriver", getDriver());
            context.setAttribute("logger", logger);
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void teardown() {
        System.out.println(">>>>>>>>> after");
        step("Cleaning up driver instance: " + driver.toString());
        logger.info("Cleaning up driver instance: " + driver.toString());
        getDriver().quit();
    }
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public void setupBrowser(ITestContext context) {
//        step("Running Test on '" + System.getProperty("os.name") + "' OS.");
//        logger.info("Running Test on '" + System.getProperty("os.name") + "' OS.");
//        step("Browser: " + browser.toUpperCase());
//        logger.info("Browser: " + browser.toUpperCase());
//
//        switch (browser) {
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//                break;
//            case "chromeHeadless":
//                step("Browser: Chrome Headless");
//                logger.info("Browser: Chrome Headless");
//                WebDriverManager.chromedriver().setup();
//                chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--no-sandbox");
//                chromeOptions.addArguments("--disable-dev-shm-usage");
//                chromeOptions.addArguments("--headless");
//                driver = new ChromeDriver(chromeOptions);
//                break;
//            case "chrome":
//                WebDriverManager.chromedriver().setup();
//                chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--no-sandbox");
//                chromeOptions.addArguments("--disable-dev-shm-usage");
//                driver = new ChromeDriver(chromeOptions);
//                break;
//            case "edge":
//                WebDriverManager.edgedriver().setup();
//                driver = new EdgeDriver();
//                break;
//            case "ie":
//                WebDriverManager.iedriver().setup();
//                driver = new InternetExplorerDriver();
//                break;
//            case "opera":
//                WebDriverManager.operadriver().setup();
//                driver = new OperaDriver();
//                break;
//            case "safari":
//                driver = new SafariDriver();
//                break;
//            default:
//                step("No proper browser specified.");
//                logger.info("No proper browser specified.");
//        }
//        if (driver != null) {
//            step("Driver instance: " + driver.toString());
//            logger.info("Driver instance: " + driver.toString());
//            driver.manage().timeouts().pageLoadTimeout(48, TimeUnit.SECONDS);
//            context.setAttribute("WebDriver", driver);
//            context.setAttribute("logger", logger);
//        }
//    }
//
//    public void closeBrowser() throws Exception {
//        step("Cleaning up driver instance: " + driver.toString());
//        logger.info("Cleaning up driver instance: " + driver.toString());
//        driver.quit();
//    }
//
//    public WebDriver getChromeDriver() {
//        WebDriverManager.chromedriver().setup();
//        return new ChromeDriver();
//    }

}
