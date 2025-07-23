package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import com.serenitydojo.playwright.utils.BrowserManager;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        BrowserManager.getPage().navigate("http://172.23.176.163:8200/web/index.php/auth/login" , new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

//        Playwright environment = Playwright.create();
//        Browser browser = environment.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//        Page page = browser.newPage();
//        page.navigate("http://172.23.176.163:8200/web/index.php/auth/login",new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        BrowserManager.tearDown();


    }


}
