package com.serenitydojo.playwright.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.serenitydojo.playwright.ui.blocks.SideMenu;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PimSteps {
    SideMenu sideMenu = new SideMenu();

    @And("The side menu is visible")
    public void theSideMenuIsVisible() {
        sideMenu.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);
        PlaywrightAssertions.assertThat(sideMenu.getCurrentPage().getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Sidepanel") )).isVisible();
    }

    @When("the Employee List tab option is clicked")
    public void theEmployeeListTabOptionIsClicked() {
        Locator employeeListItem = sideMenu.getCurrentPage().locator("li[class='oxd-topbar-body-nav-tab --visited'] a[class='oxd-topbar-body-nav-tab-item']");
        employeeListItem.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        employeeListItem.click();
    }

    @Then("The list of all the employees is displayed")
    public void theListOfAllTheEmployeesIsDisplayed() {
        PlaywrightAssertions.assertThat(sideMenu.getCurrentPage().locator("div[role='table']")).isVisible();

        sideMenu.closePage();

    }

    @When("the Add button is pressed")
    public void theAddButtonIsPressed() {
       Locator addButton =  sideMenu.getCurrentPage().locator("//button[normalize-space()='Add']");
       addButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
       addButton.click();
    }

    @And("The Add Employee form is displayed")
    public void theAddEmployeeFormIsDisplayed() {
        PlaywrightAssertions.assertThat(sideMenu.getCurrentPage().getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Add Employee"))).isVisible();
    }

    @And("The form is populated with valid {} and {} and {}  values")
    public void theFormIsPopulatedWithValidAndAndValues(String firstName, String lastName, String empId) {

        Locator firstNameField = sideMenu.getCurrentPage().getByPlaceholder("First Name");
        Locator lastNameField = sideMenu.getCurrentPage().getByPlaceholder("Last Name");
        Locator empIdField = sideMenu.getCurrentPage().locator("(//input[@class='oxd-input oxd-input--active'])[2]");

        firstNameField.fill(firstName);
        lastNameField.fill(lastName);
        empIdField.fill(empId.trim());
    }

    @And("The Save button is pressed")
    public void theSaveButtonIsPressed() {
        sideMenu.getCurrentPage().getByRole(AriaRole.BUTTON).getByText("Save").click();
    }

    @Then("user is redirected to Personal Details page")
    public void userIsRedirectedToPersonalDetailsPage() {

        Locator personalDetailsTitle = sideMenu.getCurrentPage().locator("//h6[normalize-space()='Personal Details']");

        personalDetailsTitle.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        PlaywrightAssertions.assertThat(personalDetailsTitle).isVisible();
        sideMenu.closePage();
    }
}
