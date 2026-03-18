package stepDefinitions;

import api.CheckAvailabilityAPI;
import api.ListOfRoomsAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;

import java.io.IOException;
import java.util.List;

public class CheckAvailabilitySteps {
    @When("I request rooms with check-in {string} and check-out {string}")
    public void iRequestRoomsWithCheckInAndCheckOut(String CheckIn, String CheckOut) throws IOException {
        String resourceDetails="CheckAvailability";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        CheckAvailabilityAPI.getCheckAvailabilityAPI_Call(resourcesAPI.getResources(),CheckIn,CheckOut);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) throws IOException {
        CheckAvailabilityAPI.checkStatusCode(String.valueOf(statusCode));

    }

    @And("the response should contain a list of available rooms")
    public void theResponseShouldContainAListOfAvailableRooms() {
        CheckAvailabilityAPI.verifyRoomsListResponse();
    }

    @And("each room in the list should have the following fields:")
    public void eachRoomInTheListShouldHaveTheFollowingFields(DataTable dataTable) {
        List<String> expectedFields = dataTable.asList();
        CheckAvailabilityAPI.verifyEachRoomsDetails_FromResponse(expectedFields);
    }

    @And("the response should contain an error message indicating {string}")
    public void theResponseShouldContainAnErrorMessageIndicating(String errorMessage) {
        CheckAvailabilityAPI.checkErrorMessage(errorMessage);
    }

    @And("“Room availability information is returned in a format compliant with the JSON schema")
    public void roomAvailabilityInformationIsReturnedInAFormatCompliantWithTheJSONSchema() {
        CheckAvailabilityAPI.checkSchemaValidation();
    }


}
