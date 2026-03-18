package check;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class HealthCheck {
    public static void checkBookingSystemOperational() {
        String testEndpoint = "https://automationintesting.online";

        Response response = RestAssured.get(testEndpoint);
        if (response == null) {
            Assert.fail("API did not respond (response is null)");
        }

        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            Assert.fail("API is not operational. Status code: " + statusCode);
        }

        System.out.println("API is operational. Status code: " + statusCode);
    }
}
