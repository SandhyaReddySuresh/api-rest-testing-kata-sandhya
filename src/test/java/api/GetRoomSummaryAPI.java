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
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

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

    public static Response getRoomSummary_UnauthorizedUsers(String resourceDetails, int roomID,String tokenValue) throws IOException {
        requestSpec = given()
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
    public static Response getRoomSummary_EmptyRoomId(String resourceDetails) throws IOException {
        String tokenValue = AdminAuthAPI.checkTokenDetails();

        requestSpec = given()
                .header("Cookie", "token=" + tokenValue)
                .queryParam("roomid","")
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
    public static void checkErrorMessage(String expectedMessage)
    {
        Utils.checkErrorMessage(response,expectedMessage);
    }
    public static void checkResponse_For_InvalidRoomId()
    {
        response.then()
                .assertThat()
                .body("bookings", notNullValue())
                .body("bookings.size()", equalTo(0));

    }
    public static void checkInvalidStatusCode()
    {
        Utils.checkInvalidStatusCode(response);
    }

    public static void checkSchemaValidation(String schemaJson)
    {
        Utils.checkSchemaValidation(response,schemaJson);
    }


}
