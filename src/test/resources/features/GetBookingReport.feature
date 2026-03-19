@HotelBooking-Regression
@BookingReport
Feature: Generate hotel booking report
  In order to monitor hotel performance and occupancy
  As a hotel manager
  I want to generate a booking report
  So that I can review booking and revenue information

  Background:
    Given the booking system is operational
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the users should receive an authentication token

  Scenario: Hotel manager generates a booking report
    When the hotel manager requests the booking report
    Then the system should generate the booking report