package test.java.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Test {

    public static void main(String[] args) {
        //Instantiating the properties file
        Properties props = new Properties();
        props.putIfAbsent("Browser", "Chrome");

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

//        System.out.println(System.getProperties());
        // java.specification.version=11,
        // java.vm.vendor=AdoptOpenJDK, java.vendor.url=https://adoptopenjdk.net/,
        // os.name=Windows 10,
        // java.vm.name=OpenJDK 64-Bit Server VM, java.runtime.name=OpenJDK Runtime Environment, java.vm.specification.vendor=Oracle Corporation, java.runtime.version=11.0.10+9, java.version.date=2021-01-19, java.home=C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot,
        // file.separator=\,
        // user.name=Jagadeesh C (L), os.version=10.0,  file.encoding=UTF-8,
        // user.dir=C:\Users\jagadeesh\Documents\SeleniumMaven, os.arch=amd64,
        // java.class.version=55.0}

    }

}