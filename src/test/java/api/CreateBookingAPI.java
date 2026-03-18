package api;

import TestData.TestDataBuild;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.BookingResponse;
import pojo.CreateBooking;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CreateBookingAPI extends Utils {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public static Response response;
    static CreateBooking bookingPayload;

    public static int bookingID;
    public static int roomId;
    static TestDataBuild testData=new TestDataBuild();
    public static Response postCreateBooking(String resourceDetails, String firstname, String lastname, boolean depositpaid, String checkin, String checkout, String phone) throws IOException {
        String tokenValue= AdminAuthAPI.checkTokenDetails();

        bookingPayload = testData.createBookingPayLoad(firstname,lastname,depositpaid,checkin,checkout,phone);

        requestSpec=given()
               .header("Cookie", "token=" + tokenValue)
                .spec(requestSpecification())
                .body(bookingPayload).log().all();

        response = requestSpec
                .when()
                .post(resourceDetails)
                .then()
                .spec(responseSpecification())
                .log().all()
                .extract().response();
        return response;

    }
    public static void checkStatusCode(String statusCode)throws IOException

    {
        System.out.println(response.toString());
        String statusCodeResponse= String.valueOf(response.statusCode());
        Assert.assertEquals(statusCodeResponse,statusCode);

    }
    public static void checkBookingDetails()
    {
        BookingResponse bookingResponse=response.as(BookingResponse.class);
        System.out.println(bookingResponse.getBookingid());

        bookingID=bookingResponse.getBookingid();
        roomId=bookingResponse.getRoomid();
        Assert.assertNotNull("Validate RoomID", bookingResponse.getRoomid());
        Assert.assertEquals("System should display FirstName",bookingPayload.getFirstname(), bookingResponse.getFirstname());
        Assert.assertEquals("System should display LastName",bookingPayload.getLastname(), bookingResponse.getLastname());
        Assert.assertEquals("System should display DepositPaid",bookingPayload.getDepositpaid(), bookingResponse.getDepositpaid());
        Assert.assertEquals("System should display Check in from BookingDates",bookingPayload.getBookingdates().getCheckin(), bookingResponse.getBookingdates().getCheckin());
        Assert.assertEquals("System should display Check out from BookingDates",bookingPayload.getBookingdates().getCheckout(), bookingResponse.getBookingdates().getCheckout());


    }
    public static int returnBookingId()
    {
        Assert.assertNotNull("Validate BookingID is returned successfully", bookingID);

        return bookingID;
    }
}
