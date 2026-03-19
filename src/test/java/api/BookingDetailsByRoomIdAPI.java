package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.BookingDetails;
import pojo.BookingResponse;
import pojo.ListRoomDetails;
import pojo.Rooms;
import utils.Utils;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookingDetailsByRoomIdAPI extends Utils {

    public static RequestSpecification requestSpec;

    public static Response response;
    public static BookingDetails bookingListDetails;

    public static int roomId;
    public static int bookingId;

    public static Response getBookingDetailsById(String resourceDetails, String roomID) throws IOException {
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
        checkStatusCode(response,statusCode);

    }

    public static void getBookingAndRoomId_FromResponse() {
        bookingListDetails = response.as(BookingDetails.class);
        for (BookingResponse booking : bookingListDetails.getBookings()) {
            bookingId=booking.getBookingid();
            roomId=booking.getRoomid();

        }
        }
    public static void verifyEachBookingDetails_FromResponse(List<String> expectedFields ) {
        bookingListDetails = response.as(BookingDetails.class);

        Assert.assertNotNull("The system should return a list of booking details", bookingListDetails.getBookings());
        Assert.assertFalse("Booking list should not be empty", bookingListDetails.getBookings().isEmpty());

        for (BookingResponse booking : bookingListDetails.getBookings()) {
            for (String field : expectedFields) {
                switch (field) {
                    case "bookingid":
                        Assert.assertNotNull("bookingid is missing", booking.getBookingid());
                        bookingId=booking.getBookingid();
                        break;
                    case "roomid":
                        Assert.assertNotNull("roomid is missing", booking.getRoomid());
                        roomId=booking.getRoomid();
                        break;
                    case "firstname":
                        Assert.assertNotNull("firstname is missing", booking.getFirstname());
                        break;
                    case "lastname":
                        Assert.assertNotNull("lastname is missing", booking.getLastname());
                        break;
                    case "depositpaid":
                        Assert.assertTrue("depositpaid field missing", true);
                        break;
                    case "bookingdates":
                        Assert.assertNotNull("bookingdates is missing",booking.getBookingdates());
                        break;
                }
            }
        }
    }

    public static void checkErrorMessage(String expectedMessage)
    {
        String actualMessage = response.jsonPath().getString("error");
        assertThat("Error message mismatch", actualMessage, equalTo(expectedMessage));
    }

    public static void validateInvalidResponse() {
        response.then()
                .assertThat()
                .body("bookings.size()", equalTo(0));
        Assert.fail("the system should inform the customer that the selected room is invalid");
    }
    public static void checkSchemaValidation()
    {
        try {
            validateJsonSchema(response, "schema/bookingByRoomId-schema.json");
        }
        catch (AssertionError e)
        {
            Assert.fail("Schema validation correctly failed: " + e.getMessage());

        }
    }

}
