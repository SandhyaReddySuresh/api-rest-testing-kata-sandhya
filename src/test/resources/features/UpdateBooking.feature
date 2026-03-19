@UpdateBooking @HotelBookingRegression
Feature: Edit hotel booking
  As a registered user
  I want to edit an existing booking
  So that I can update guest and stay details

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
      | 605    | KFirstName | DLastName | false       | 2026-07-16 | 2026-07-18 | 07387480685 |

  Scenario: Update an existing booking
    When the user updates the existing booking with the following details:
      | firstname | lastname | depositpaid | checkin    | checkout   |
      | Lion      | King     | false       | 2026-01-11 | 2026-01-12 |
    Then the booking should be successfully updated

  Scenario: View updated booking details for a room
    When the customer views the booking details for that updated room
    Then the booking details should reflect the latest updates
    And the booking should include:
      | bookingid     |
      | roomid        |
      | firstname     |
      | lastname      |
      | depositpaid   |
      | bookingdates  |
    And the response should match the booking JSON schema