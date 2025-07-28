Feature: Login Flow

  Scenario: Admin User Login with valid credentials
    Given The Login Page is accessed
    And valid credentials are populated in the username and password fields
    When the Submit button is clicked
    Then the application Dasboard page is displayed

  Scenario: Admin User Login with invalid credentials
    Given The Login Page is accessed
    And Invalid credentials are populated in the username and password fields
    When the Submit button is clicked
    Then The Login Page is displayed
    And An Alert message "Invalid credentials" is displayed on the Login page
