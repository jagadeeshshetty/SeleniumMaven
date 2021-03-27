package test.java.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import test.java.tests.theInternet.TestLogin;

import java.util.concurrent.TimeUnit;

public class Base {
    protected WebDriver driver;
    public static final Logger logger = helper.LoggerHelper.getLogger(TestLogin.class);

    @BeforeMethod
    public void globalSetup() {
        logger.info(System.getProperty("os.name"));
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        if (driver != null) logger.info(driver.toString());
        driver.manage().timeouts().pageLoadTimeout(48, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        logger.info("Cleaning up driver instance.");
        driver.quit();
    }

}
