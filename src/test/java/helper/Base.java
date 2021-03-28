package test.java.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;

public class Base {
    protected WebDriver driver;
    public static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    @BeforeMethod
    public void globalSetup(ITestContext context) {
        step("Running Test on '" + System.getProperty("os.name") + "' OS.");
        logger.info("Running Test on '" + System.getProperty("os.name") + "' OS.");
        step("Browser: Chrome");
        logger.info("Browser: Chrome");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        step("Headless mode: True");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        if (driver != null) {
            step("Driver instance: " + driver.toString());
            logger.info("Driver instance: " + driver.toString());
        }
        driver.manage().timeouts().pageLoadTimeout(48, TimeUnit.SECONDS);
        context.setAttribute("WebDriver", driver);
        context.setAttribute("logger", logger);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        step("Cleaning up driver instance: " + driver.toString());
        logger.info("Cleaning up driver instance: " + driver.toString());
        driver.quit();
    }

}
