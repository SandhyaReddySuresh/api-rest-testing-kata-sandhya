package stepDefinitions;

import api.CreateBookingAPI;
import api.DeleteBookingAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;

public class DeleteBookingSteps {

    public static String bookingId;
    @And("a booking exists with booking id")
    public void aBookingExistsWithBookingId() {
        bookingId= String.valueOf(CreateBookingAPI.returnBookingId());
    }

    @When("the user cancels the booking")
    public void theUserCancelsTheBooking() throws IOException {
        String resourceDetails="CreateBookingAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        DeleteBookingAPI.deleteBookingByBookingIDAPI_Call(resourcesAPI.getResources(),bookingId);
    }

    @Then("the booking should be successfully cancelled")
    public void theBookingShouldBeSuccessfullyCancelled() throws IOException {
        int statusCode= ConfigReader.getIntProperty("SuccessStatusCode");
        DeleteBookingAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @And("the booking should no longer be retrievable")
    public void theBookingShouldNoLongerBeRetrievable() {

    }

    @When("the user attempts to delete a booking with an invalid booking ID")
    public void theUserAttemptsToDeleteABookingWithAnInvalidBookingID() {
    }

    @Then("the deletion should fail")
    public void theDeletionShouldFail() {
    }
}
