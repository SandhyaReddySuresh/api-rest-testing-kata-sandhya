@HotelBooking-Regression
@CreateBooking
Feature: Customer Room Booking
As a guest or customer
I want to book a room through the booking system
So that I can reserve my stay and receive a booking confirmation with all details
  Background:
    Given the booking system is operational
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the users should receive an authentication token

  Scenario Outline: Customer successfully books a room
    When the customer submits a booking request with the following details:
      | roomid   | firstname   | lastname   | depositpaid   | checkin   | checkout   | phone   |
      | <roomid> | <firstname> | <lastname> | <depositpaid> | <checkin> | <checkout> | <phone> |
    Then the system confirms the booking
    And the booking information should be accurate and complete
    And a booking ID should be returned
    And the response body should match the expected JSON schema "createBooking-schema.json"

    Examples:
      | roomid | firstname | lastname | depositpaid | checkin    | checkout   | phone       |
      | 106    | trisha1   | vijay1   | false       | 2026-07-16 | 2026-07-18 | 07358480685 |
      | 107    | arjun     | kumar    | true        | 2026-08-01 | 2026-08-05 | 07123456789 |
      | 108    | priya     | singh    | false       | 2026-09-10 | 2026-09-12 | 07987654321 |


  Scenario Outline: Booking fails with invalid or missing input data
    When the customer submits a booking request with the following details:
      | roomid   | firstname   | lastname   | depositpaid   | checkin   | checkout   | phone   |
      | <roomid> | <firstname> | <lastname> | <depositpaid> | <checkin> | <checkout> | <phone> |
    Then the system should reject the booking
    And an error message <error_message> should be returned

    Examples:
      | roomid | firstname | lastname | depositpaid | checkin    | checkout   | phone       | error_message                  |
      | 101    |           | kumar    | true        | 2026-08-01 | 2026-08-05 | 07123456789 | Firstname should not be blank  |
      | 102    | arjun     |          | true        | 2026-08-01 | 2026-08-05 | 07123456789 | Lastname should not be blank   |
      | 103    | arjun     | kumar    | true        |            | 2026-08-05 | 07123456789 | must not be null               |
      | 104    | arjun     | kumar    | true        | 2026-08-01 |            | 07123456789 | must not be null               |
      | 105    | arjun     | kumar    | true        | 2026-08-05 | 2026-08-01 | 07123456789 | Failed to create booking       |
      | 106    | priya     | singh    | false       | 2026-09-10 | 2026-09-12 | abc123      | size must be between 11 and 21 |
      | 107    | raj       | patel    | true        | 2026-10-01 | 2026-10-03 | 123         | size must be between 11 and 21 |
      | 108    | ravi      | kumar    | false       | 2024-01-01 | 2024-01-05 | 07123456789 | Failed to create booking       |
      | 109    | john      | doe      | invalid     | 2026-07-10 | 2026-07-12 | 07123456789 | Failed to create booking       |
      | 110    | arjun     | kumar    | true        | 2026-08-01 | 2026-08-05 |             | Failed to create booking       |