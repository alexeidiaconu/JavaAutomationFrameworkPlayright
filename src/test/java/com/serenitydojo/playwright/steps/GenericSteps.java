package com.serenitydojo.playwright.steps;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.serenitydojo.playwright.ui.blocks.SideMenu;
import com.serenitydojo.playwright.ui.pages.LoginPage;
import com.serenitydojo.playwright.utils.AdminUser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class GenericSteps {
    LoginPage loginPage = new LoginPage();;
    AdminUser adminUser = new AdminUser();
    SideMenu sideMenu = new SideMenu();



    @Given("Admin User is logged in with valid credentials")
    public void adminUserIsLoggedInWithValidCredentials() {
      adminUser.loadCredentials();
      loginPage.login(adminUser);
    }

    @And("The {} menu item is clicked")
    public void theMenuItemIsClicked(String menuItem) {

        String trimmedMenuItem = menuItem.trim();

        switch (trimmedMenuItem) {

            case "Admin":
                sideMenu.getMenuItemAdmin().click();
                break;

            case "PIM":
                sideMenu.getMenuItemPIM().click();
                break;

        }

    }

    @And("the {} page is displayed")
    public void thePageIsDisplayed(String pageTitle) {
        PlaywrightAssertions.assertThat(sideMenu.getCurrentPage().locator(".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module")).hasText(pageTitle);
    }
}
