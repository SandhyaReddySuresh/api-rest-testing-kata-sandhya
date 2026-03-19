@EndToEndBooking @HotelBookingRegression
Feature: End-to-End Room Booking Management

  As an admin
  I want to manage room bookings from creation to report generation
  So that I can ensure rooms are properly booked, edited, or canceled

  Background:
    Given the booking system is operational
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the users should receive an authentication token

  Scenario: Full end-to-end booking workflow
    # Step 1: List rooms and check availability
    When the user sends a GET request to fetch rooms from the system
    Then the system should return a successful response
    And the response should contain a list of rooms
    And each room should have the following fields:
      | roomid      |
      | roomName    |
      | type        |
      | accessible  |
      | image       |
      | description |
      | features    |
      | roomPrice   |

    When I request rooms with check-in "2025-07-17" and check-out "2025-07-18"
    Then the response status should be 200
    And the response should contain a list of available rooms
    And each room in the list should have the following fields:
      | roomid      |
      | roomName    |
      | type        |
      | accessible  |
      | image       |
      | description |
      | features    |
      | roomPrice   |
    And Room availability information is returned in a format compliant with the JSON schema


    # Step 2: Get details of the room (optional)
    When I ask the system for the details of the room
    Then I should get the correct information about the room:
      | Field      | Value  |
      | Room Name  | (any)  |
      | Room Type  | (any)  |
      | Accessible | Yes/No |
      | Price      | (any)  |
    And the room should include features such as:
      | Radio |
      | WiFi  |
      | Safe  |
    And the room should have a description explaining it
    And the room should have an image to view
    And the response body should match the expected JSON schema

    # Step 3: Create a new booking
    When the customer submits a booking request with the following details:
      | roomid | firstname  | lastname  | depositpaid | checkin    | checkout   | phone       |
      | 41     | CFirstName | CLastName | false       | 2026-07-16 | 2026-07-18 | 07358480685 |
    Then the system confirms the booking
    And the booking information should be accurate and complete
    And a booking ID should be returned
    And a room Id should be returned

    # Step 4: Verify booking by booking id and with room id
    When I get booking details by booking ID
    Then I should see booking information

    # Step 5: Edit booking (optional)
    When the user updates the existing booking with the following details:
      | firstname  | lastname  | depositpaid | checkin    | checkout   |
      | BFirstName | BLastName | false       | 2026-01-11 | 2026-01-12 |
    Then the booking should be successfully updated

    # Step 6: Delete booking (optional)
    And a booking exists with booking id
    When the user cancels the booking
    Then the booking should be successfully cancelled
    And the booking should no longer be retrievable
    And the response should contain an error message "Failed to fetch booking: 404"

    # Step 7: Get room summary (optional)
    When the user asks the room booking summary for roomId
    Then the room booking summary response should be successful
    And the response should contain a empty list of bookings

    # Step 8: Generate booking report
    When the hotel manager requests the booking report
    Then the system should generate the booking report