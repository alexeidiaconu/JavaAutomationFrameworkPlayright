package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.LoadState;
import com.serenitydojo.playwright.utils.enums.Constants;
import com.serenitydojo.playwright.utils.BrowserManager;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import static com.serenitydojo.playwright.utils.enums.Constants.WAIT_TIMEOUT;

public abstract class GenericPage {
    private Page currentPage;
   // public WebElementActions webElementActions;

    public GenericPage() {
        BrowserManager.getInstance();
        currentPage = BrowserManager.getPage();
    }

    public Page getCurrentPage() {
        return this.currentPage;
    }

    public void closePage() {
        BrowserManager.tearDown();
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public void waitForPageToBeVisible(Constants timeoutMillis) {

       try {
           currentPage.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(WAIT_TIMEOUT.getValue()));
       } catch (TimeoutError e) {
           System.err.println("Element was not visible within " + timeoutMillis + " ms. Locator: " + currentPage);
       }
    }

    /*Actions*/

//    public void populateField(Locator fieldToPopulate, String dataToBePopulated) {
//        fieldToPopulate.fill(dataToBePopulated);
//
//    }
}

//refresh
//isonpage
//wait for page to be visible