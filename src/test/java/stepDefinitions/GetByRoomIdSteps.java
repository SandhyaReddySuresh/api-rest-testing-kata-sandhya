package stepDefinitions;

import api.GetByRoomIdAPI;
import api.ListOfRoomsAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;

public class GetByRoomIdSteps {

    public static int roomIdFromListOfRoom;

    @Given("I want to check a room")
    public void iWantToCheckARoom() {
        roomIdFromListOfRoom=ListOfRoomsAPI.roomId;
    }

    @When("I ask the system for the details of the room")
    public void iAskTheSystemForTheDetailsOfTheRoom() throws IOException {
        String resourceDetails="ListOfRoomsAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetByRoomIdAPI.getByRoomIdAPI_Call(resourcesAPI.getResources(),roomIdFromListOfRoom);
    }

    @Then("I should get the correct information about the room:")
    public void iShouldGetTheCorrectInformationAboutTheRoom() throws IOException {
        int statusCode= ConfigReader.getIntProperty("SuccessStatusCode");
        GetByRoomIdAPI.checkStatusCode(String.valueOf(statusCode));
    }
}
