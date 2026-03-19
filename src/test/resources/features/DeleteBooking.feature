@DeleteBooking
Feature: Cancel hotel booking
  As a guest
  I want to cancel an existing booking
  So that the room becomes available again

  Background:
    Given the booking system is operational
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the users should receive an authentication token

  Scenario: Cancel an existing booking successfully
    And a booking exists with booking ID
    And a booking exists with room ID
    When the user deletes the booking
    Then the booking should be deleted successfully
    And the user should receive a confirmation of deletion

  Scenario: Delete a booking using an invalid booking ID
    When the user attempts to delete a booking with an invalid booking ID
    Then the deletion should fail