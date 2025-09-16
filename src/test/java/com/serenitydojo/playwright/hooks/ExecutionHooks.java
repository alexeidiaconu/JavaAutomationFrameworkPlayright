package com.serenitydojo.playwright.hooks;

import com.serenitydojo.playwright.ui.blocks.SideMenu;
import com.serenitydojo.playwright.ui.blocks.TopBar;
import com.serenitydojo.playwright.ui.pages.LoginPage;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.ConfigReaderManager;
import com.serenitydojo.playwright.utils.ScenarioContext;
import com.serenitydojo.playwright.utils.enums.ApiUrls;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.serenitydojo.playwright.utils.ApiUtils.getAccessAndRefreshTokens;
import static com.serenitydojo.playwright.utils.enums.ContextKeys.*;

public class ExecutionHooks {

    @Before
    public static void before() {
        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        scenarioContext.setScenarioContext(ADMIN_USER,new AdminUser());
        scenarioContext.setScenarioContext(LOGIN_PAGE, new LoginPage());
        scenarioContext.setScenarioContext(SIDE_MENU, new SideMenu());
        scenarioContext.setScenarioContext(TOP_BAR, new TopBar());
//       getAccessAndRefreshTokens(ConfigReaderManager.getProperty("refresh_token"), ApiUrls.GET_ACCESS_AND_REFRESH_TOKEN);
        scenarioContext.setScenarioContext(ACCESS_TOKEN, ConfigReaderManager.getProperty("access_token"));
    }

    @After
    public void closePage () {
        LoginPage loginPage = (LoginPage) ScenarioContext.getInstance().getScenarioContext(LOGIN_PAGE);
        loginPage.closePage();
    }



}

//reflection
