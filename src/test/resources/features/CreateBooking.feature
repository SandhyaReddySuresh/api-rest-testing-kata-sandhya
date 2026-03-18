@CreateBooking
Feature: Customer Room Booking
As a guest or customer
I want to book a room through the booking system
So that I can reserve my stay and receive a booking confirmation with all details

  Scenario Outline: Customer successfully books a room
    Given the booking system is operational
    When the customer submits a booking request with the following details:
      | roomid   | firstname   | lastname   | depositpaid   | checkin   | checkout   | email   | phone   |
      | <roomid> | <firstname> | <lastname> | <depositpaid> | <checkin> | <checkout> | <email> | <phone> |
    Then the system confirms the booking
    And a booking ID should be returned
    And the booking information should be accurate and complete

    Examples:
      | roomid | firstname | lastname | depositpaid | checkin    | checkout   | email                 | phone       |
      | 200    | trisha1   | vijay1   | false       | 2026-07-16 | 2026-07-18 | suresh1@gmail.com     | 07358480685 |
      | 201    | arjun     | kumar    | true        | 2026-08-01 | 2026-08-05 | arjun.kumar@gmail.com | 07123456789 |
      | 202    | priya     | singh    | false       | 2026-09-10 | 2026-09-12 | priya.singh@gmail.com | 07987654321 |