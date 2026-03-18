package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.GetRoomIdDetails;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetBookingReportAPI extends Utils {
    public static RequestSpecification requestSpec;
    public static Response response;

    public static Response getBookingReportAPI_Call(String resourceDetails) throws IOException {


        requestSpec=given()
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

    public static void checkStatusCode(String statusCode)throws IOException

    {
        System.out.println(response.toString());
        String statusCodeResponse= String.valueOf(response.statusCode());
        Assert.assertEquals("Success status response",statusCodeResponse,statusCode);

    }
}
