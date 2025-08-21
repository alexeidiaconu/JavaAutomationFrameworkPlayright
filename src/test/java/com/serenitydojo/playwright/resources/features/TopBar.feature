Feature: User Dropdown Flow

  Background: User logged in successfully
  Given Admin User is logged in with valid credentials
  @Smoke
  Scenario Outline: Verify User Dropdown Menu Actions
    Given the top bar is visible
    When the top bar user dropdown is clicked
    And the " <option> " option is selected from the dropdown menu
    Then the corresponding "<page title> " is displayed on the opened page

    Examples:
      | option          | page title                     |
      | About           | About                          |
      | Support         | Getting Started with OrangeHRM |
      | Change Password | Update Password                |
      | Logout          | Login                          |







