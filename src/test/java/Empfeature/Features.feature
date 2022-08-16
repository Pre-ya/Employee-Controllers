Feature: EmployeeController Feature

  Background: Create a Employee
    Given Creating a Employee

  Scenario: Verify that a Employee is created
    Then the user is created

  Scenario: Verify that the firstName is not given while creating an Employee
    When creating a Employee with firstName Blank
    Then Error is displayed when firstName is not given

  Scenario: Verify that the lastName is not given while creating an Employee
    When creating a Employee with lastName Blank
    Then Error is displayed when lastName is not given

  Scenario: Verify that the address is not given while creating an Employee
    When creating a Employee with address Blank
    Then  Error is displayed when address is not given

  Scenario: Verify that the phoneNumber is not given While Creating an Employee
    When creating a Employee with phoneNumber Blank
    Then  Error is displayed when phoneNumber is not given

  Scenario: Verify that the user able to get the details of Employees by using valid Endpoint
    When get the details of employee

  Scenario: Verify that the user not able to get the details of Employees by using invalid Endpoint
    When get the details of employee with invalid Endpoint
    Then Details of Employee displayed with invalid Endpoint

  Scenario: Verify that the user able to get the details of Employee by ID
    When get the details of employee with ID
    Then Details of Employee displayed With ID

  Scenario: Verify that the user able to get the details of Employees without using ID
    When get the details of employees without using ID

  Scenario: Verify that a Employee is updated
    When Update the user
    Then the user is updated

  Scenario: Verify that ID is not given While Updating an Employee
    When Updating a Employee without ID
    Then  Error is displayed when ID is not given

  Scenario: Verify that firstName is not given While Updating an Employee
    When Updating an Employee with firstName Blank
    Then Error is displayed when firstName is not given While updating

  Scenario: Verify that the lastName is not given While Updating an Employee
    When Updating an Employee with lastName Blank
    Then  Error is displayed when lastName is not given While updating

  Scenario: Verify that phoneNumber is not given While Updating an Employee
    When Updating an Employee with phoneNumber Blank
    Then  Error is displayed when phoneNumber is not given While updating

  Scenario: Verify that a Employee is deleted
    Then the user is deleted

  Scenario: Verify that error is thrown when ID is not given While Deleting a Employee
    When Deleting an Employee without ID
    Then  Error is displayed when ID is not given While Deleting a Employee

