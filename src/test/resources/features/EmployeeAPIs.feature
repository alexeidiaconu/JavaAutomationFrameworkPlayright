Feature: Employee APIs Flows
  @API
  Scenario Outline: Retrieve details for a specific employee by Id

    Given An employee record identified by Employee Number <employee_number>
    When A Get request is sent to the Get_An_Employee endpoint with this <employee_number>
    Then The response from the server should be 200
    And the response contains:
      | field      | value          |
      | empNumber  | <employee_number>  |
      | firstName  | <first_name>   |
      | lastName   | <last_name>    |
      | employeeId | <employee_id>  |


    Examples:
      | employee_number | first_name | last_name | employee_id |
      | 6               | Alexei     | Diaconu   | 0006        |
      | 177             | Byron      | Sporer    | 258361495   |
      | 25              | Harry      | Walker    | 0024        |

  @API
  Scenario Outline: Create an Employee with specific details

    Given An new Employee record with FirstName <first_name> and Last Name <last_name>  will be created
    When A POST request is sent to the Create Employee endpoint with data for Employee Creation:

      | firstName  | <first_name>  |
      | lastName   | <last_name>   |
      | employeeId | <employee_id> |

    Then The response from the server should be 200
    And The created Employee should exist in the system
    Examples:
      | first_name | last_name | employee_id |
      | Elton      | John      | 69          |

  @API
  Scenario Outline: Delete an Employee by Employee Number
    Given An Employee record having Employee Number <employee_number> exists in the system
    When A DELETE request is sent for this <employee_number>
    Then The response from the server should be 200
    And The deleted Employee with Employee Number <employee_number> should NOT exist in the system
    Examples:
      | employee_number |
      | 119             |