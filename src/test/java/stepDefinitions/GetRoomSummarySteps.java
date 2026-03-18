package stepDefinitions;

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
}
