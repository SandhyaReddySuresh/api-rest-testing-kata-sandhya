@CreateBooking @HotelBookingRegression
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
      | roomid | firstname  | lastname  | depositpaid | checkin    | checkout   | phone       |
      | 107    | EFirstName | FLastName | false       | 2026-07-16 | 2026-07-18 | 07358480685 |
      | 108    | arjun      | kumar     | true        | 2026-08-01 | 2026-08-05 | 07123456789 |
      | 109    | priya      | singh     | false       | 2026-09-10 | 2026-09-12 | 07987654321 |


  Scenario Outline: Booking fails with invalid or missing input data
    When the customer submits a booking request with the following details:
      | roomid   | firstname   | lastname   | depositpaid   | checkin   | checkout   | phone   |
      | <roomid> | <firstname> | <lastname> | <depositpaid> | <checkin> | <checkout> | <phone> |
    Then the system should reject the booking
    And an error message <error_message> should be returned

    Examples:
      | roomid | firstname  | lastname  | depositpaid | checkin    | checkout   | phone       | error_message                  |
      | 101    |            | qLastName | true        | 2026-08-01 | 2026-08-05 | 07129856789 | Firstname should not be blank  |
      | 102    | EFirstName |           | true        | 2026-08-01 | 2026-08-05 | 07123456776 | Lastname should not be blank   |
      | 103    | RFirstName | kLastName | true        |            | 2026-08-05 | 07123456732 | must not be null               |
      | 104    | fFirstName | dLastName | true        | 2026-08-01 |            | 07123456774 | must not be null               |
      | 105    | qFirstName | jLastName | true        | 2026-08-05 | 2026-08-01 | 07123486789 | Failed to create booking       |
      | 106    | aFirstName | qLastName | false       | 2026-09-10 | 2026-09-12 | abc1276     | size must be between 11 and 21 |
      | 107    | cFirstName | uLastName | true        | 2026-10-01 | 2026-10-03 | 193         | size must be between 11 and 21 |
      | 108    | aFirstName | hLastName | false       | 2024-01-01 | 2024-01-05 | 07343456789 | Failed to create booking       |
      | 109    | oFirstName | yLastName | invalid     | 2026-07-10 | 2026-07-12 | 07187456789 | Failed to create booking       |
      | 110    | hFirstName | aLastName | true        | 2026-08-01 | 2026-08-05 |             | Failed to create booking       |