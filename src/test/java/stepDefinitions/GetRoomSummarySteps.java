package stepDefinitions;

import api.AdminAuthAPI;
import api.GetRoomSummaryAPI;
import api.ListOfRoomsAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;

public class GetRoomSummarySteps {
    public static String token;

    @When("the user asks the room booking summary for roomId {}")
    public void theUserAsksTheRoomBookingSummaryForRoomId(String roomId) throws IOException {
        String resourceDetails="CreateBookingAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetRoomSummaryAPI.getRoomSummary(resourcesAPI.getResources(),roomId);
    }

    @Then("the room booking summary response should be successful")
    public void theRoomBookingSummaryResponseShouldBeSuccessful() throws IOException {
        int statusCode= ConfigReader.getIntProperty("SuccessStatusCode");
        GetRoomSummaryAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @And("the response should contain a list of bookings")
    public void theResponseShouldContainAListOfBookings() {
        GetRoomSummaryAPI.getBookingDetailsByRoomIDAPI_Response();
    }

    @Given("the user is not authenticated")
    public void theUserIsNotAuthenticated() {
        token = null;

    }

    @When("the user requests the booking summary for room ID {int}")
    public void theUserRequestsTheBookingSummaryForRoomID(int roomId) throws IOException {
        String resourceDetails="CreateBookingAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetRoomSummaryAPI.getRoomSummary_UnauthorizedUsers(resourcesAPI.getResources(),roomId,token);
    }

    @Then("the system denies access")
    public void theSystemDeniesAccess() throws IOException {
        int statusCode= ConfigReader.getIntProperty("UnauthorizedStatusCode");
        GetRoomSummaryAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @And("an authentication error message {string} is displayed")
    public void anAuthenticationErrorMessageIsDisplayed(String errorMessage) {
        GetRoomSummaryAPI.checkErrorMessage(errorMessage);
    }

    @Then("the system should not show any valid room")
    public void theSystemShouldNotShowAnyValidRoom() {
        GetRoomSummaryAPI.checkResponse_For_InvalidRoomId();
    }

    @When("the user requests the booking summary without providing a room ID")
    public void theUserRequestsTheBookingSummaryWithoutProvidingARoomID() throws IOException {
        String resourceDetails="CreateBookingAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetRoomSummaryAPI.getRoomSummary_EmptyRoomId(resourcesAPI.getResources());
    }

    @Then("the system should respond with an error")
    public void theSystemShouldRespondWithAnError() {
       GetRoomSummaryAPI.checkInvalidStatusCode();
    }

    @And("the error message should be {string}  is dispalyed")
    public void theErrorMessageShouldBeIsDispalyed(String errorMessage) {
        GetRoomSummaryAPI.checkErrorMessage(errorMessage);

    }

    @And("the response body should match the expected Room summary JSON schema {string}")
    public void theResponseBodyShouldMatchTheExpectedRoomSummaryJSONSchema(String schemaJson) {
        GetRoomSummaryAPI.checkSchemaValidation(schemaJson);
    }


}
