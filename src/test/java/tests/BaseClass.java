package test.java.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    private WebDriver driver;
    public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();

    public WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println(driver.toString());
        driver.manage().timeouts().pageLoadTimeout(48, TimeUnit.SECONDS);
        tDriver.set(driver);
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tDriver.get();
    }
}
