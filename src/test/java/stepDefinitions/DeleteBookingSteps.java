package stepDefinitions;

import api.CreateBookingAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteBookingSteps {
    @And("a booking exists with booking id")
    public void aBookingExistsWithBookingId() {
        CreateBookingAPI.returnBookingId();
    }

    @When("the user cancels the booking")
    public void theUserCancelsTheBooking() {

    }

    @Then("the booking should be successfully cancelled")
    public void theBookingShouldBeSuccessfullyCancelled() {

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
