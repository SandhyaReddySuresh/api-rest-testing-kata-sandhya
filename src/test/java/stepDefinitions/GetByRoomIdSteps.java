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
        roomIdFromListOfRoom=ListOfRoomsAPI.roomId;
    }

    @When("I ask the system for the details of the room")
    public void iAskTheSystemForTheDetailsOfTheRoom() throws IOException {
        String resourceDetails="GetByRoomId";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetByRoomIdAPI.getByRoomIdAPI_Call(resourcesAPI.getResources(),roomIdFromListOfRoom);
    }

    @Then("I should get the correct information about the room:")
    public void iShouldGetTheCorrectInformationAboutTheRoom(DataTable dataTable) throws IOException {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        int statusCode= ConfigReader.getIntProperty("SuccessStatusCode");
        GetByRoomIdAPI.checkStatusCode(String.valueOf(statusCode));
        GetByRoomIdAPI.verifyRoomsDetailsPresentInResponse();
        GetByRoomIdAPI.validateRoomsDetailsFromResponse(rows,roomIdFromListOfRoom);

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
}
