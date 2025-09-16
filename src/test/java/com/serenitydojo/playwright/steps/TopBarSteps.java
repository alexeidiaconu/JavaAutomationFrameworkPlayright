package com.serenitydojo.playwright.steps;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import com.serenitydojo.playwright.utils.enums.Constants;
import com.serenitydojo.playwright.ui.blocks.TopBar;
import com.serenitydojo.playwright.utils.ScenarioContext;
import com.serenitydojo.playwright.utils.WebElementActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import static com.serenitydojo.playwright.utils.enums.ContextKeys.*;


public class TopBarSteps {
    ScenarioContext scenarioContext = ScenarioContext.getInstance();
//    LoginPage loginPage = (LoginPage) scenarioContext.getScenarioContext(LOGIN_PAGE);
//    AdminUser adminUser = (AdminUser) scenarioContext.getScenarioContext(ADMIN_USER);
//    SideMenu sideMenu = (SideMenu) scenarioContext.getScenarioContext(SIDE_MENU);
    TopBar topBar = (TopBar) scenarioContext.getScenarioContext(TOP_BAR);


    @Given("the top bar is visible")
    public void theTopBarIsVisible() {

        topBar = new TopBar();
        Page currentPage = topBar.getCurrentPage();
        WebElementActions.waitForWebElementToBeVisible(topBar.getProfilePicture(), Constants.WAIT_TIMEOUT);
        currentPage.waitForLoadState(LoadState.NETWORKIDLE);
        String currentUrl = currentPage.url();
        Assertions.assertThat(currentUrl.contains("index.php")).isTrue();
        PlaywrightAssertions.assertThat(topBar.getProfilePicture()).isVisible();
        Assertions.assertThat(topBar.getTopbarTitleText().contains("Dashboard")).isTrue();

    }

    @When("the top bar user dropdown is clicked")
    public void theTopBarUserDropdownIsClicked() {

        WebElementActions.clickOnMenuItem(topBar.getUserDropdown());
        WebElementActions.waitForWebElementToBeVisible(topBar.getUserDropdownTab(),Constants.WAIT_TIMEOUT);
        PlaywrightAssertions.assertThat(topBar.getUserDropdownTab()).isVisible();
    }

    @And("the {string} option is selected from the dropdown menu")
    public void anIsSelectedFromTheDropdownMenu(String menuOption) {

        WebElementActions.clickOnMenuItem(topBar.locateMenuItemByText(menuOption));

    }

    @Then("the corresponding {string} is displayed on the opened page")
    public void theCorrespondingTitleIsDisplayed(String pageTitle) {

        PlaywrightAssertions.assertThat(
                WebElementActions.locateHeadingByText(topBar.getCurrentPage(), pageTitle)
        ).isVisible();

    }
}

