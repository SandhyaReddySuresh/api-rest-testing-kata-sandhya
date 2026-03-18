package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import payloadDetails.AdminAuthPayload;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class AdminAuthAPI extends Utils {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public static  Response response;
    public static String tokenValueFromResponse;

    public static Response postAuthAdmin(String resourceDetails, String userName, String Password) throws IOException {


        requestSpec=given().spec(requestSpecification())
                .body(AdminAuthPayload.adminAuthPayload(userName,Password)).log().all();

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
    public static String checkTokenDetails()
    {
        tokenValueFromResponse=getJsonPath(response,"token");
        return tokenValueFromResponse;
    }
}
