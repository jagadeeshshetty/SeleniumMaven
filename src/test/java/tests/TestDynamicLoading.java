package test.java.tests;

import helper.LoggerHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.java.pageobjects.DynamicLoading;

import static org.junit.Assert.assertTrue;

public class TestDynamicLoading {

    private WebDriver driver;
    private static final Logger logger = LoggerHelper.getLogger(TestDynamicLoading.class);

    private DynamicLoading dynamicLoading;

    @Before
    public void setUp() {
        logger.info("Setting up Chrome driver.");
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.gecko.driver",
                        System.getProperty("user.dir") + "/vendor/geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (System.getProperty("os.name").contains("Mac OS X")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/vendor/macOS/85/chromedriver");
                driver = new ChromeDriver();
            } else {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }
            if (driver != null) logger.info(driver.toString());
        } catch (Exception e) {
            logger.error("Chrome driver init failed.", e);
        }
        dynamicLoading = new DynamicLoading(driver);
    }


    @Test
    public void hiddenElementLoads() {
        dynamicLoading.loadExample("1");
        assertTrue("finish text didn't display after loading",
                dynamicLoading.finishTextPresent());
    }

    @Test
    public void elementAppears() {
        dynamicLoading.loadExample("2");
        assertTrue("finish text didn't render after loading",
                dynamicLoading.finishTextPresent());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            logger.info("Cleaning up driver instance.");
            driver.quit();
        }
    }
}
