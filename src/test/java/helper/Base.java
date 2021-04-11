package test.java.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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

    ChromeOptions chromeOptions;

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setup(ITestContext context) {
        step("Start time: " + getDateTime("yyyy/MM/dd HH:mm:ss"));

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "chromeHeadless":
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
            step("Driver instance: " + getDriver().toString());

            getDriver().manage().timeouts().pageLoadTimeout(GlobalConfig.pageLoadTimeoutInSec, TimeUnit.SECONDS);

            context.setAttribute("WebDriver", getDriver());
            context.setAttribute("logger", logger);
        }
    }

    @AfterMethod
    public void teardown() {
        ReportHelper.setEnvironment(getDriverCapabilities("browserName"), getDriverCapabilities("browserVersion"));
        step("Cleaning up driver instance: " + getDriver().toString());
        getDriver().quit();
        step("Complete time: " + getDateTime("yyyy/MM/dd HH:mm:ss"));
    }

    public String getDateTime(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Get the desired capabilities of the created WebDriver session.
     * <p>
     * See <a href="hhttps://w3c.github.io/webdriver/#capabilities">W3C WebDriver specification</a>
     * for more details.
     * <p> Usage
     * <br>String browserName = cap.getBrowserName();
     * <br>String browserVersion = (String) cap.getCapability("browserVersion");
     * <br>String osName = Platform.fromString((String) cap.getCapability("platformName")).name().toLowerCase();
     *
     * @return The capabilities.
     * <p>
     * EX:
     * acceptInsecureCerts: false, javascriptEnabled: true, networkConnectionEnabled: false, setWindowRect: true,
     * platformName: WINDOWS, browserName: chrome, browserVersion: 89.0.4389.114,
     * timeouts: {implicit: 0, pageLoad: 300000, script: 30000},
     */
    private String getDriverCapabilities(String capName) {
        Capabilities cap = ((RemoteWebDriver) getDriver()).getCapabilities();
        return (String) cap.getCapability(capName);
    }

}
