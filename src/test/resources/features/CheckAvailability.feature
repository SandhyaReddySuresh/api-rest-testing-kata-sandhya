@CheckAvailabilityAPI @HotelBookingRegression
Feature: Room Availability API
  As a hotel booking system user
  I want to check available rooms for a given date range
  So that I can view room details and prices

  Background:
    Given the booking system is operational
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the users should receive an authentication token

  # Positive scenarios
  Scenario: Successfully retrieve available rooms for valid date range
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


  # Negative scenarios
  Scenario: Request rooms with invalid numeric dates
    When I request rooms with check-in "5666666" and check-out "77777777"
    Then the response status should be 400
    And the response should contain an error message indicating "Invalid date format"

  Scenario: Request rooms with invalid date format
    When I request rooms with check-in "17-07-2025" and check-out "18-07-2025"
    Then the response status should be 400
    And the response should contain an error message indicating "Invalid date format"

  Scenario: Request rooms for a date in the past
    When I request rooms with check-in "2000-01-01" and check-out "2000-01-02"
    Then the response status should be 404
    And the response should contain an error message indicating "No rooms available for the selected dates"

  Scenario: Request rooms with check-out date before check-in date
    When I request rooms with check-in "2025-07-18" and check-out "2025-07-17"
    Then the response status should be 400
    And the response should contain an error message indicating "Check-out date must be after check-in date"

