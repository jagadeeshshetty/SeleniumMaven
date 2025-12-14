package test.java.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import test.java.tests.TestLoginPageGA;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseRemove {

    private WebDriver driver;
    public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();
    private static final Logger logger = helper.LoggerHelper.getLogger(TestLoginPageGA.class);

    public WebDriver initDriver() {
        System.out.println(System.getProperty("os.name"));
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        if (driver != null) logger.info(driver.toString());
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(48));
        tDriver.set(driver);
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tDriver.get();
    }
}
