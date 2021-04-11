package test.java.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReportHelper {

    public static void setEnvironment(String browserName, String browserVersion) {
        //Instantiating the properties file
        Properties props = new Properties();
        props.put("Platform", System.getProperty("os.name"));
        props.put("Browser Name and Version", browserName + ' ' + browserVersion);
        props.put("Browser Name and Version", capitalizeFirstCharacter(browserName) + ' ' + browserVersion);
        props.put("Tester Name", System.getProperty("user.name"));
        props.put("JAVA", System.getProperty("java.vm.name") + ' ' + System.getProperty("java.runtime.version"));
        props.put("Project Directory", System.getProperty("user.dir"));

        String dirPath = System.getProperty("user.dir") +
                System.getProperty("file.separator") +
                "target" +
                System.getProperty("file.separator") +
                "allure-results" +
                System.getProperty("file.separator");
        String filePath = dirPath + "environment.properties";
        File file = new File(filePath);
        File dir = new File(dirPath);

        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream(filePath);
            props.store(outputStream, "Environment properties file.");

            if (file.exists()) {
                System.out.println("Properties file created. Path: " + filePath);
            } else {
                System.out.println("Properties file not created. Path: " + filePath);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String capitalizeFirstCharacter(String value) {
        String firstLetter = value.substring(0, 1);
        String remainingLetters = value.substring(1, value.length());
        return firstLetter.toUpperCase() + remainingLetters;
    }
}
