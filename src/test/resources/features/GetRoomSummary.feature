@RoomSummary
Feature: Room Booking Summary

  As a hotel guest or customer
  I want to view the booking summary for a specific room
  So that I can check who has booked the room and the booking details

  Background:
    Given the booking system is operational
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the users should receive an authentication token

 @GetRoomSummary
  Scenario Outline: Get the room booking summary
    When the user asks the room booking summary for roomId <roomid>
    Then the room booking summary response should be successful
    And the response should contain a list of bookings

    Examples:
      | roomid |
      | 1      |
      | 2      |

  Scenario: Unauthorized user cannot retrieve booking details
    Given the user is not authenticated
    When the user requests the booking summary for room ID 1
    Then the system denies access
    And an authentication error message "Authentication required" is displayed

  Scenario Outline: Retrieve bookings for a room
    When the user asks the room booking summary for roomId <roomid>
    Then the system should not show any valid room
    Examples:
      | roomid  |
      | 9999999 |

  Scenario: Booking summary returns error when room ID is missing
    When the user requests the booking summary without providing a room ID
    Then the system should respond with an error
    And the error message should be "Room ID is required"  is dispalyed