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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Listeners({Listener.class})
public class Java8Features extends Base {

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the 'Due' column sort functionality in an ascending order.")
    public void testDuesColumnAscendingSort() throws IOException {
        getDriver().get("https://automationqahub.com/how-to-use-new-java-features-in-the-test-automation/");
        allureHelper.report("1. Access the test URL.");

        List<WebElement> archiveDates = getDriver().findElements(By.xpath("//*[@class='widget widget_archive']//a"));

        // For Loop
        allureHelper.report("2. List all the archive dates using 'for()' loop.");
        for (WebElement archiveDate : archiveDates) {
            allureHelper.report("....Archive Dates: " + archiveDate.getText(), archiveDate);
        }

        // For Each Loop
        allureHelper.report("3. List all the archive dates using 'forEach()' loop.");
        archiveDates.forEach(date -> {
            try {
                allureHelper.report("....Archive Dates: " + date.getText(), date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.MINOR)
    @Description("Demo of Iterating Maps using 'for' loop.")
    public void testMapsWithForAndForEachLoop() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Chrome", 99);
        map.put("Chrome", 98);
        map.put("Firefox", 98);
        map.put("Safari", 15);

        // For loop
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            System.out.println("Key : " + k + ", Value : " + v);
        }

        // For Each loop
        map.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));
    }
    
}
