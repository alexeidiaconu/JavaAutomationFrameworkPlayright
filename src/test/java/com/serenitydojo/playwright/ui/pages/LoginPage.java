package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;
import com.serenitydojo.playwright.utils.AdminUser;

public class LoginPage extends GenericPage {


    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;

    public LoginPage() {

        super();
        this.usernameField = super.getCurrentPage().locator("[name=username]");
        this.passwordField = super.getCurrentPage().locator("[name=password]");
        this.loginButton = super.getCurrentPage().locator("button:has-text(' Login ')");
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

    public void login(AdminUser adminUser) {

        this.getCurrentPage().navigate("http://172.23.176.163:8200/web/index.php/auth/login" , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

        this.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);
        this.usernameField.fill(adminUser.USERNAME);
        this.passwordField.fill(adminUser.PASSWORD);
        this.loginButton.click();
    }

}
