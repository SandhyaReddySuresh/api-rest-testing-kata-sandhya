package stepDefinitions;

import api.AdminAuthAPI;
import check.HealthCheck;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utils.APIResources;
import utils.ConfigReader;

import java.io.IOException;
import java.util.Map;

public class AuthenticationAPISteps {

    @Given("the booking system is operational")
    public void the_booking_system_is_operational() {
        HealthCheck.checkBookingSystemOperational();

    }

    @When("the admin enters the correct username and password")
    public void theAdminEntersTheCorrectUsernameAndPassword(DataTable dataTable) throws IOException {
        Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0);
        String username = data.get("username");
        String password = data.get("password");
        String resourceDetails="AuthAdmin";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        AdminAuthAPI.postAuthAdmin(resourcesAPI.getResources(),username,password);
    }

    @Then("the login should be successful")
    public void theLoginShouldBeSuccessful() throws IOException {
       int statusCode= ConfigReader.getIntProperty("SuccessStatusCode");
       AdminAuthAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @And("the users should receive an authentication token")
    public void theUsersShouldReceiveAnAuthenticationToken() {
        AdminAuthAPI.checkTokenDetails();

    }

    @When("the admin enters an incorrect password")
    public void theAdminEntersAnIncorrectPassword(DataTable dataTable) throws IOException {
        Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0);
        String username = data.get("username");
        String password = data.get("password");
        String resourceDetails="AuthAdmin";
        APIResources resourcesAPI=APIResources.valueOf(resourceDetails);
        AdminAuthAPI.postAuthAdmin(resourcesAPI.getResources(),username,password);
    }

    @Then("the login should fail")
    public void theLoginShouldFail() throws IOException {
        int statusCode= ConfigReader.getIntProperty("UnauthorizedStatusCode");
        AdminAuthAPI.checkStatusCode(String.valueOf(statusCode));
    }

    @And("the admin should see an error message saying the credentials are invalid")
    public void theAdminShouldSeeAnErrorMessageSayingTheCredentialsAreInvalid() {
        AdminAuthAPI.checkErrorMessage_ForUnUnauthorizedUser();

    }

    @And("the response should conform to the expected schema")
    public void theResponseShouldConformToTheExpectedSchema() {
        AdminAuthAPI.checkSchemaValidation();

    }
}
