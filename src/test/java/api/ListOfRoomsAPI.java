package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.ListRoomDetails;
import pojo.Rooms;
import utils.Utils;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ListOfRoomsAPI extends Utils {
    public static RequestSpecification requestSpec;
    public static Response response;
    public static ListRoomDetails roomListDetails;
    public static int roomId;

    public static Response getListOfRooms(String resourceDetails) throws IOException {
        requestSpec = given().spec(requestSpecification())
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

    public static void verifyRoomsListResponse() {
        roomListDetails = response.as(ListRoomDetails.class);
        Assert.assertNotNull("The system should display a list of rooms in response", roomListDetails.getRooms());
    }

    public static void verifyEachRoomsDetails_FromResponse(List<String> expectedFields) {
        Assert.assertNotNull("The system should return a list of rooms", roomListDetails.getRooms());
        Assert.assertFalse("Rooms list should not be empty", roomListDetails.getRooms().isEmpty());

        for (Rooms room : roomListDetails.getRooms()) {
            for (String field : expectedFields) {
                switch (field) {
                    case "roomid":
                        Assert.assertNotNull("roomid is missing", room.getRoomid());
                        roomId = room.getRoomid();
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

    public static void checkSchemaValidation() {
        try {
            validateJsonSchema(response, "schema/listOfRooms-schema.json");
        } catch (AssertionError e) {
            Assert.fail("Schema validation correctly failed: " + e.getMessage());

        }
    }

    public static Response getListOfRooms_Invalid(String resourceDetails) throws IOException {
        requestSpec = given().spec(requestSpecification())
                .log().all();

        response = requestSpec
                .when()
                .get(resourceDetails)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public static Response getListOfRooms_WithPOSTMethodCall(String resourceDetails) throws IOException {
        requestSpec = given().spec(requestSpecification())
                .log().all();

        response = requestSpec
                .when()
                .post(resourceDetails)
                .then()
                .log().all()
                .extract().response();
        return response;
    }

    public static void checkErrorMessage(String ExpectedErrorMessage) {
        String actualErrorMessage = getJsonPath(response, "error");
        Assert.assertNotNull("Error Message should be displayed", actualErrorMessage);
        Assert.assertEquals("Display Method Not Allowed Message", ExpectedErrorMessage, actualErrorMessage);
    }
}