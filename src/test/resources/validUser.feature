Feature: Valid users
  In order to get information from Gmail
  The user must be registered.

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
    Then I fill in the login field <email> and password <password>

    Given I navigate to letter page
    When I fill in field the invalid email <incorrectEmail> and subject <subject>
    And I verify that alert tab is displayed
    And I submit letter with incorrect address
    And I fill in field the valid email <sendTo>
    Then I verify that letter was sent


    Examples:Valid User
      | email                    | password   | sendTo               | subject                             | incorrectEmail |
      | seleniumlab12@gmail.com  | selenium12 | ira.mysiuk@gmail.com | Important message from IntellijIDEA | ira.mysiuk.com |
      | selenium1lab12@gmail.com | selenium12 | ira.mysiuk@gmail.com | Important message from IntellijIDEA | ira.mysiuk.com |
      | selenium2lab12@gmail.com | Lablab12s_ | ira.mysiuk@gmail.com | Important message from IntellijIDEA | ira.mysiuk.com |
      | selenium3lab12@gmail.com | Lablab12s_ | ira.mysiuk@gmail.com | Important message from IntellijIDEA | ira.mysiuk.com |
      | seleniumlab124@gmail.com | Lablab12s_ | ira.mysiuk@gmail.com | Important message from IntellijIDEA | ira.mysiuk.com |
