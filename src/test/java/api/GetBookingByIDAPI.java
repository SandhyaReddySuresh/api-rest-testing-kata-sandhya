package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.BookingResponse;
import utils.Utils;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class GetBookingByIDAPI extends Utils {

    public static RequestSpecification requestSpec;
    public static Response response;
    public static Response getBookingByID(String resourceDetails,String bookingId) throws IOException {
        String tokenValue= AdminAuthAPI.checkTokenDetails();

        requestSpec=given()
                .pathParam("BookingID",bookingId)
                .header("Cookie", "token=" + tokenValue)
                .spec(requestSpecification())
                .log().all();

        response = requestSpec
                .when()
                .get(resourceDetails)
                .then()
                .spec(responseSpecification())
                .log().all()
                .extract().response();
        return response;
    }

    public static void checkStatusCode(String statusCode)throws IOException {
        System.out.println(response.toString());
        String statusCodeResponse= String.valueOf(response.statusCode());
        Assert.assertEquals(statusCodeResponse,statusCode);
    }
    public static void verifyRoomsDetailsPresentInResponse() {
        BookingResponse booking =response.as(BookingResponse.class);
        Assert.assertNotNull("the system should present the booking’s full details",booking);
    }
    public static void checkErrorMessage(String ExpectedErrorMessage) {
        String actualErrorMessage = getJsonPath(response, "error");

        if (actualErrorMessage == null || actualErrorMessage.equals("[]") || actualErrorMessage.trim().isEmpty()) {
            actualErrorMessage = null;
        }
        Assert.assertNotNull(
                "Your request could not be processed. Please review the information you provided.",
                actualErrorMessage
        );

        Assert.assertEquals(
                "Expected error message did not match. Customer should see: '" + ExpectedErrorMessage + "'",
                ExpectedErrorMessage,
                actualErrorMessage
        );
    }
}