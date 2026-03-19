package stepDefinitions;

import api.GetBookingReportAPI;
import api.GetRoomSummaryAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;

public class GetBookingReportSteps {
    @When("the hotel manager requests the booking report")
    public void theHotelManagerRequestsTheBookingReport() throws IOException {
        String resourceDetails="GetReportSummary";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        GetBookingReportAPI.getBookingReportAPI_Call(resourcesAPI.getResources());
    }

    @Then("the system should generate the booking report")
    public void theSystemShouldGenerateTheBookingReport() throws IOException {
        int statusCode= ConfigReader.getIntProperty("Ok");

        GetBookingReportAPI.checkStatusCode(String.valueOf(statusCode));
        GetBookingReportAPI.getBookingReportSummaryAPI_Response();

    }


}
