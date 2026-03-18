package stepDefinitions;

import api.AdminAuthAPI;
import api.ListOfRoomsAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;

public class ListOfRoomsSteps {
    @When("the user sends a GET request to fetch rooms from the system")
    public void theUserSendsAGETRequestToFetchRoomsFromTheSystem() throws IOException {
        String resourceDetails="AuthAdmin";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        ListOfRoomsAPI.getListOfRooms(resourcesAPI.getResources());
    }

    @Then("the system should return a successful response")
    public void theSystemShouldReturnASuccessfulResponse() throws IOException {
        int statusCode= ConfigReader.getIntProperty("SuccessStatusCode");
        ListOfRoomsAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @And("the response should contain a list of rooms")
    public void theResponseShouldContainAListOfRooms() {

    }

    @And("each room should have the following fields:")
    public void eachRoomShouldHaveTheFollowingFields() {
    }
}
