@DeleteBooking @HotelBookingRegression
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

  Scenario Outline: Customer successfully books a room
    When the customer submits a booking request with the following details:
      | roomid | firstname   | lastname   | depositpaid   | checkin   | checkout   | phone       |
      | <roomid> | <firstname> | <lastname> | <depositpaid> | <checkin> | <checkout> | <phone>   |
    Then the system confirms the booking
    And the booking information should be accurate and complete
    And a booking ID should be returned
    And a room Id should be returned
    Examples:
      | roomid | firstname  | lastname  | depositpaid | checkin    | checkout   | phone       |
      | 604    | DFirstName | DLastName | false       | 2026-07-16 | 2026-07-18 | 07358480685 |

  Scenario: Cancel an existing booking successfully
    And a booking exists with booking id
    When the user cancels the booking
    Then the booking should be successfully cancelled
    And the booking should no longer be retrievable
    And the response should contain an error message "Failed to fetch booking: 404"


  Scenario: Delete a booking using an invalid booking ID
    When the user attempts to delete a booking with an invalid booking ID
    Then the deletion should fail "Failed to delete booking"