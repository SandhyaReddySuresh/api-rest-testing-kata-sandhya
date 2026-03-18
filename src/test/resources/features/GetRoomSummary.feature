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