@UpdateBooking
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
      | firstname   | lastname   | depositpaid   | checkin   | checkout   | phone   |
      | <firstname> | <lastname> | <depositpaid> | <checkin> | <checkout> | <phone> |
    Then the system confirms the booking
    And a booking ID should be returned
    And a room Id should be returned
    Examples:
      | firstname | lastname | depositpaid | checkin    | checkout   | phone       |
      | trisha1   | vijay1   | false       | 2026-07-16 | 2026-07-18 | 07358480685 |

  Scenario : Update an existing booking
    When the user updates the existing booking with the following details:
      | firstname | lastname | depositpaid | checkin    | checkout   |
      | Lion      | Dear     | false       | 2026-01-11 | 2026-01-12 |
    Then the booking should be successfully updated with response code 200
