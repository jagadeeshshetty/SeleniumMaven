package test.java.tests.testng;

import org.testng.annotations.*;

/**
 * RESULT
 * ------
 * Before Suite method
 * Before Test method
 * Before Class method
 * Before Method
 * Test method
 * After Method
 * After Class method
 * After Test method
 * After Suite method
 */
public class BeforeAfterAnnotations {
    @Test
    public void testCaseOne() {
        System.out.println("Test method one");
    }

    @Test
    public void testCaseTwo() {
        System.out.println("Test method two");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite method");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite method");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test method");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test method");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class method");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }

}
