Feature: User Dropdown Flow

  Scenario Outline: Verify User Dropdown Actions
    Given The top bar is displayed
    When the top bar user dropdown is clicked
    And " <option> " option is selected from the dropdown menu
    Then an "<action> " is taking place

    Examples:
      | option          | action                           |
      | About           | theAboutPageIsDisplayed          |
      | Support         | theSupportPageIsDisplayed        |
      | Change Password | thePasswordChangePageIsDisplayed |
      | Logout          | Logout                           |







