@Login
Feature: Hotel booking login
   Login to Hotel Booking System
   As a guest or hotel manager
   I want to log in using valid credentials
   So that I can access the hotel booking system and perform my tasks

  Background:
    Given the booking system is operational

    @Authentication
  Scenario: Users logs in with valid credentials
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the users should receive an authentication token