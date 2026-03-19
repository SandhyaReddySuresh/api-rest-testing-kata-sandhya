@BookingDetailsByRoomId
Feature: View room booking details

  As a customer
  I want to see booking details for a specific room
  So that I can know who has booked the room and the stay dates
  Background:
    Given the booking system is operational
    When the admin enters the correct username and password
      | username | password |
      | admin    | password |
    Then the login should be successful
    And the users should receive an authentication token

#  Scenario Outline: View booking details for a room
#    When the customer selects room "<roomid>" to view booking details
#    Then the booking details should be displayed successfully
#    And the booking should include:
#      | bookingid     |
#      | roomid        |
#      | firstname     |
#      | lastname      |
#      | depositpaid   |
#      | bookingdates  |
#
#    Examples:
#      | roomid |
#      | 1      |
#      | 2      |
#

  Scenario Outline: Invalid room selection
    When the customer selects an invalid room "<roomid>" to check booking details
    Then the customer should see a message indicating the room selection is invalid
    Examples:
      | roomid |
      | 99999  |
      | abc    |
      | -1     |
