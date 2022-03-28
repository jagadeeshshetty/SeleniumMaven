package test.java.tests.theInternet;

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
public class TestCheckboxes extends Base {

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the default state of checkboxes.")
    public void testCheckboxesDefaultState() throws IOException {
        allureHelper.report("1. Access the test URL.", getDriver().findElement(By.cssSelector("body")));
        getDriver().get("http://the-internet.herokuapp.com/checkboxes");

        // Using two approach we can handle Checkboxes
        // 1. Using Attribute lookup.
        // 2. Using isSelected() Selenium method.

        // 1. Using Attribute lookup.
        // //input[@type='checkbox']
        List<WebElement> checkboxes = getDriver().findElements(By.xpath("//input[@type='checkbox']"));

        allureHelper.report("2.Using 'Attribute lookup' method to verify the Checkboxes checked or not.");
        for (WebElement checkbox : checkboxes) {
            allureHelper.report(".... Is the Checkbox checked? ", checkbox);
//            step("2. Click on Due column header.");
            System.out.println(checkbox);
            System.out.println(checkbox.getAttribute("checked"));
        }

        // 2. Using isSelected() Selenium method.
    }

}
