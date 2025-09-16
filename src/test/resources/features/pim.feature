Feature: Employee Flow
  Background:
    Given Admin User is logged in with valid credentials
    And The side menu is visible
    And The PIM menu item is clicked
@Employee
  Scenario Outline: Add new employee

    Given the PIM page is displayed
    When the Add button is pressed
    And The Add Employee form is displayed
    And The form is populated with valid <firstName> and <lastName> and <employeeId>  values
    And The Save button is pressed
    Then user is redirected to Personal Details page
    Examples:
      | firstName     | lastName     | employeeId |
      | TestfirstName | TestlastName | 2222       |
  @Employee
Scenario Outline: Display the Employee list

  Given the <menuItem> page is displayed
  When the Employee List tab option is clicked
  Then The list of all the employees is displayed
  Examples:
    | menuItem |
    | PIM      |
  @Employee
  Scenario Outline: Delete employee

    Given the PIM page is displayed
    And the Employee List tab option is clicked
    And The list of all the employees is displayed
    When the Employee to delete is selected from Employee list by <firstName> and <lastName> and <employeeId>  values
    And The Delete button is pressed
    And The Yes,Delete button is pressed on the confirmation popup
    Then The list of all the employees is displayed
    And the Employee with <firstName> First Name  and <lastName> Last Name and <employeeId> ID is not present in the Employee list.
    Examples:
      | firstName     | lastName     | employeeId |
      | TestfirstName | TestlastName | 2222       |


