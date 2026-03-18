@Login
Feature: Hotel booking login
   Login to Hotel Booking System
   As a guest or hotel manager
   I want to log in using valid credentials
   So that I can access the hotel booking system and perform my tasks

  Background:
    Given the booking system is operational

    @Authentication @SuccessLogin
  Scenario: Users logs in with valid credentials
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the users should receive an authentication token

   @Authentication @FailureLogin
   Scenario Outline:Admin tries to log in with an invalid password
    When the admin enters an incorrect password
      | username   | password   |
      | <username> | <password> |
     Then the login should fail
    And the admin should see an error message saying the credentials are invalid
    Examples:
      | username | password  |
      | admin2   | password1 |
      | admin3   | password2 |

  Scenario: Validate authentication response schema with valid structure
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the response should conform to the expected schema
