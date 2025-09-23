package com.serenitydojo.playwright.utils;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Paths;
import java.util.Arrays;

@Log4j2
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
                        .setHeadless(true)
//                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
                );
                page = browser.newPage();
                log.debug("CHROME browser loaded");
                break;
            case "FIREFOX":
                browser = environment.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                page = browser.newPage();
                log.debug("FIREFOX browser loaded");
                break;
            case "EDGE":

                browser = environment.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--no-sandbox","--disable-extensions","--disable-gpu"))
                        .setExecutablePath(Paths.get("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe"))
                );

                page = browser.newPage();
               log.debug("EDGE browser loaded");
                break;
            default:
                log.error(("The WebDriver type <%s> is not defined").formatted(webBrowserType));
        }

    }

    public static BrowserManager getInstance() {
        if (instance == null) {
            instance = new BrowserManager();
        }

        if (instance != null) {
            log.debug(("Returning Browser Instance: %s").formatted(instance.toString()));
            return instance;
        } else {
            log.error("Cannot get the Browser Instance");
            throw new RuntimeException("Cannot get the Browser Instance");
        }
    }

    public static Playwright getEnvironment() {
        BrowserManager.getInstance();
        return BrowserManager.environment;
    }

    public static Page getPage() {
        BrowserManager.getInstance();

        log.debug(("Current page returned successfully: %s").formatted(BrowserManager.page.toString()));
        return BrowserManager.page;
    }

    public static Browser getWebBrowser() {
        return browser;
    }

    public static void setPage(@NotNull Page page) {
        BrowserManager.page = page;
        log.debug(("Current page set successfully: %s").formatted(BrowserManager.page.url()));
    }

    public static void tearDown() {
        BrowserManager.getInstance();
        if (browser != null) {browser.close();}
        if (environment != null) {environment.close();}
        instance = null;
        page = null;
        log.debug(("Browser instance <%s> teared down.").formatted(BrowserManager.webBrowserType));
    }

}
