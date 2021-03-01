package test.java.helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class GenerateScreenshot {
    public static String capture(WebDriver driver, String screenShotName) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenShotName + timestamp.getTime() + ".png";
        System.out.println(dest);
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        return dest;
    }

    public static void clearDir() {
        try {
            // create a new file object
            File directory = new File(System.getProperty("user.dir") + "\\ErrorScreenshots");

            // list all the files in an array
            File[] files = directory.listFiles();

            // delete each file from the directory
            for (File file : files) {
                System.out.println(file + " deleted.");
                file.delete();
            }

            // delete the directory
            if (directory.delete()) {
                System.out.println("Directory Deleted");
            } else {
                System.out.println("Directory not Found");
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
