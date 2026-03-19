package stepDefinitions;

import api.GetByRoomIdAPI;
import api.ListOfRoomsAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetByRoomIdSteps {

    public static int roomIdFromListOfRoom;

    @Given("I want to check a room")
    public void iWantToCheckARoom() {
        roomIdFromListOfRoom = ListOfRoomsAPI.roomId;
    }

    @When("I ask the system for the details of the room")
    public void iAskTheSystemForTheDetailsOfTheRoom() throws IOException {
        roomIdFromListOfRoom = ListOfRoomsAPI.roomId;
        String resourceDetails = "GetByRoomId";
        APIResources resourcesAPI = APIResources.valueOf(resourceDetails);
        GetByRoomIdAPI.getByRoomIdAPI_Call(resourcesAPI.getResources(), roomIdFromListOfRoom);
    }

    @Then("I should get the correct information about the room:")
    public void iShouldGetTheCorrectInformationAboutTheRoom(DataTable dataTable) throws IOException {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        int statusCode = ConfigReader.getIntProperty("SuccessStatusCode");
        GetByRoomIdAPI.checkStatusCode(String.valueOf(statusCode));
        GetByRoomIdAPI.verifyRoomsDetailsPresentInResponse();
        GetByRoomIdAPI.validateRoomsDetailsFromResponse(rows, roomIdFromListOfRoom);

    }

    @And("the room should include features such as:")
    public void theRoomShouldIncludeFeaturesSuchAs(DataTable dataTable) {
        List<String> expectedFields = dataTable.asList();
        GetByRoomIdAPI.verifyRoomFeaturesDetails_FromResponse(expectedFields);
    }

    @And("the room should have a description explaining it")
    public void theRoomShouldHaveADescriptionExplainingIt() {
        GetByRoomIdAPI.verifyRoomDescriptionDetails_FromResponse();
    }

    @And("the room should have an image to view")
    public void theRoomShouldHaveAnImageToView() {
        GetByRoomIdAPI.verifyRoomImageDetails_FromResponse();
    }

    @When("the user requests room details with room ID {string}")
    public void the_user_requests_room_details_with_room_id(String roomId) throws IOException {
        String resourceDetails = "GetByRoomId";
        APIResources resourcesAPI = APIResources.valueOf(resourceDetails);
        GetByRoomIdAPI.getByRoomIdAPIInvalidAPI_Call(resourcesAPI.getResources(), roomId);
    }

    @Then("the API should return an error")
    public void theAPIShouldReturnAnError() {
        GetByRoomIdAPI.checkInvalidStatusCode();
    }

    @And("the error message should be {string}")
    public void the_error_message_should_be(String expectedMessage) {
        GetByRoomIdAPI.checkErrorMessage(expectedMessage);
    }

    @And("the response body should match the expected JSON schema")
    public void theResponseBodyShouldMatchTheExpectedJSONSchema() {
        GetByRoomIdAPI.checkSchemaValidation();
    }
}
