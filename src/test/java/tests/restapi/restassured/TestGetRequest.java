package test.java.tests.restapi.restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class TestGetRequest {

    @Test
    void testPing() throws URISyntaxException {
        Response response = given()
                .when()
                .get(new URI("http://192.168.0.126:8080/laptop-bag/webapi/api/ping/hi"));
        System.out.println(response.asString());
    }

    @Test
    void testGetReqWithJSONRes() throws URISyntaxException {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("http://192.168.0.126:8080/laptop-bag/webapi/api/all"));
        System.out.println(response.asString());
    }

    @Test
    void testGetReqWithXMLRes() throws URISyntaxException {
        Response response = given()
                .accept(ContentType.XML)
                .when()
                .get(new URI("http://192.168.0.126:8080/laptop-bag/webapi/api/all"));
        System.out.println(response.asString());
    }

    @Test
    void testGetReqWith200OKUsingThenReturn() throws URISyntaxException {
        int returnStatusCode = given()
                .when()
                .get(new URI("http://192.168.0.126:8080/laptop-bag/webapi/api/all"))
                .thenReturn()
                .statusCode();
        Assert.assertEquals(returnStatusCode, HttpStatus.SC_OK);
    }

    @Test
    void testGetReqWith200OKUsingThen() throws URISyntaxException {
        given()
                .when()
                .get(new URI("http://192.168.0.126:8080/laptop-bag/webapi/api/all"))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void testGetReqWithValidID() throws URISyntaxException {
        given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("http://192.168.0.126:8080/laptop-bag/webapi/api/find/126"))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void testGetReqWithInValidID() throws URISyntaxException {
        given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("http://192.168.0.126:8080/laptop-bag/webapi/api/find/0"))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
