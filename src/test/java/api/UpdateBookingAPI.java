package api;

import TestData.TestDataBuild;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.BookingDetails;
import pojo.BookingResponse;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UpdateBookingAPI extends Utils {

    public static RequestSpecification requestSpec;

    public static Response response;
   public  static BookingResponse bookingPayload;
    static TestDataBuild testData=new TestDataBuild();

    public static Response putBookingAPI(String resourceDetails, String firstname,String lastname,boolean depositPaid,String checkIn,String checkOut) throws IOException {
        String tokenValue= AdminAuthAPI.checkTokenDetails();
        int updateBookingId= CreateBookingAPI.bookingID;
        int updateRoomId= CreateBookingAPI.roomId;
        bookingPayload = testData.updateBookingDetailsPayload(updateBookingId,updateRoomId,firstname,lastname,depositPaid,checkIn,checkOut);

        requestSpec = given()
                .pathParam("BookingID",updateBookingId)
                .header("Cookie", "token=" + tokenValue)
                .spec(requestSpecification())
                .body(bookingPayload).log().all();

        response = requestSpec
                .when()
                .put(resourceDetails)
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
}
