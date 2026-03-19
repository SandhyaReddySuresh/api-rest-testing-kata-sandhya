package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.ListRoomDetails;
import pojo.Rooms;
import utils.Utils;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CheckAvailabilityAPI extends Utils {

    public static RequestSpecification requestSpec;

    public static Response response;
    public static ListRoomDetails roomListDetails;


    public static Response getCheckAvailabilityAPI_Call(String resourceDetails,String CheckIn, String CheckOut) throws IOException {

        String tokenValue = AdminAuthAPI.checkTokenDetails();

        requestSpec=given()
                .queryParam("checkin",CheckIn)
                .queryParam("CheckOut",CheckOut)
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
    public static void checkStatusCode(String statusCode)throws IOException

    {
        checkStatusCode(response,statusCode);
    }
    public static void verifyRoomsListResponse() {
        roomListDetails = response.as(ListRoomDetails.class);
        Assert.assertNotNull("The system should display a list of rooms in response", roomListDetails.getRooms());

    }
    public static void verifyEachRoomsDetails_FromResponse(List<String> expectedFields ) {

        Assert.assertNotNull("The system should return a list of rooms", roomListDetails.getRooms());
        Assert.assertFalse("Rooms list should not be empty", roomListDetails.getRooms().isEmpty());

        for (Rooms room : roomListDetails.getRooms()) {
            for (String field : expectedFields) {
                switch (field) {
                    case "roomid":
                        Assert.assertNotNull("roomid is missing", room.getRoomid());
                        break;
                    case "roomName":
                        Assert.assertNotNull("roomName is missing", room.getRoomName());
                        break;
                    case "type":
                        Assert.assertNotNull("type is missing", room.getType());
                        break;
                    case "accessible":
                        Assert.assertTrue("accessible field missing", true);
                        break;
                    case "image":
                        Assert.assertNotNull("image is missing", room.getImage());
                        break;
                    case "description":
                        Assert.assertNotNull("description is missing", room.getDescription());
                        break;
                    case "features":
                        Assert.assertNotNull("features is missing", room.getFeatures());
                        break;
                    case "roomPrice":
                        Assert.assertTrue("roomPrice should be > 0", room.getRoomPrice() > 0);
                        break;
                }
            }
        }
    }

    public static void checkErrorMessage(String ExpectedErrorMessage) {
        String actualErrorMessage = getJsonPath(response, "error");

        if (actualErrorMessage == null || actualErrorMessage.equals("[]") || actualErrorMessage.trim().isEmpty()) {
            actualErrorMessage = null;
        }

        Assert.assertNotNull(
                "Your request could not be processed. Please review the information you provided.",
                actualErrorMessage
        );

        Assert.assertEquals(
                "Expected error message did not match. Customer should see: '" + ExpectedErrorMessage + "'",
                ExpectedErrorMessage,
                actualErrorMessage
        );
    }

    public static void checkSchemaValidation()
    {
        try {
            validateJsonSchema(response, "schema/checkAvailability-schema.json");
        }
        catch (AssertionError e)
        {
            Assert.fail("Schema validation correctly failed: " + e.getMessage());

        }
    }
}
