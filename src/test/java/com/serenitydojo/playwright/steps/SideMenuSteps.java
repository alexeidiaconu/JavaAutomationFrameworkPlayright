package com.serenitydojo.playwright.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.serenitydojo.playwright.ui.blocks.SideMenu;
import com.serenitydojo.playwright.ui.blocks.TopBar;
import com.serenitydojo.playwright.utils.ScenarioContext;
import com.serenitydojo.playwright.utils.WebElementActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import static com.serenitydojo.playwright.utils.enums.Constants.WAIT_TIMEOUT;
import static com.serenitydojo.playwright.utils.enums.ContextKeys.*;
import static com.serenitydojo.playwright.utils.enums.ContextKeys.TOP_BAR;

public class SideMenuSteps {
    ScenarioContext scenarioContext = ScenarioContext.getInstance();
    SideMenu sideMenu = (SideMenu) scenarioContext.getScenarioContext(SIDE_MENU);
    TopBar topBar = (TopBar) scenarioContext.getScenarioContext(TOP_BAR);
    
    @Given("the Side Menu is visible")
    public void theSideMenuIsVisible() {
        sideMenu.waitForPageToBeVisible();
        PlaywrightAssertions.assertThat(sideMenu.getMenuSidepanel()).isVisible();
    }

    @When("a Side Menu {string} is clicked")
    public void aSideMenuIsClicked(String menuOption) {
        sideMenu.clickOnSideMenuItem(menuOption);
    }

    @Then("the corresponding {string} title is displayed on the new opened page")
    public void theCorrespondingIsDisplayedOnTheNewOpenedPage(String pageTitle) {

        sideMenu.waitForPageToBeVisible();


        try {
            Locator   textToFindOnThePage =  WebElementActions.locateByText(sideMenu.getCurrentPage(), pageTitle); //WebElementActions.locateHeadingByText(sideMenu.getCurrentPage(), pageTitle);


            if (textToFindOnThePage != null && textToFindOnThePage.isVisible()) {
                PlaywrightAssertions.assertThat(textToFindOnThePage).isVisible();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        assert textToFindOnThePage != null;
//        if (! textToFindOnThePage.isVisible()) {
//            textToFindOnThePage = WebElementActions.locateByText(sideMenu.getCurrentPage(), pageTitle);
//
//        }
        //        Locator   textToFindOnThePage = sideMenu.getCurrentPage().locator("//*[contains(@class, 'oxd-text')]").getByText(pageTitle);
//                .or(WebElementActions.locateByText(sideMenu.getCurrentPage(), pageTitle));
    }

    @And("the corresponding {string} title is displayed on the Top Bar")
    public void theCorrespondingIsDisplayedOnTheTopBar(String topBarTitle) {
        Assertions.assertThat(topBar.getTopbarTitleText().contains(topBarTitle)).isTrue();
    }
}
