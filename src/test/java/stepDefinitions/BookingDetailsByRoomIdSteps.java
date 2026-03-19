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

    @When("the customer selects a room with no bookings to check booking details")
    public void theCustomerSelectsARoomWithNoBookingsToCheckBookingDetails() {
        
    }

    @Then("the system should show {string}")
    public void theSystemShouldShow(String arg0) {
        
    }

    @When("the customer selects an invalid room to check booking details")
    public void theCustomerSelectsAnInvalidRoomToCheckBookingDetails() {
    }
}
