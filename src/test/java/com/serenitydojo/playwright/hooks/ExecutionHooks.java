package com.serenitydojo.playwright.hooks;

import com.serenitydojo.playwright.ui.pages.LoginPage;
import com.serenitydojo.playwright.utils.AdminUser;
import io.cucumber.java.BeforeAll;
import org.junit.jupiter.api.AfterEach;

public class ExecutionHooks {

    @BeforeAll
    public void beforeAll() {
        AdminUser adminUser = new AdminUser();
        adminUser.loadCredentials();
        LoginPage loginPage = new LoginPage();
    }




}
