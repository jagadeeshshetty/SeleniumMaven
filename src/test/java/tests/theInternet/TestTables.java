package test.java.tests.theInternet;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.helper.Base;
import test.java.helper.Listener;
import test.java.helper.Retry;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Listeners({Listener.class})
public class TestTables extends Base {
    //    private LoginPage loginPage;

    @Test(priority = 1, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the 'Due' column sort functionality in an ascending order.")
    public void testDuesColumnAscendingSort() throws IOException {
        step("1. Access the test URL.");
        getDriver().get("http://the-internet.herokuapp.com/tables");

        step("2. Click on Due column header.");
        getDriver().findElement(By.cssSelector("#table1 thead tr th:nth-of-type(4)")).click();

        List<WebElement> dues = getDriver().findElements(By.cssSelector("#table1 tbody tr td:nth-of-type(4)"));
        List<Double> dueValues = new LinkedList<Double>();
        for (WebElement element : dues) {
            dueValues.add(Double.parseDouble(element.getText().replace("$", "")));
        }

        step("3. Verify table rows should be sorted in ascending order as per the 'Due' column values.");
        for (int counter = 0; counter < dueValues.size() - 1; counter++) {
            Assert.assertTrue(dueValues.get(counter) <= dueValues.get(counter + 1));
            step("....Verified " + (counter + 1) + " row '" + dueValues.get(counter) + "' value is less than or equal to " + (counter + 2) + " row '" + dueValues.get(counter + 1) + "' value.");
        }

        allureHelper.report("Screenshot", getDriver().findElement(By.cssSelector("#table1 thead tr th:nth-of-type(4)")));
    }

    @Test(priority = 2, enabled = true, retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the 'Due' column sort functionality in an ascending order with step level screenshot.")
    public void testDuesColumnAscendingSortWithStepLevelScreenshot() throws IOException {
        getDriver().get("http://the-internet.herokuapp.com/tables");
        allureHelper.report("1. Access the test URL.", getDriver().findElement(By.cssSelector("body")));

        allureHelper.report("2. Click on Due column header.", getDriver().findElement(By.cssSelector("#table1 thead tr th:nth-of-type(4)")));
        getDriver().findElement(By.cssSelector("#table1 thead tr th:nth-of-type(4)")).click();

        List<WebElement> dues = getDriver().findElements(By.cssSelector("#table1 tbody tr td:nth-of-type(4)"));
        List<Double> dueValues = new LinkedList<Double>();
        for (WebElement element : dues) {
            dueValues.add(Double.parseDouble(element.getText().replace("$", "")));
        }

        allureHelper.report("3. Verify table rows should be sorted in ascending order as per the 'Due' column values.", getDriver().findElement(By.cssSelector("#table1 thead tr th:nth-of-type(4)")));
        for (int counter = 0; counter < dueValues.size() - 1; counter++) {
            Assert.assertTrue(dueValues.get(counter) <= dueValues.get(counter + 1));
            allureHelper.report("....Verified " + (counter + 1) + " row '" + dueValues.get(counter) + "' value is less than or equal to " + (counter + 2) + " row '" + dueValues.get(counter + 1) + "' value.", dues.get(counter));
        }
    }

}
