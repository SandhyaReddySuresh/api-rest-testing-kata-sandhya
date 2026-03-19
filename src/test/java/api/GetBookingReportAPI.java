package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.*;
import utils.Utils;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class GetBookingReportAPI extends Utils {
    public static RequestSpecification requestSpec;
    public static Response response;

    public static Response getBookingReportAPI_Call(String resourceDetails) throws IOException {

        String tokenValue = AdminAuthAPI.checkTokenDetails();

        requestSpec = given()
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

    public static void checkStatusCode(String statusCode) throws IOException {
        checkStatusCode(response, statusCode);
    }

    public static void getBookingReportSummaryAPI_Response() {
        ReportSummary reportSummary = response.as(ReportSummary.class);
        for (Report report : reportSummary.getReport()) {
            Assert.assertNotNull("Start should not be null", report.getStart());
            Assert.assertNotNull("End should not be null", report.getEnd());
            Assert.assertNotNull("Title should not be null", report.getTitle());
        }
    }
}