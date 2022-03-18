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

import java.util.List;

import static io.qameta.allure.Allure.step;

@Listeners({Listener.class})
public class TestCheckboxes extends Base {

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the default state of checkboxes.")
    public void testCheckboxesDefaultState() {
        step("1. Access the test URL.");
        getDriver().get("http://the-internet.herokuapp.com/checkboxes");
        // //input[@type='checkbox']
        List<WebElement> checkboxes = getDriver().findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            System.out.println(checkbox);
            System.out.println(checkbox.getAttribute("checked"));
        }
    }

}
