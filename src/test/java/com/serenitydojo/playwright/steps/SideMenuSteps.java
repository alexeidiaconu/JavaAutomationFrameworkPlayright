package com.serenitydojo.playwright.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.serenitydojo.playwright.ui.blocks.SideMenu;
import com.serenitydojo.playwright.ui.blocks.TopBar;
import com.serenitydojo.playwright.utils.WebElementActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class SideMenuSteps {
    SideMenu sideMenu = new SideMenu();
    TopBar topBar = new TopBar();
    
    @Given("the Side Menu is visible")
    public void theSideMenuIsVisible() {
        sideMenu.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);
        PlaywrightAssertions.assertThat(sideMenu.getCurrentPage().getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Sidepanel") )).isVisible();
    }

    @When("a Side Menu {string} is clicked")
    public void aSideMenuIsClicked(String menuOption) {
        sideMenu.clickOnSideMenuItem(menuOption);
    }

    @Then("the corresponding {string} title is displayed on the new opened page")
    public void theCorrespondingIsDisplayedOnTheNewOpenedPage(String pageTitle) {

        sideMenu.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);

        Locator   textToFindOnThePage = WebElementActions.locateHeadingByText(sideMenu.getCurrentPage(), pageTitle);

        assert textToFindOnThePage != null;
        if (textToFindOnThePage.isVisible()) {
            PlaywrightAssertions.assertThat(textToFindOnThePage).isVisible();
        } else {
            textToFindOnThePage = WebElementActions.locateByText(sideMenu.getCurrentPage(), pageTitle);
            PlaywrightAssertions.assertThat(textToFindOnThePage).isVisible();
        }




    }

    @And("the corresponding {string} title is displayed on the Top Bar")
    public void theCorrespondingIsDisplayedOnTheTopBar(String topBarTitle) {
        Assertions.assertThat(topBar.getTopbarTitleText().contains(topBarTitle));
    }
}
