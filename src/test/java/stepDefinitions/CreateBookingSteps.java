package stepDefinitions;

import api.AdminAuthAPI;
import api.CreateBookingAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;
import java.util.Map;

public class CreateBookingSteps {
    @When("the customer submits a booking request with the following details:")
    public void theCustomerSubmitsABookingRequestWithTheFollowingDetails(DataTable dataTable) throws IOException {
        Map<String, String> bookingData = dataTable.asMaps(String.class, String.class).get(0);
        String roomId= bookingData.get("roomid");
        String firstname = bookingData.get("firstname");
        String lastname=bookingData.get("lastname");
        boolean depositPaid= Boolean.parseBoolean(bookingData.get("depositpaid"));
        String checkIn=bookingData.get("checkin");
        String checkOut=bookingData.get("checkout");
        String phone=bookingData.get("phone");

        String resourceDetails="CreateBookingAPI";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        CreateBookingAPI.postCreateBooking(resourcesAPI.getResources(),roomId,firstname,lastname,depositPaid,checkIn,checkOut,phone);

    }

    @Then("the system confirms the booking")
    public void theSystemConfirmsTheBooking() throws IOException {
        int statusCode= ConfigReader.getIntProperty("Created");
        CreateBookingAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @And("a booking ID should be returned")
    public void aBookingIDShouldBeReturned() {
        CreateBookingAPI.returnBookingId();
    }

    @And("the booking information should be accurate and complete")
    public void theBookingInformationShouldBeAccurateAndComplete() {
        CreateBookingAPI.checkBookingDetails();
    }

    @Then("the system should reject the booking")
    public void theSystemShouldRejectTheBooking() {
        CreateBookingAPI.checkInvalidStatusCode();
    }

    @And("an error message {} should be returned")
    public void anErrorMessageShouldBeReturned(String errorMessage) {
        CreateBookingAPI.checkErrorMessage(errorMessage);

    }

    @And("the response body should match the expected JSON schema {string}")
    public void theResponseBodyShouldMatchTheExpectedJSONSchema(String schemaJson) {
        CreateBookingAPI.checkSchemaValidation(schemaJson);
    }


    @And("a room Id should be returned")
    public void aRoomIdShouldBeReturned() {
        CreateBookingAPI.returnRoomId();

    }
}
