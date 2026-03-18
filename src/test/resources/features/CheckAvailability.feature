@CheckAvailabilityAPI
Feature: Room Availability API
  As a hotel booking system user
  I want to check available rooms for a given date range
  So that I can view room details and prices

  Background:
    Given the booking system is operational

  # Positive scenarios
  Scenario: Successfully retrieve available rooms for valid date range
    When I request rooms with check-in "2025-07-17" and check-out "2025-07-18"
    Then the response status should be 200
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
