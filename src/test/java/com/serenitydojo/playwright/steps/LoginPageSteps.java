package com.serenitydojo.playwright.steps;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.serenitydojo.playwright.utils.enums.Constants;
import com.serenitydojo.playwright.ui.pages.LoginPage;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.ScenarioContext;
import com.serenitydojo.playwright.utils.WebElementActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;

import static com.serenitydojo.playwright.utils.enums.ContextKeys.ADMIN_USER;
import static com.serenitydojo.playwright.utils.enums.ContextKeys.LOGIN_PAGE;


public class LoginPageSteps {
    ScenarioContext scenarioContext = ScenarioContext.getInstance();
    LoginPage loginPage = (LoginPage) scenarioContext.getScenarioContext(LOGIN_PAGE);
    AdminUser adminUser = (AdminUser) scenarioContext.getScenarioContext(ADMIN_USER);



    @Given("The Login Page is accessed$")
    public void theLoginPageIsAccessed() {
        loginPage.navigateToLoginPageURL();
        PlaywrightAssertions.assertThat(loginPage.getLoginTitle()).isVisible();

    }

    @And("valid credentials are populated in the username and password fields")
    public void validCredentialsArePopulatedInTheUsernameAndPasswordFields() {
        adminUser.loadCredentials();
        loginPage.waitForPageToBeVisible(Constants.WAIT_TIMEOUT);
        WebElementActions.populateField(loginPage.getUsernameField(), adminUser.USERNAME);
        WebElementActions.populateField(loginPage.getPasswordField(), adminUser.PASSWORD);
    }

    @When("the Submit button is clicked")
    public void theSubmitButtonIsClicked() {

    WebElementActions.clickOnButton(loginPage.getLoginButton());
    }


    @And("Invalid credentials are populated in the username and password fields:")
    public void invalidCredentialsArePopulatedInTheUsernameAndPasswordFields(Map<String,String> userCredentials) {

        loginPage.waitForPageToBeVisible(Constants.WAIT_TIMEOUT);
        WebElementActions.populateField(loginPage.getUsernameField(),userCredentials.get("userName"));
        WebElementActions.populateField(loginPage.getPasswordField(),userCredentials.get("password"));
    }

    @And("An Alert message {string} is displayed on the Login page")
    public void anAlertMessageIsDisplayedOnTheLoginPage(String errorMessage) {
        PlaywrightAssertions.assertThat(loginPage.getInvalidCeredntialsAlert()).isVisible();
        PlaywrightAssertions.assertThat(loginPage.getInvalidCeredntialsAlert()).hasText(errorMessage);
        loginPage.closePage();
    }

    @Then("The Login Page is displayed")
    public void theLoginPageIsDisplayed() {
        loginPage.waitForPageToBeVisible(Constants.WAIT_TIMEOUT);
        PlaywrightAssertions.assertThat(loginPage.getLoginTitle()).isVisible();
    }
}

