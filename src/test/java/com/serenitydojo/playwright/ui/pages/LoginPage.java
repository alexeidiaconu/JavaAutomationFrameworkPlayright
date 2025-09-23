package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;
import com.serenitydojo.playwright.utils.BrowserManager;
import com.serenitydojo.playwright.utils.WebElementActions;
import com.serenitydojo.playwright.utils.enums.Constants;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.ConfigReaderManager;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class LoginPage extends GenericPage {

    public String LOGIN_PAGE_URL = ConfigReaderManager.getProperty("login_page_url");


    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator loginTitle;
    private final Locator invalidCeredntialsAlert;


    public LoginPage() {

        super();

        Page superPage = super.getCurrentPage();

        this.usernameField = superPage.locator("[name=username]");
        this.passwordField = superPage.locator("[name=password]");
        this.loginButton = superPage.getByRole(AriaRole.BUTTON).getByText("Login");
        this.loginTitle = superPage.getByRole(AriaRole.HEADING).getByText("Login");
        this.invalidCeredntialsAlert = superPage.getByRole(AriaRole.ALERT).getByText("Invalid credentials");

        log.debug(("LoginPage() instantiated: %s").formatted(this.toString()));
    }
    /*Getters*/

    public Locator getLoginTitle() {

        log.debug(("Executing getLoginTitle(): %s").formatted(loginTitle.toString()));
        return loginTitle;
    }

    public Locator getUsernameField() {

        log.debug(("Executing getUsernameField(): %s").formatted(usernameField.toString()));
        return usernameField;
    }

    public Locator getPasswordField() {

        log.debug(("Executing getPasswordField(): %s").formatted(passwordField.toString()));
        return passwordField;
    }

    public Locator getLoginButton() {

        log.debug(("Executing getLoginButton(): %s").formatted(loginButton.toString()));
        return loginButton;
    }

    public Locator getInvalidCeredntialsAlert() {

        log.debug(("Executing getInvalidCeredntialsAlert(): %s").formatted(invalidCeredntialsAlert.toString()));
        return invalidCeredntialsAlert;
    }

    /* Actions */


    public void navigateToLoginPageURL() {

        try {
            this.getCurrentPage().navigate(this.LOGIN_PAGE_URL , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
            log.info(("navigateToLoginPageURL() executed successfully. Page title : %s").formatted(this.getCurrentPage().title()));
        } catch (Exception e) {
            log.error(("Unable to access Login Page. Reason: %s").formatted(e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    public void login(AdminUser adminUser) {

        this.getCurrentPage().navigate(this.LOGIN_PAGE_URL , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

        this.waitForPageToBeVisible(Constants.WAIT_TIMEOUT);
        this.usernameField.fill(adminUser.USERNAME);
        this.passwordField.fill(adminUser.PASSWORD);
        this.loginButton.click();

        log.info(("login(AdminUser adminUser): Admin User <%s> logged in successfully").formatted(adminUser.USERNAME));
        this.getCurrentPage();
    }

}
