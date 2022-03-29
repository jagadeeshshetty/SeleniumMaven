package test.java.tests.selenium;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;

import java.io.IOException;
import java.util.List;

@Listeners({Listener.class})
public class Java8Features extends Base {

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the 'Due' column sort functionality in an ascending order.")
    public void testDuesColumnAscendingSort() throws IOException {
        getDriver().get("https://automationqahub.com/how-to-use-new-java-features-in-the-test-automation/");
        allureHelper.report("1. Access the test URL.");

        List<WebElement> archiveDates = getDriver().findElements(By.xpath("//*[@class='widget widget_archive']//a"));
        allureHelper.report("2. List all the archive dates.");

        for (WebElement archiveDate : archiveDates) {
            allureHelper.report("....Archive Dates: " + archiveDate.getText(), archiveDate);
        }
    }
}
