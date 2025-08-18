package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;
import com.serenitydojo.playwright.ui.blocks.TopBar;
import com.serenitydojo.playwright.ui.pages.LoginPage;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.BrowserManager;
import org.assertj.core.api.Assertions;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        //BrowserManager.getPage().navigate("http://172.23.176.163:8200/web/index.php/auth/login" , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

//        Playwright environment = Playwright.create();
//        Browser browser = environment.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//        Page page = browser.newPage();
//        page.navigate("http://172.23.176.163:8200/web/index.php/auth/login",new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

        //BrowserManager.tearDown();

//        AdminUser adminUser = new AdminUser();
//        adminUser.loadCredentials();
//        LoginPage loginPage = new LoginPage();
//        loginPage.login(adminUser);
//        TopBar topBar = new TopBar();
//        Page currentPage = loginPage.getCurrentPage();
//        currentPage.waitForSelector("//img[@alt='profile picture']").isVisible();
//        //currentPage.waitForURL("http://172.23.176.163:8200/web/index.php/dashboard/index");
//        currentPage.waitForLoadState(LoadState.NETWORKIDLE);
//        String title = topBar.getTopbarTitleText();
//        System.out.println("Title: " + title);
//        String currentUrl = currentPage.url();
//        System.out.println("The value is: " + Assertions.assertThat(currentUrl.contains("index.php")));



    }


}
