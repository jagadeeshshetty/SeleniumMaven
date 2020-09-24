package pageobjects;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import tests.TestLogin;

public class ChromeDevTools extends BasePage {
    private static final Logger logger = LoggerHelper.getLogger(TestLogin.class);
    private WebDriver driver;
    private static DevTools chromeDevTools;

    public ChromeDevTools(WebDriver driver) {
        super(driver);
    }

    public void emulateDeviceTest() {

    }
}
