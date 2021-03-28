package test.java.tests.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseRemoveClass {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BaseClass's Before method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("BaseClass's After method");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("BaseClass's Before Class method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("BaseClass's After Class method");
    }
}
