package com.serenitydojo.playwright.steps;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.ElementState;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.serenitydojo.playwright.ui.blocks.TopBar;
import com.serenitydojo.playwright.ui.pages.LoginPage;
import com.serenitydojo.playwright.utils.AdminUser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;




public class TopBarSteps {
    TopBar topBar;
    LoginPage loginPage = new LoginPage();
    AdminUser adminUser = new AdminUser();



    @Given("The top bar is displayed")
    public void theTopBarIsDisplayed() {

        topBar = new TopBar();
        adminUser.loadCredentials();
        loginPage.login(adminUser);
        Page currentPage = loginPage.getCurrentPage();
        currentPage.waitForSelector("//img[@alt='profile picture']").isVisible();
        //currentPage.waitForURL("http://172.23.176.163:8200/web/index.php/dashboard/index");
        currentPage.waitForLoadState(LoadState.NETWORKIDLE);
        //System.out.println("Title: " + topBar.getTopbarTitleText());
        String currentUrl = currentPage.url();
       // System.out.println("The URL value is: " + currentUrl);
        Assertions.assertThat(currentUrl.contains("index.php"));
       // System.out.println("The page title is: " + topBar.getTopbarTitleText());
        Assertions.assertThat(topBar.getTopbarTitleText().contains("Dashboard"));

    }

    @When("the top bar user dropdown is clicked")
    public void theTopBarUserDropdownIsClicked() {
        topBar.getUserDropdown().click();
        Assertions.assertThat(topBar.getUserDropdown().locator("span.oxd-userdropdown-tab").isVisible());
    }

    @And("{string} option is selected from the dropdown menu")
    public void anIsSelectedFromTheDropdownMenu(String option) {
        topBar.getUserDropdown().click();
        topBar.getUserDropdown().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator menuItem = topBar.getCurrentPage().getByRole(AriaRole.MENUITEM).getByText(option);

        menuItem.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        // Print the text and click it
        //System.out.println(menuItem.textContent());
        menuItem.click();

        topBar.getUserDropdown().getByRole(AriaRole.MENUITEM).getByText(option);
    }

    @Then("an {string} is taking place")
    public void anIsTakingPlace(String action) {

        String trimmedAction = action.trim();

        switch (trimmedAction) {
            case "theAboutPageIsDisplayed":
                theAboutPageIsDisplayed();
                break;
            case "theSupportPageIsDisplayed":
                theSupportPageIsDisplayed();
                break;
            case "thePasswordChangePageIsDisplayed":
                thePasswordChangePageIsDisplayed();
                break;
        }
        loginPage.closePage();
    }

    private void thePasswordChangePageIsDisplayed() {
        System.out.println("thePasswordChangePageIsDisplayed() is executed");

    }

    private void theSupportPageIsDisplayed() {

        System.out.println("theSupportPageIsDisplayed() is executed");
    }

    private void theAboutPageIsDisplayed() {
        topBar.getCurrentPage().waitForSelector("//div[@role='document']").waitForElementState(ElementState.VISIBLE);

        PlaywrightAssertions.assertThat(
                topBar.getCurrentPage().getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("About"))
        ).isVisible();
        System.out.println("theAboutPageIsDisplayed() is executed");
    }
}


