package test.java.tests.theInternet;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.ReportAllureHelper;
import test.java.helper.Retry;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

/**
 * Run:
 * mvn clean test -Dbrowser=chrome -DmaxRetryCount=0 -Dsurefire.suiteXmlFiles=testng-dev.xml && allure serve target/allure-results
 */
@Listeners({Listener.class})
public class ABTestOptOut extends Base {

    @Test(priority = 3, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("The test will verify the ABTest scenario by forge an opt-out cookie after visiting the page.")
    public void Test_With_Cookie_After_Visiting_Page() {
        getDriver().get("http://the-internet.herokuapp.com/abtest");
        String headingText = getDriver().findElement(By.tagName("h3")).getText();
        Assert.assertTrue(headingText.startsWith("A/B Test"));

        // forge an opt-out cookie
        getDriver().manage().addCookie(new Cookie("optimizelyOptOut", "true"));
        getDriver().navigate().refresh();
        System.out.println(getDriver().findElement(By.tagName("h3")).getText());
        Assert.assertEquals(getDriver().findElement(By.tagName("h3")).getText(), "No A/B Test");
    }

    private ReportAllureHelper allureHelper = new ReportAllureHelper();

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("The test will verify the ABTest scenario by forge an opt-out cookie.")
    public void Test_With_Cookie_Before_Visiting_Page() throws IOException {
        step("1. Visit the \"http://the-internet.herokuapp.com\" page.");
        getDriver().get("http://the-internet.herokuapp.com");

        step("2. Forge an opt-out cookie.");
        getDriver().manage().addCookie(new Cookie("optimizelyOptOut", "true"));

        step("3. Visit the \"http://the-internet.herokuapp.com/abtest\" abtest link.");
        getDriver().get("http://the-internet.herokuapp.com/abtest");

        step("4. Get the heading text.");
        String headingText = getDriver().findElement(By.tagName("h3")).getText();

        step("5. Verify the heading text should be 'No A/B Test' value.");
        allureHelper.report("5.1 Heading text", getDriver().findElement(By.tagName("h3")));
        Assert.assertEquals(headingText, "No A/B Test");
    }

    @Test(priority = 2, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("The test will verify the ABTest scenario by passing URL parameter.")
    public void Test_With_Opt_Out_Url() throws IOException {
        step("1. Visit the \"http://the-internet.herokuapp.com/abtest?optimizely_opt_out=true\" page.");
        getDriver().get("http://the-internet.herokuapp.com/abtest?optimizely_opt_out=true");

        step("2. Dismiss the alert.");
        getDriver().switchTo().alert().dismiss();

        step("3. Get the heading text.");
        String headingText = getDriver().findElement(By.tagName("h3")).getText();

        step("4. Verify the heading text should be 'No A/B Test' value.");
        allureHelper.report("4.1 Heading text", getDriver().findElement(By.tagName("h3")));
        Assert.assertEquals(headingText, "No A/B Test");
    }

}
