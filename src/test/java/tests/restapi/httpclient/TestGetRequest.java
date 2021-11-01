package test.java.tests.restapi.httpclient;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestGetRequest {
    public static void main(String[] args) {
        try {
            // 1. Create GET method.
            HttpGet get = new HttpGet("/all");

            // 2. Create the HTTP Client.
            CloseableHttpClient client = HttpClientBuilder.create().build();

            // 3. Execute the HTTP method using the Client and catch the response.
            CloseableHttpResponse res = client.execute(get);
            StatusLine status = res.getStatusLine();

            // 4. Display the response.
            System.out.println("Status Code: " + status.getStatusCode());
            System.out.println("Protocol Version: " + status.getProtocolVersion());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
