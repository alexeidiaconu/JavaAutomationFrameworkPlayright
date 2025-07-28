package com.serenitydojo.playwright.utils;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;
import java.util.Arrays;

public class BrowserManager {
    private static String webBrowserType = "Chrome";
    private static BrowserManager instance;
    private static Playwright environment;
    private static Browser browser;
    private static Page page;

//public Options getOptions() {
//    return new Options().setLaunchOptions(new BrowserType.LaunchOptions()
//            .setHeadless(false)
//            );
//}

    public BrowserManager() {
        environment = Playwright.create();

        switch (webBrowserType.toUpperCase()){
            case "CHROME":
                browser = environment.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false)
//                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
                );
                page = browser.newPage();
                break;
            case "FIREFOX":
                browser = environment.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                page = browser.newPage();
                break;
            case "EDGE":

                browser = environment.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--no-sandbox","--disable-extensions","--disable-gpu"))
                        .setExecutablePath(Paths.get("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe"))
                );

                page = browser.newPage();
                break;
            default:
                System.out.println("The WebDriver type " + webBrowserType + " is not defined");
        }

    }

    public static BrowserManager getInstance() {
        if (instance == null) {
            instance = new BrowserManager();
        }
        return  instance;
    }

    public static Page getPage() {
        BrowserManager browserManager = BrowserManager.getInstance();
        return BrowserManager.page;
    }

    public static Browser getWebBrowser() {
        return browser;
    }

    public static void setPage(Page page) {
        BrowserManager.page = page;
    }

    public static void tearDown() {
        BrowserManager browserManager = BrowserManager.getInstance();
        if (browser != null) {browser.close();}
        if (environment != null) {environment.close();}
        instance = null;
        page = null;
    }

}
