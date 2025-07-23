package com.serenitydojo.playwright.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.WaitUntilState;
import com.serenitydojo.playwright.ui.LoginPage;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.BrowserManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class LoginPageSteps {

    LoginPage loginPage = new LoginPage();

    @BeforeAll
    static void browserSetup() {

        //LoginPage loginPage = new LoginPage();
    }

    @Test
    void loginWithValidCredentials () {

         loginPage.getCurrentPage().navigate("http://172.23.176.163:8200/web/index.php/auth/login" , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

        System.out.println("Browser page open");

        String title = loginPage.getCurrentPage().title();
        Assertions.assertTrue(title.contains("OrangeHRM"));
        //PlaywrightAssertions.assertThat(page.getByTitle("OrangeHRM")).containsText("OrangeHRM");

        loginPage.getUsernameField().fill(AdminUser.USERNAME);
        assertThat(loginPage.getCurrentPage().getByPlaceholder("Username")).hasValue(AdminUser.USERNAME);


        loginPage.getPasswordField().fill(AdminUser.PASSWORD);
        assertThat(loginPage.getCurrentPage().getByPlaceholder("Password")).hasValue(AdminUser.PASSWORD);

        loginPage.getLoginButton().click();

         loginPage.getCurrentPage().waitForURL("http://172.23.176.163:8200/web/index.php/dashboard/index");
//
//
//
        loginPage.getCurrentPage().waitForSelector("//h6[normalize-space()='Dashboard']", new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE)
//                .setTimeout(3000)
                );
       Assertions.assertTrue(loginPage.getCurrentPage().locator("//h6[normalize-space()='Dashboard']").isVisible());

        loginPage.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);

        BrowserManager.tearDown();

    }


}
