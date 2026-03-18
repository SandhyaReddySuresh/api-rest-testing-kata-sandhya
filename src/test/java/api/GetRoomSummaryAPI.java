package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.BookingDetails;
import pojo.BookingResponse;
import pojo.ListRoomDetails;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetRoomSummaryAPI extends Utils {
    public static RequestSpecification requestSpec;

    public static Response response;

    public static Response getRoomSummary(String resourceDetails, String roomID) throws IOException {
        String tokenValue = AdminAuthAPI.checkTokenDetails();

        requestSpec = given()
                .header("Cookie", "token=" + tokenValue)
                .queryParam("roomid", roomID)
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

    public static void checkStatusCode(String statusCode) throws IOException {
        System.out.println(response.toString());
        String statusCodeResponse = String.valueOf(response.statusCode());
        Assert.assertEquals("Success status response", statusCodeResponse, statusCode);

    }

    public static void getBookingDetailsByRoomIDAPI_Response() {
        BookingDetails bookingDetails = response.as(BookingDetails.class);
        for (BookingResponse booking : bookingDetails.getBookings()) {
            Assert.assertNotNull("Booking Id should not be null", booking.getBookingid());
            Assert.assertNotNull("Room Id should not be null", booking.getRoomid());
            Assert.assertNotNull("FirstName should not be null", booking.getFirstname());
            Assert.assertNotNull("LastName should not be null", booking.getLastname());
            Assert.assertNotNull("Depositpaid should not be null", booking.getDepositpaid());
            Assert.assertNotNull("Check in from Bookingdates and its should not null", booking.getBookingdates().getCheckin());
            Assert.assertNotNull("Check out from Bookingdates and its should not null", booking.getBookingdates().getCheckout());


        }
    }
}
