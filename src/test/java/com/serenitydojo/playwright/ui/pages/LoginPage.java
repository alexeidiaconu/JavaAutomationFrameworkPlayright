package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;
import com.serenitydojo.playwright.resources.Constants;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.ConfigReaderManager;

public class LoginPage extends GenericPage {

    public String LOGIN_PAGE_URL = ConfigReaderManager.getProperty("login_page_url");


    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator loginTitle;
    private final Locator invalidCeredntialsAlert;



    public LoginPage() {

        super();
        this.usernameField = super.getCurrentPage().locator("[name=username]");
        this.passwordField = super.getCurrentPage().locator("[name=password]");
        this.loginButton = super.getCurrentPage().getByRole(AriaRole.BUTTON).getByText("Login");
        this.loginTitle = super.getCurrentPage().getByRole(AriaRole.HEADING).getByText("Login");
        this.invalidCeredntialsAlert = super.getCurrentPage().getByRole(AriaRole.ALERT).getByText("Invalid credentials");
    }
    /*Getters*/

    public Locator getLoginTitle() {
        return loginTitle;
    }

    public Locator getUsernameField() {
        return usernameField;
    }

    public Locator getPasswordField() {
        return passwordField;
    }

    public Locator getLoginButton() {
        return loginButton;
    }

    public Locator getInvalidCeredntialsAlert() {
        return invalidCeredntialsAlert;
    }

    /* Actions */


    public void navigateToLoginPageURL() {
        this.getCurrentPage().navigate(this.LOGIN_PAGE_URL , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
    }


    public void login(AdminUser adminUser) {

        this.getCurrentPage().navigate(this.LOGIN_PAGE_URL , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

        this.waitForPageToBeVisible(Constants.WAIT_TIMEOUT);
        this.usernameField.fill(adminUser.USERNAME);
        this.passwordField.fill(adminUser.PASSWORD);
        this.loginButton.click();
    }

}
