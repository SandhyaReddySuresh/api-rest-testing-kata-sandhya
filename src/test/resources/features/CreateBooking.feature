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
      | firstname   | lastname   | depositpaid   | checkin   | checkout   | phone   |
      | <firstname> | <lastname> | <depositpaid> | <checkin> | <checkout> | <phone> |
    Then the system confirms the booking
    And a booking ID should be returned
    And the booking information should be accurate and complete

    Examples:
      | firstname | lastname | depositpaid | checkin    | checkout   | phone       |
      | trisha1   | vijay1   | false       | 2026-07-16 | 2026-07-18 | 07358480685 |
      | arjun     | kumar    | true        | 2026-08-01 | 2026-08-05 | 07123456789 |
      | priya     | singh    | false       | 2026-09-10 | 2026-09-12 | 07987654321 |

  Scenario Outline: Booking fails with invalid or missing input data
    When the customer submits a booking request with the following details:
      | firstname   | lastname   | depositpaid   | checkin   | checkout   | phone   |
      | <firstname> | <lastname> | <depositpaid> | <checkin> | <checkout> | <phone> |
    Then the system should reject the booking
    And an error message "<error_message>" should be returned

    Examples:
      | firstname | lastname | depositpaid | checkin    | checkout   | phone       | error_message                         |
      |           | kumar    | true        | 2026-08-01 | 2026-08-05 | 07123456789 | Missing firstname                     |
      | arjun     |          | true        | 2026-08-01 | 2026-08-05 | 07123456789 | Missing lastname                      |
      | arjun     | kumar    | true        |            | 2026-08-05 | 07123456789 | Missing checkin date                  |
      | arjun     | kumar    | true        | 2026-08-01 |            | 07123456789 | Missing checkout date                 |
      | arjun     | kumar    | true        | 2026-08-05 | 2026-08-01 | 07123456789 | Invalid date range                    |
      | priya     | singh    | false       | 2026-09-10 | 2026-09-12 | abc123      | Invalid phone number                  |
      | raj       | patel    | true        | 2026-10-01 | 2026-10-03 | 123         | Invalid phone number                  |
      | ravi      | kumar    | false       | 2024-01-01 | 2024-01-05 | 07123456789 | Check-in date cannot be in the past   |
      | john      | doe      | invalid     | 2026-07-10 | 2026-07-12 | 07123456789 | Invalid deposit value                 |
      | arjun     | kumar    | true        | 2026-08-01 | 2026-08-05 |             | Missing phone                         |