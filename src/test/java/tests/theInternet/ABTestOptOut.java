package test.java.tests.theInternet;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;

@Listeners({Listener.class})
public class ABTestOptOut extends Base {

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test template.")
    public void test() {
        getDriver().get("http://the-internet.herokuapp.com/abtest");
        String headingText = getDriver().findElement(By.tagName("h3")).getText();
        Assert.assertTrue(headingText.startsWith("A/B Test"));
    }

}
