package test.java.tests.selenium.interview;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;

import java.io.IOException;

@Listeners({Listener.class})
public class TestCases extends Base {

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Perform the back, forward and refresh actions.")
    public void testForwardBackRefreshActions() throws IOException {
        // https://www.javatpoint.com/selenium-webdriver-navigation-commands
        // https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver#:~:text=Implicit%20Wait%20directs%20the%20Selenium,which%20the%20browser%20is%20open.
    }

}
