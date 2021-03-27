package test.java.tests.testng;

import org.testng.annotations.*;

/**
 * RESULT
 * ------
 * BaseClass's Before Class method
 * ChildClass's Before Class method
 * BaseClass's Before method
 * ChildClass's Before method
 * ===== Executing actual test ======
 * ChildClass's After method
 * BaseClass's After method
 * ChildClass's After Class method
 * BaseClass's After Class method
 */
public class ChildClass extends BaseRemoveClass {
    @BeforeMethod
    public void beforeChildMethod() {
        System.out.println("ChildClass's Before method");
    }

    @AfterMethod
    public void afterChildMethod() {
        System.out.println("ChildClass's After method");
    }

    @BeforeClass
    public void beforeChildClass() {
        System.out.println("ChildClass's Before Class method");
    }

    @AfterClass
    public void afterChildClass() {
        System.out.println("ChildClass's After Class method");
    }

    @Test
    public void testCase() {
        System.out.println("===== Executing actual test ======");
    }
}
