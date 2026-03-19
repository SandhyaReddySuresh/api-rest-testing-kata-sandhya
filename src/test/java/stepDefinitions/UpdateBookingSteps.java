package stepDefinitions;

import api.BookingDetailsByRoomIdAPI;
import api.CreateBookingAPI;
import api.UpdateBookingAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;
import java.util.Map;

public class UpdateBookingSteps {
    @When("the user updates the existing booking with the following details:")
    public void theUserUpdatesTheExistingBookingWithTheFollowingDetails(DataTable dataTable) throws IOException {
        Map<String, String> bookingData = dataTable.asMaps(String.class, String.class).get(0);
        String firstname = bookingData.get("firstname");
        String lastname = bookingData.get("lastname");
        boolean depositPaid = Boolean.parseBoolean(bookingData.get("depositpaid"));
        String checkIn = bookingData.get("checkin");
        String checkOut = bookingData.get("checkout");
        String resourceDetails = "UpdateBookingDetails";
        APIResources resourcesAPI = APIResources.valueOf(resourceDetails);
        UpdateBookingAPI.putBookingAPI(resourcesAPI.getResources(), firstname, lastname, depositPaid, checkIn, checkOut);
    }

    @Then("the booking should be successfully updated")
    public void theBookingShouldBeSuccessfullyUpdated() throws IOException {
        int statusCode = ConfigReader.getIntProperty("SuccessStatusCode");
        UpdateBookingAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @When("the customer views the booking details for that updated room")
    public void theCustomerViewsTheBookingDetailsForThatUpdatedRoom() throws IOException {
        String resourceDetails = "CreateBookingAPI";
        APIResources resourcesAPI = APIResources.valueOf(resourceDetails);
        BookingDetailsByRoomIdAPI.getBookingDetailsById(resourcesAPI.getResources(), String.valueOf(CreateBookingAPI.roomId));
        int statusCode = ConfigReader.getIntProperty("SuccessStatusCode");
        BookingDetailsByRoomIdAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @Then("the booking details should reflect the latest updates")
    public void theBookingDetailsShouldReflectTheLatestUpdates() throws IOException {
        BookingDetailsByRoomIdAPI.getBookingAndRoomId_FromResponse();
        UpdateBookingAPI.checkUpdateDetails_InResponse();
    }
}