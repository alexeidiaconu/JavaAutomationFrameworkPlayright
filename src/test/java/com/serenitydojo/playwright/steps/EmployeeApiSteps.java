package com.serenitydojo.playwright.steps;

import com.serenitydojo.playwright.api.actions.ApiActions;
import com.serenitydojo.playwright.utils.ApiUtils;
import com.serenitydojo.playwright.utils.enums.ApiUrls;
import com.serenitydojo.playwright.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;

import static com.serenitydojo.playwright.utils.enums.ContextKeys.API_RESPONSE;
import static com.serenitydojo.playwright.utils.enums.ContextKeys.API_RESPONSE_CODE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;

public class EmployeeApiSteps {
    ScenarioContext scenarioContext = ScenarioContext.getInstance();


    @Given("An employee record identified by Employee Number {}")
    public void AnEmployeeRecordIdentifiedByEmployeeId(String employeeNumber) {
        System.out.println("An employee record identified by " + employeeNumber);

    }

    @When("A Get request is sent to the Get_An_Employee endpoint with this {}")
    public void aGetRequestIsSentToTheGet_An_EmployeeEndpointWithThis(String employeeNumber) {

        ApiActions.GetAPI(((ApiUrls.GET_AN_EMPLOYEE.getUrl())) + employeeNumber);
    }


    @And("the response contains:")
    public void theResponseContains(io.cucumber.datatable.DataTable expectedData) {
        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        Map<String, Object> actualData = (Map<String, Object>) scenarioContext.getScenarioContext(API_RESPONSE);

        ApiUtils.compareExpectedWithActualData(expectedData,actualData);
    }

    @Given("An new Employee record with FirstName {} and Last Name {}  will be created")
    public void anNewEmployeeRecordWithFirstNameAndLastNameWillBeCreated(String firstName, String lastName) {
        System.out.println("Creating a newEmployee record: " + firstName + " " + lastName);
    }

    @When("A POST request is sent to the Create Employee endpoint with data for Employee Creation:")
    public void aPOSTRequestIsSentToTheCreateEmployeeEndpointWithDataForEmployeeCreation(io.cucumber.datatable.DataTable inputData) {
        ApiUtils.assertResponseCode(200, ApiActions.PostAPI(ApiUrls.EMPLOYEE,inputData));
    }

    @Then("The response from the server should be {int}")
    public void theResponseFromTheServerShouldBe(int expectedResponse) {

        Assertions.assertEquals(scenarioContext.getScenarioContext(API_RESPONSE_CODE), expectedResponse);
        System.out.println("The response for the POST/DELETE request is: " + expectedResponse);

    }

    @And("The created Employee should exist in the system")
    public void theCreatedEmployeeShouldExistInTheSystem() {
        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        System.out.println("Verifying if the Employee is created");

       String newEmpNumber = ApiUtils.extractFieldValueFromResponse("empNumber");
        ApiUtils.assertResponseCode(200, ApiActions.GetAPI(ApiUrls.GET_AN_EMPLOYEE.getUrl() + newEmpNumber));

    }

    @Given("An Employee record having Employee Number {} exists in the system")
    public void anEmployeeRecordHavingEmployeeNumberExistsInTheSystem(String empNumber) {
        ApiUtils.assertResponseCode(200, ApiActions.GetAPI(ApiUrls.GET_AN_EMPLOYEE.getUrl() + empNumber));
    }

    @When("A DELETE request is sent for this {}")
    public void aDELETERequestIsSentForThis(String empNumber) {
        ApiUtils.assertResponseCode(200, ApiActions.DeleteAPI(ApiUrls.EMPLOYEE,empNumber));
    }

    @And("The deleted Employee with Employee Number {} should NOT exist in the system")
    public void theDeletedEmployeeShouldNOTExistInTheSystem(String empNumber) {
        ApiUtils.assertResponseCode(422, ApiActions.GetAPI(ApiUrls.GET_AN_EMPLOYEE.getUrl() + empNumber));
    }
}
