@HotelBooking-Regression
@GetByRoomId
Feature: View Room Details
  As a guest or customer
  I want to see the details of a room
  So that I can know what the room offers and how much it costs

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

  Scenario: Viewing a room
    Given I want to check a room
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


#    Negative scenario
  Scenario Outline: User tries to fetch room details with invalid inputs
    When the user requests room details with room ID "<room_id>"
    Then the API should return an error
    And the error message should be <error_message>

    Examples:
      | room_id | error_message         |
      | abc     | "Invalid room ID"     |
      | -1      | "Room not found"      |
      | 99999   | "Room not found"      |
      | 1.5     | "Invalid room ID"     |
      | !@#     | "Invalid room ID"     |
      |         | "Room ID is required" |
      | null    | "Invalid room ID"     |