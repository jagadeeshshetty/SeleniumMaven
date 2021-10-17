package test.java.tests.testng;

import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * BeforeGroups annotated method will run before any of the test method of the specified group is executed.
 * <p>
 * Scenario 1: Run only Sanity tests.
 * Scenario 2: Run only P1 tests.
 * Scenario 3: Run both Sanity and P1 tests.
 * Scenario 4: Run tests which doesn't belongs to any group.
 * <p>
 * testng.xml
 * <pre>
 *     <?xml version="1.0" encoding="UTF-8"?>
 * <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
 * <suite name="Testing BeforeGroups Annotation">
 *     <test name="Scenario 1: Run only Sanity tests.">
 *         <groups>
 *             <run>
 *                 <include name="sanity"></include>
 *                 <exclude name="P1"></exclude>
 *             </run>
 *         </groups>
 *         <classes>
 *             <class name="test.java.tests.testng.TestBeforeGroups"/>
 *         </classes>
 *     </test> <!-- Test -->
 *
 *     <test name="Scenario 2: Run only P1 tests.">
 *         <groups>
 *             <run>
 *                 <include name="P1"></include>
 *                 <exclude name="sanity"></exclude>
 *             </run>
 *         </groups>
 *         <classes>
 *             <class name="test.java.tests.testng.TestBeforeGroups"/>
 *         </classes>
 *     </test> <!-- Test -->
 *
 *     <test name="Scenario 3: Run both Sanity and P1 tests.">
 *         <groups>
 *             <run>
 *                 <include name="P1"></include>
 *                 <include name="sanity"></include>
 *             </run>
 *         </groups>
 *         <classes>
 *             <class name="test.java.tests.testng.TestBeforeGroups"/>
 *         </classes>
 *     </test> <!-- Test -->
 *
 *     <test name="Scenario 4: Run tests which doesn't belongs to any group.">
 *         <groups>
 *             <run>
 *                 <exclude name="P1"></exclude>
 *                 <exclude name="sanity"></exclude>
 *             </run>
 *         </groups>
 *         <classes>
 *             <class name="test.java.tests.testng.TestBeforeGroups"/>
 *         </classes>
 *     </test> <!-- Test -->
 *
 * </suite> <!-- Suite -->
 * </pre>
 */
public class TestGroups {
    @BeforeGroups(groups = "sanity")
    public void setupSanity() {
        System.out.println("START: " + new Throwable().getStackTrace()[0].getMethodName());
    }

    @AfterGroups(groups = "sanity")
    public void teardownSanity() {
        System.out.println("COMPLETE: " + new Throwable().getStackTrace()[0].getMethodName() + "\n");
    }

    @BeforeGroups(groups = "P1")
    public void setupSanityP1(ITestContext testContext) {
        System.out.println("START: " + new Throwable().getStackTrace()[0].getMethodName());
    }

    @AfterGroups(groups = "P1")
    public void teardownSanityP1() {
        System.out.println("COMPLETE: " + new Throwable().getStackTrace()[0].getMethodName() + "\n");
    }

    @Test(groups = {"sanity"})
    public void sanityTest() {
        System.out.println(new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test(groups = {"P1"})
    public void p1Test() {
        System.out.println(new Throwable().getStackTrace()[0].getMethodName());
    }

}
