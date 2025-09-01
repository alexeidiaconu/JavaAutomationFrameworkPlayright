package com.serenitydojo.playwright.steps;

import com.serenitydojo.playwright.resources.Constants;
import com.serenitydojo.playwright.ui.blocks.SideMenu;
import com.serenitydojo.playwright.ui.blocks.TopBar;
import com.serenitydojo.playwright.ui.pages.LoginPage;
import com.serenitydojo.playwright.ui.pages.PimPage;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.assertj.core.api.Assertions;
import static com.serenitydojo.playwright.utils.ContextKeys.*;
import static com.serenitydojo.playwright.resources.Constants.WAIT_TIMEOUT;


public class GenericSteps {
    ScenarioContext scenarioContext = ScenarioContext.getInstance();
    LoginPage loginPage = (LoginPage) scenarioContext.getScenarioContext(LOGIN_PAGE);
    AdminUser adminUser = (AdminUser) scenarioContext.getScenarioContext(ADMIN_USER);
    SideMenu sideMenu = (SideMenu) scenarioContext.getScenarioContext(SIDE_MENU);
    TopBar topBar = (TopBar) scenarioContext.getScenarioContext(TOP_BAR);

    @Given("Admin User is logged in with valid credentials")
    public void adminUserIsLoggedInWithValidCredentials() {
      adminUser.loadCredentials();
      loginPage.login(adminUser);
    }

    @And("The {} menu item is clicked")
    public void theMenuItemIsClicked(String menuItem) {

        sideMenu.clickOnSideMenuItem(menuItem);
    }

    @And("the {} page is displayed")
    public void thePageIsDisplayed(String pageTitle) {
        topBar.waitForPageToBeVisible(WAIT_TIMEOUT);
        Assertions.assertThat(topBar.getTopbarTitleText().contains(pageTitle)).isTrue();

    }
}
