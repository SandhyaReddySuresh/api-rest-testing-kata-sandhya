package stepDefinitions;

import api.CreateBookingAPI;
import api.GetBookingByIDAPI;
import api.GetByRoomIdAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;

import java.io.IOException;

public class EndToEndSteps {
    @When("I get booking details by booking ID")
    public void iGetBookingDetailsByBookingID() throws IOException {
        int bookingId=CreateBookingAPI.returnBookingId();
        String resourceDetails="UpdateBookingDetails";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetBookingByIDAPI.getBookingByID(resourcesAPI.getResources(), String.valueOf(bookingId));

    }
    @Then("I should see booking information")
    public void iShouldSeeBookingInformation() {
        GetBookingByIDAPI.verifyRoomsDetailsPresentInResponse();
    }
    @When("I get booking details for room ID")
    public void iGetBookingDetailsForRoomID() throws IOException {
       int roomId= CreateBookingAPI.returnRoomId();
        String resourceDetails="GetByRoomId";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetByRoomIdAPI.getByRoomIdAPI_Call(resourcesAPI.getResources(),roomId);
    }


    @Then("the booking information should display")
    public void theBookingInformationShouldDisplay() {
        GetByRoomIdAPI.verifyRoomsDetailsPresentInResponse();

    }
}
