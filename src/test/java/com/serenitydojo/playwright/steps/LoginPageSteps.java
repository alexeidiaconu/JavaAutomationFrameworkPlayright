package com.serenitydojo.playwright.steps;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;
import com.serenitydojo.playwright.utils.AdminUser;
import io.cucumber.java.en.*;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.serenitydojo.playwright.ui.pages.LoginPage;
import com.serenitydojo.playwright.ui.blocks.SideMenu;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.*;

import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;




public class LoginPageSteps {
    LoginPage loginPage = new LoginPage();

@BeforeEach
public void initPage () {
    loginPage = new LoginPage();
}

@AfterEach
    public void closePage () {
        loginPage.closePage();
    }

    @Given("The Login Page is accessed$")
    public void theLoginPageIsAccessed() {
        loginPage.getCurrentPage().navigate("http://172.23.176.163:8200/web/index.php/auth/login" , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        PlaywrightAssertions.assertThat(loginPage.getCurrentPage().getByRole(AriaRole.HEADING)).isVisible();

    }

    @And("valid credentials are populated in the username and password fields")
    public void validCredentialsArePopulatedInTheUsernameAndPasswordFields() {
        loginPage.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);
        loginPage.getUsernameField().fill(AdminUser.USERNAME);
        loginPage.getPasswordField().fill(AdminUser.PASSWORD);
    }

    @When("the Submit button is clicked")
    public void theSubmitButtonIsClicked() {
        loginPage.getLoginButton().click();
    }


    @Then("the application Dasboard page is displayed")
    public void theApplicationDasboardPageIsDisplayed() {
        loginPage.getCurrentPage().waitForSelector("//h6[normalize-space()='Dashboard']", new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.VISIBLE)
//                .setTimeout(3000)
        );
        Assertions.assertTrue(loginPage.getCurrentPage().locator("//h6[normalize-space()='Dashboard']").isVisible());
        loginPage.closePage();

    }

    @And("Invalid credentials are populated in the username and password fields:")
    public void invalidCredentialsArePopulatedInTheUsernameAndPasswordFields(Map<String,String> userCredentials) {

        loginPage.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);
        loginPage.getUsernameField().fill(userCredentials.get("userName"));
        loginPage.getPasswordField().fill(userCredentials.get("password"));
    }

    @And("An Alert message {string} is displayed on the Login page")
    public void anAlertMessageIsDisplayedOnTheLoginPage(String errorMessage) {
        PlaywrightAssertions.assertThat(loginPage.getCurrentPage().getByRole(AriaRole.ALERT)).isVisible();
        PlaywrightAssertions.assertThat(loginPage.getCurrentPage().getByRole(AriaRole.ALERT)).hasText(errorMessage);
        loginPage.closePage();
    }

    @Then("The Login Page is displayed")
    public void theLoginPageIsDisplayed() {
        loginPage.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);
        PlaywrightAssertions.assertThat(loginPage.getCurrentPage().getByRole(AriaRole.HEADING)).isVisible();

    }
}





//    LoginPage loginPage = new LoginPage();
//@AfterEach
//public void closePage() {
//    loginPage.closePage();
//}
//
//    @Test
//    @DisplayName("User is loging in with valid credentials")
//    void loginWithValidCredentials () {
//
//        loginPage.login();
//
////         loginPage.getCurrentPage().navigate("http://172.23.176.163:8200/web/index.php/auth/login" , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
////
////        System.out.println("Browser page open");
////
////        String title = loginPage.getCurrentPage().title();
////        Assertions.assertTrue(title.contains("OrangeHRM"));
////        //PlaywrightAssertions.assertThat(page.getByTitle("OrangeHRM")).containsText("OrangeHRM");
////
////        loginPage.getUsernameField().fill(AdminUser.USERNAME);
////        assertThat(loginPage.getCurrentPage().getByPlaceholder("Username")).hasValue(AdminUser.USERNAME);
////
////
////        loginPage.getPasswordField().fill(AdminUser.PASSWORD);
////        assertThat(loginPage.getCurrentPage().getByPlaceholder("Password")).hasValue(AdminUser.PASSWORD);
////
////        loginPage.getLoginButton().click();
////
//         loginPage.getCurrentPage().waitForURL("http://172.23.176.163:8200/web/index.php/dashboard/index");
////
//////
//////
////        loginPage.getCurrentPage().waitForSelector("//h6[normalize-space()='Dashboard']", new Page.WaitForSelectorOptions()
////                .setState(WaitForSelectorState.VISIBLE)
//////                .setTimeout(3000)
////                );
////       Assertions.assertTrue(loginPage.getCurrentPage().locator("//h6[normalize-space()='Dashboard']").isVisible());
//
//        loginPage.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);
//
////        loginPage.closePage();
//
//
//
//
//
//    }
//
//    @Test
//    @DisplayName("User Loggs in and clicks on every menu item")
//    void loginAndClickOnAllMenuItems() {
//
//       // LoginPage loginPage = new LoginPage();
//        SideMenu sideMenu = new SideMenu();
//
//
//        loginPage.login();
//        //Page currentPage = loginPage.getCurrentPage();
//
//
//       loginPage.getCurrentPage().waitForURL("http://172.23.176.163:8200/web/index.php/dashboard/index");
//
//        loginPage.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);
//        loginPage.getCurrentPage().waitForSelector(".oxd-sidepanel-body",
//                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE)
//                );
//       // Assertions.assertTrue(currentPage.locator("[class=oxd-main-menu --fixed]").isVisible());
//
//        sideMenu.getMenuItemAdmin().click();
//        sideMenu.getMenuItemPIM().click();
//        sideMenu.getMenuItemLeave().click();
//        sideMenu.getMenuItemTime().click();
//        sideMenu.getMenuItemRecruitment().click();
//        sideMenu.getMenuItemMyInfo().click();
//        sideMenu.getMenuItemPerformance().click();
//        sideMenu.getMenuItemDashboard().click();
//        sideMenu.getMenuItemDirectory().click();
//        sideMenu.getMenuItemMaintenance().click();
//        sideMenu.getMenuItemClaim().click();
//        sideMenu.getMenuItemBuzz().click();
//
////        loginPage.closePage();
//
//    }

