package test.java.tests.restapi.restassured;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

public class BaseClass {

    @BeforeClass
    static void setUp() {
        baseURI = "http://192.168.0.126";
        port = 8080;
        basePath = "laptop-bag/webapi/api";
    }
}
