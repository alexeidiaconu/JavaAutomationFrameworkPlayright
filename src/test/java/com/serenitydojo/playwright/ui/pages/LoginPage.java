package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.ConfigReaderManager;

public class LoginPage extends GenericPage {

    public String LOGIN_PAGE_URL = ConfigReaderManager.getProperty("login_page_url");


    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator loginTitle;


    public LoginPage() {

        super();
        this.usernameField = super.getCurrentPage().locator("[name=username]");
        this.passwordField = super.getCurrentPage().locator("[name=password]");
        this.loginButton = super.getCurrentPage().locator("button:has-text(' Login ')");
        this.loginTitle = super.getCurrentPage().getByRole(AriaRole.HEADING).getByText("Login");
    }
//locator("//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']");
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

    /* Actions */


//    public void populateField(Locator fieldToPopulate, String dataToBePopulated) {
//        fieldToPopulate.fill(dataToBePopulated);
//
//    }

//    public void clickOnButton(Locator buttonToClick) {
//        buttonToClick.click();
//    }

    public void navigateToLoginPageURL() {
        this.getCurrentPage().navigate(this.LOGIN_PAGE_URL , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
    }


    public void login(AdminUser adminUser) {

        this.getCurrentPage().navigate(this.LOGIN_PAGE_URL , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

        this.getCurrentPage().waitForLoadState(LoadState.NETWORKIDLE);
        this.usernameField.fill(adminUser.USERNAME);
        this.passwordField.fill(adminUser.PASSWORD);
        this.loginButton.click();
    }

}
