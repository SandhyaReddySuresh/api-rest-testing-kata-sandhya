package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.GetRoomIdDetails;
import pojo.Rooms;
import utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

public class GetByRoomIdAPI extends Utils {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    public static Response response;
    public static GetRoomIdDetails roomDetails;

    public static Response getByRoomIdAPI_Call(String resourceDetails,int roomId) throws IOException {


        requestSpec=given()
                .pathParam("roomId",roomId)
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
    public static void checkInvalidStatusCode()
    {
        assertThat("Status code should be 4xx",
                response.getStatusCode(), allOf(greaterThanOrEqualTo(400), lessThan(500)));
    }
    public static void checkErrorMessage(String expectedMessage)
    {
        String actualMessage = response.jsonPath().getString("error");
        assertThat("Error message mismatch", actualMessage, equalTo(expectedMessage));
    }
    public static void verifyRoomsDetailsPresentInResponse()
    {
        roomDetails=response.as(GetRoomIdDetails.class);
        Assert.assertNotNull("the system should present the room’s full details",roomDetails);

    }
    public static void validateRoomsDetailsFromResponse(List<Map<String, String>> expectedFields, int expectedRoomID) {
        // Validate room ID first
        Assert.assertEquals("Validate expected room id", expectedRoomID, roomDetails.getRoomid());

        for (Map<String, String> row : expectedFields) {
            String field = row.get("Field");

            // Null-safe retrieval of expected value
            String expectedRaw = row.get("Value");
            if (expectedRaw == null) {
                throw new RuntimeException("Missing Value for field: " + field);
            }
            String expected = expectedRaw.toLowerCase();

            switch (field) {
                case "Room Name":
                    String roomName = roomDetails.getRoomName();
                    if (expected.equals("(any)")) {
                        Assert.assertNotNull("Room Name should exist", roomName);
                        Assert.assertFalse("Room Name should not be empty", roomName.isEmpty());
                    } else {
                        Assert.assertEquals("Room Name should match expected", expected, roomName.toLowerCase());
                    }
                    break;

                case "Room Type":
                    String roomType = roomDetails.getType();
                    if (expected.equals("(any)")) {
                        Assert.assertNotNull("Room Type should exist", roomType);
                        Assert.assertFalse("Room Type should not be empty", roomType.isEmpty());
                    } else {
                        Assert.assertEquals("Room Type should match expected", expected, roomType.toLowerCase());
                    }
                    break;

                case "Accessible":
                    Boolean accessible = roomDetails.getAccessible();
                    Assert.assertNotNull("Accessible should exist", accessible);
                    if (expected.equals("yes/no")) {
                        // Check that value is boolean (true/false)
                        Assert.assertTrue("Accessible should be true or false", accessible == true || accessible == false);
                    } else {
                        // Optional: compare exact value if given as "true"/"false"
                        Assert.assertEquals("Accessible should match expected", Boolean.parseBoolean(expected), accessible);
                    }
                    break;

                case "Price":
                    Integer price = roomDetails.getRoomPrice();
                    Assert.assertNotNull("Price should exist", price);
                    if (!expected.equals("(any)")) {
                        Assert.assertEquals("Price should match expected", Integer.parseInt(expected), (int) price);
                    }
                    break;

                default:
                    Assert.fail("Unknown field: " + field);
            }
        }
    }

    public static void verifyRoomFeaturesDetails_FromResponse(List<String> expectedFields ) {
        List<String> features =roomDetails.getFeatures();
        Assert.assertNotNull("Feature should not be null", roomDetails.getFeatures());

        for (String field : expectedFields) {
                switch (field) {
                    case "Radio":
                        Assert.assertTrue("Feature TV should be present", features.contains("Radio"));
                        break;
                    case "WiFi":
                        Assert.assertTrue("Feature WiFi should be present", features.contains("WiFi"));
                        break;
                    case "Safe":
                        Assert.assertTrue("Feature Safe should be present", features.contains("Safe"));
                        break;
                }
            }
        }

    public static void verifyRoomDescriptionDetails_FromResponse() {
        Assert.assertNotNull("Description should not be null", roomDetails.getDescription());

    }

    public static void verifyRoomImageDetails_FromResponse() {
        Assert.assertNotNull("Image should not be null", roomDetails.getImage());

    }

    public static Response getByRoomIdAPIInvalidAPI_Call(String resourceDetails,String roomId) throws IOException {


        requestSpec=given()
                .pathParam("roomId",roomId)
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
    public static void checkSchemaValidation()
    {
        try {
            validateJsonSchema(response, "schema/getByRoomId-schema.json");
        }
        catch (AssertionError e)
        {
            Assert.fail("Schema validation correctly failed: " + e.getMessage());

        }
    }
    }


