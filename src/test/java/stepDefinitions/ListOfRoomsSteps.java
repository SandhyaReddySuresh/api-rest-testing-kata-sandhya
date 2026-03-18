package stepDefinitions;

import api.AdminAuthAPI;
import api.ListOfRoomsAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ListOfRoomsSteps {
    @When("the user sends a GET request to fetch rooms from the system")
    public void theUserSendsAGETRequestToFetchRoomsFromTheSystem() throws IOException {
        String resourceDetails="ListOfRoomsAPI";
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
      ListOfRoomsAPI.verifyRoomsListResponse();
    }

    @And("each room should have the following fields:")
    public void eachRoomShouldHaveTheFollowingFields(DataTable dataTable) {
        List<String> expectedFields = dataTable.asList();
       ListOfRoomsAPI.verifyEachRoomsDetails_FromResponse(expectedFields);
    }

    @And("the response body should conform to the expected JSON schema")
    public void theResponseBodyShouldConformToTheExpectedJSONSchema() {
      ListOfRoomsAPI.checkSchemaValidation();
    }

    @When("the user sends a request to an incorrect API endpoint")
    public void theUserSendsARequestToAnIncorrectAPIEndpoint() throws IOException {
        String resourceDetails="InvalidResourceListOfRoomsAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        ListOfRoomsAPI.getListOfRooms_Invalid(resourcesAPI.getResources());
    }

    @Then("the system should return resource not found")
    public void theSystemShouldReturnResourceNotFound() throws IOException {
        int statusCode= ConfigReader.getIntProperty("NotFound");
        ListOfRoomsAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @When("the user requests data using an invalid identifier")
    public void theUserRequestsDataUsingAnInvalidIdentifier() throws IOException {
        String resourceDetails="InvalidIdentifierListOfRoomsAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        ListOfRoomsAPI.getListOfRooms_Invalid(resourcesAPI.getResources());
    }

    @When("the user sends a request using an unsupported HTTP method")
    public void theUserSendsARequestUsingAnUnsupportedHTTPMethod() throws IOException {
        String resourceDetails="ListOfRoomsAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        ListOfRoomsAPI.getListOfRooms_WithPOSTMethodCall(resourcesAPI.getResources());

    }

    @Then("the system should return status code {int}")
    public void theSystemShouldReturnStatusCode(int statusCode) throws IOException {
        ListOfRoomsAPI.checkStatusCode(String.valueOf(statusCode));

    }

    @And("the user should see a {string} message")
    public void theUserShouldSeeAMessage(String errorMessage) {
        ListOfRoomsAPI.checkErrorMessage(errorMessage);
    }
}
