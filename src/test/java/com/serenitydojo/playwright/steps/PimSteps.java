package com.serenitydojo.playwright.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.utils.enums.Constants;
import com.serenitydojo.playwright.ui.blocks.SideMenu;
import com.serenitydojo.playwright.ui.pages.AddEmployeePage;
import com.serenitydojo.playwright.ui.pages.PimPage;
import com.serenitydojo.playwright.utils.ScenarioContext;
import com.serenitydojo.playwright.utils.WebElementActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static com.serenitydojo.playwright.utils.enums.ApiUrls.DELETE_EMPLOYEE;
import static com.serenitydojo.playwright.utils.enums.Constants.WAIT_TIMEOUT;
import static com.serenitydojo.playwright.utils.enums.ContextKeys.SIDE_MENU;

public class PimSteps {
    ScenarioContext scenarioContext = ScenarioContext.getInstance();
    SideMenu sideMenu = (SideMenu) scenarioContext.getScenarioContext(SIDE_MENU);
    PimPage pimPage = new PimPage();
    AddEmployeePage addEmployeePage = new AddEmployeePage();

//    @After
//    public void after() {
//        pimPage.closePage();
//    }

    @And("The side menu is visible")
    public void theSideMenuIsVisible() {
        sideMenu.waitForPageToBeVisible(WAIT_TIMEOUT);
        PlaywrightAssertions.assertThat(sideMenu.getCurrentPage().getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Sidepanel") )).isVisible(); //???
    }

    @When("the Employee List tab option is clicked")
    public void theEmployeeListTabOptionIsClicked() {

        if (WebElementActions.waitForWebElementToBeVisible(pimPage.getMenuItemEmployeeList(),Constants.WAIT_TIMEOUT)) {
            WebElementActions.clickOnButton(pimPage.getMenuItemEmployeeList());
        }


    }

    @Then("The list of all the employees is displayed")
    public void theListOfAllTheEmployeesIsDisplayed() {
        Assertions.assertTrue(WebElementActions.waitForWebElementToBeVisible(pimPage.getListOfEmployees(), WAIT_TIMEOUT));

    }

    @When("the Add button is pressed")
    public void theAddButtonIsPressed() {
        WebElementActions.clickOnButton(pimPage.getButtonAddEmployee());
    }

    @And("The Add Employee form is displayed")
    public void theAddEmployeeFormIsDisplayed() {
        Assertions.assertTrue(WebElementActions.waitForWebElementToBeVisible(addEmployeePage.getTitleAddEmployee(),WAIT_TIMEOUT));
    }

    @And("The form is populated with valid {} and {} and {}  values")
    public void theFormIsPopulatedWithValidAndAndValues(String firstName, String lastName, String employeeId) {

        WebElementActions.populateField(addEmployeePage.getFirstNameField(),firstName);
        WebElementActions.populateField(addEmployeePage.getLastNameField(),lastName);
        WebElementActions.populateField(addEmployeePage.getEmployeeIdField(),employeeId.trim());
    }

    @And("The Save button is pressed")
    public void theSaveButtonIsPressed() {
        WebElementActions.clickOnButton(addEmployeePage.getButtonSave());
    }

    @Then("user is redirected to Personal Details page")
    public void userIsRedirectedToPersonalDetailsPage() {

        Locator personalDetailsTitle = WebElementActions.locateHeadingByText(pimPage.getCurrentPage(),"Personal Details");

        if (personalDetailsTitle != null) {
            WebElementActions.waitForWebElementToBeVisible(personalDetailsTitle,WAIT_TIMEOUT);
        }

        PlaywrightAssertions.assertThat(personalDetailsTitle).isVisible();
    }

    @When("the Employee to delete is selected from Employee list by {} and {} and {}  values")
    public void theEmployeeToDeleteIsSelectedFromEmployeeListByAndAndValues(String employeeFirstname, String employeeLastname, String employeeId) {
        pimPage.setEmployeeCard(employeeFirstname,employeeLastname,employeeId);

    }

    @And("The Delete button is pressed")
    public void theDeleteButtonIsPressed() {

        pimPage.getEmployeeCard().getEmployeeCardRow().scrollIntoViewIfNeeded();
        WebElementActions.waitForWebElementToBeVisible( pimPage.getEmployeeCard().getEmployeeCardRow(),WAIT_TIMEOUT);
        Locator trashButton = pimPage.getEmployeeCard().getButtonDeleteEmployee();
        WebElementActions.clickOnButton(trashButton);
    }


    @And("the Employee with {} First Name  and {} Last Name and {} ID is not present in the Employee list.")
    public void theEmployeeWithFirstNameAndLastNameAndIDIsNotPresentInTheEmployeeList(String employeeFirstname, String employeeLastname, String employeeId) {

        pimPage.setEmployeeCard(employeeFirstname, employeeLastname, employeeId);
        WebElementActions.waitForWebElementToBeVisible(pimPage.getListOfEmployees(),WAIT_TIMEOUT);
        Assertions.assertFalse(pimPage.getEmployeeCard().getEmployeeCardRow().isVisible());
    }

    @And("The Yes,Delete button is pressed on the confirmation popup")
    public void theYesDeleteButtonIsPressedOnTheConfirmationPopup() {
        Locator yesDeleteButton  = pimPage.getCurrentPage().getByRole(AriaRole.BUTTON).getByText("Yes, Delete");
        WebElementActions.waitForWebElementToBeVisible(yesDeleteButton,WAIT_TIMEOUT);


        pimPage.getCurrentPage().waitForResponse(response -> response.url().contains(DELETE_EMPLOYEE.getUrl()) && response.status() == 200,
                () -> {
                    WebElementActions.clickOnButton(yesDeleteButton);
                }
        );
    }
}
