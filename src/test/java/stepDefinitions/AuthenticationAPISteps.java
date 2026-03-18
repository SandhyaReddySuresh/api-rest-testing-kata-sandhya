package stepDefinitions;

import api.AdminAuthAPI;
import check.HealthCheck;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
}
