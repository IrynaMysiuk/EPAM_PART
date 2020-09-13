Feature: Invalid users
  In order to get information from Gmail
  The user must be unregistered.

  Background: User is Logged In
    Given I navigate to Gmail page
    When I verify that current url contains correct url
    And I open Gmail page
    And I verify the current url contain 'Gmail' word
    Then I click 'Login'

  @selenium
  Scenario Outline: Successfully Login with Credentials to Website

    Given I navigate to login page
    When I switch to Gmail login page
    And I input in the login field <email>
    Then I get error message


    Examples:Invalid User
      | email                        |
      | user@gmail.com               |
      | user2@gmail.com              |
      | seleniumlab12test3@gmail.com |
