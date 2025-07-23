package com.serenitydojo.playwright.ui;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.ui.pages.GenericPage;

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
}
