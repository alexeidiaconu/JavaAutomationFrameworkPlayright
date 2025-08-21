Feature: Employee Flow
  Background:
  Scenario Outline: Add new employee
    Given Admin User is logged in with valid credentials
    And The side menu is visible
    And The PIM menu item is clicked
    When the Add button is pressed
    And The Add Employee form is displayed
    And The form is populated with valid <firstName> and <lastName> and <employeeId>  values
    And The Save button is pressed
    Then user is redirected to Personal Details page
    Examples:
      | firstName     | lastName     | employeeId |
      | TestfirstName | TestlastName | 2222       |

Scenario Outline: Display the Employee list
  Given Admin User is logged in with valid credentials
  And The side menu is visible
  And The <menuItem> menu item is clicked
  And the <menuItem> page is displayed
  When the Employee List tab option is clicked
  Then The list of all the employees is displayed
  Examples:
    | menuItem |
    | PIM      |


