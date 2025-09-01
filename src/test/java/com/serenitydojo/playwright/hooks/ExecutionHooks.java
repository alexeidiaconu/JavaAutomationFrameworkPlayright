package com.serenitydojo.playwright.hooks;

import com.serenitydojo.playwright.ui.blocks.SideMenu;
import com.serenitydojo.playwright.ui.blocks.TopBar;
import com.serenitydojo.playwright.ui.pages.LoginPage;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.serenitydojo.playwright.utils.ContextKeys.*;

public class ExecutionHooks {

    @Before
    public static void before() {
        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        scenarioContext.setScenarioContext(ADMIN_USER,new AdminUser());
        scenarioContext.setScenarioContext(LOGIN_PAGE, new LoginPage());
        scenarioContext.setScenarioContext(SIDE_MENU, new SideMenu());
        scenarioContext.setScenarioContext(TOP_BAR, new TopBar());
    }

    @After
    public void closePage () {
        LoginPage loginPage = (LoginPage) ScenarioContext.getInstance().getScenarioContext(LOGIN_PAGE);
        loginPage.closePage();
    }



}

//reflection
