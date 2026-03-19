package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.Utils;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class DeleteBookingAPI extends Utils {
    public static RequestSpecification requestSpec;
    public static Response response;
    public static Response deleteBookingByBookingIDAPI_Call(String resourceDetails,String bookingId) throws IOException {
        String tokenValue= AdminAuthAPI.checkTokenDetails();

        requestSpec=given()
                .pathParam("BookingID",bookingId)
                .header("Cookie", "token=" + tokenValue)
                .spec(requestSpecification())
                .log().all();

        response = requestSpec
                .when()
                .delete(resourceDetails)
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

    public static void checkErrorStatus(String errorMessage) throws IOException {
        checkErrorMessage(response,errorMessage);
    }
}