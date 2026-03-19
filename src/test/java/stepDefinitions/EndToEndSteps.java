package stepDefinitions;

import api.CreateBookingAPI;
import api.GetBookingByIDAPI;
import api.GetByRoomIdAPI;
import api.GetRoomSummaryAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;

import java.io.IOException;

public class EndToEndSteps {
    public static int bookingID;
    public static int roomId;
    @When("I get booking details by booking ID")
    public void iGetBookingDetailsByBookingID() throws IOException {
        bookingID=CreateBookingAPI.returnBookingId();
        String resourceDetails="UpdateBookingDetails";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetBookingByIDAPI.getBookingByID(resourcesAPI.getResources(), String.valueOf(bookingID));

    }
    @Then("I should see booking information")
    public void iShouldSeeBookingInformation() {
        GetBookingByIDAPI.verifyRoomsDetailsPresentInResponse();
    }
    @When("I get booking details for room ID")
    public void iGetBookingDetailsForRoomID() throws IOException {
        roomId= CreateBookingAPI.returnRoomId();
        String resourceDetails="GetByRoomId";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetByRoomIdAPI.getByRoomIdAPI_Call(resourcesAPI.getResources(),roomId);
    }


    @Then("the booking information should display")
    public void theBookingInformationShouldDisplay() {
        GetByRoomIdAPI.verifyRoomsDetailsPresentInResponse();

    }

    @When("the user asks the room booking summary for roomId")
    public void theUserAsksTheRoomBookingSummaryForRoomId() throws IOException {
        String resourceDetails="CreateBookingAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetRoomSummaryAPI.getRoomSummary(resourcesAPI.getResources(), String.valueOf(roomId));
    }

    @And("the response should contain a empty list of bookings")
    public void theResponseShouldContainAEmptyListOfBookings() {
        GetRoomSummaryAPI.checkResponse_For_InvalidRoomId();

    }
}
