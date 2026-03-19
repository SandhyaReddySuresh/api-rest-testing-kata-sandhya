@ListOfRoomsDetails @HotelBookingRegression
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

  Scenario: Validate rooms response against JSON schema
    When the user sends a GET request to fetch rooms from the system
    Then the system should return a successful response
    And the response should contain a list of rooms
    And the response body should conform to the expected JSON schema

  Scenario: User accesses an invalid resource
    When the user sends a request to an incorrect API endpoint
    Then the system should return resource not found

  Scenario: User provides an invalid identifier
    When the user requests data using an invalid identifier
    Then the system should return resource not found

  Scenario: User uses an unsupported request method
    When the user sends a request using an unsupported HTTP method
    Then the system should return status code 405
    And the user should see a "Method Not Allowed" message