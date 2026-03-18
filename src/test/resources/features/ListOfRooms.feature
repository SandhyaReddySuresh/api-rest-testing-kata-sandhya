@ListOfRoomsDetails
Feature: Fetch all rooms
  As a user
  I want to fetch all rooms from the system
  So that I can see which rooms are available

  Background:
    Given the booking system is operational

  Scenario: Retrieve the list of all rooms successfully
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
