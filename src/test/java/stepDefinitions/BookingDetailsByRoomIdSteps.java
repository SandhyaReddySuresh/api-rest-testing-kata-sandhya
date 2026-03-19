package stepDefinitions;

import api.BookingDetailsByRoomIdAPI;
import api.GetRoomSummaryAPI;
import api.ListOfRoomsAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;
import java.util.List;

public class BookingDetailsByRoomIdSteps {

    @When("the customer selects room {string} to view booking details")
    public void theCustomerSelectsRoomToViewBookingDetails(String roomId) throws IOException {
        String resourceDetails="CreateBookingAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        BookingDetailsByRoomIdAPI.getBookingDetailsById(resourcesAPI.getResources(),roomId);
        
    }

    @Then("the booking details should be displayed successfully")
    public void theBookingDetailsShouldBeDisplayedSuccessfully() throws IOException {
        int statusCode= ConfigReader.getIntProperty("SuccessStatusCode");
        BookingDetailsByRoomIdAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @And("the booking should include:")
    public void theBookingShouldInclude(DataTable dataTable) {
        List<String> expectedFields = dataTable.asList();
        BookingDetailsByRoomIdAPI.verifyEachBookingDetails_FromResponse(expectedFields);
    }
    @When("the customer selects an invalid room {string} to check booking details")
    public void theCustomerSelectsAnInvalidRoomToCheckBookingDetails(String roomId) throws IOException {
        String resourceDetails="CreateBookingAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        BookingDetailsByRoomIdAPI.getBookingDetailsById(resourcesAPI.getResources(),roomId);
    }

    @Then("the customer should see a message indicating the room selection is invalid")
    public void theCustomerShouldSeeAMessageIndicatingTheRoomSelectionIsInvalid() {
        BookingDetailsByRoomIdAPI.validateInvalidResponse();

    }

    @And("the response should match the booking JSON schema")
    public void theResponseShouldMatchTheBookingJSONSchema() {
        BookingDetailsByRoomIdAPI.checkSchemaValidation();
    }


}
