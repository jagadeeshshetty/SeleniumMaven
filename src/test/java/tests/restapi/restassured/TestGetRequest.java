package test.java.tests.restapi.restassured;

import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.when;

public class TestGetRequest {

    @Test
    void testGet() throws URISyntaxException {
        when().get(new URI("http://192.168.0.126:8080/laptop-bag/webapi/api/ping/hi"));
    }
}
