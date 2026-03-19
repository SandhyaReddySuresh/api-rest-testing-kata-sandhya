package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import payloadDetails.AdminAuthPayload;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class AdminAuthAPI extends Utils {
    public static RequestSpecification requestSpec;
    public static Response response;
    public static String tokenValueFromResponse;

    public static Response postAuthAdmin(String resourceDetails, String userName, String Password) throws IOException {
        requestSpec = given().spec(requestSpecification())
                .body(AdminAuthPayload.adminAuthPayload(userName, Password)).log().all();

        response = requestSpec
                .when()
                .post(resourceDetails)
                .then()
                .spec(responseSpecification())
                .log().all()
                .extract().response();
        return response;

    }

    public static void checkStatusCode(String statusCode) throws IOException {
        checkStatusCode(response, statusCode);

    }

    public static String checkTokenDetails() {
        tokenValueFromResponse = getJsonPath(response, "token");
        return tokenValueFromResponse;
    }

    public static void checkErrorMessage_ForUnUnauthorizedUser() {
        String errorMessage = getJsonPath(response, "error");
        Assert.assertNotNull("Failed to authenticate", errorMessage);
    }

    public static void checkSchemaValidation() {
        try {
            validateJsonSchema(response, "schema/login-schema.json");
        } catch (AssertionError e) {
            Assert.fail("Schema validation correctly failed: " + e.getMessage());
        }
    }
}