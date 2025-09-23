package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.LoadState;
import com.serenitydojo.playwright.utils.enums.Constants;
import com.serenitydojo.playwright.utils.BrowserManager;
import lombok.extern.log4j.Log4j2;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import static com.serenitydojo.playwright.utils.enums.Constants.WAIT_TIMEOUT;

@Log4j2

public abstract class GenericPage {
    private Page currentPage;
   // public WebElementActions webElementActions;

    public GenericPage() {
        //BrowserManager.getInstance();
        currentPage = BrowserManager.getPage();
        log.debug(("GenericPage(): New generic page instantiated: %s").formatted(currentPage.toString()));
    }

    public Page getCurrentPage() {

        currentPage = BrowserManager.getPage();
        log.debug(("getCurrentPage(): Generic page is returned: %s").formatted(currentPage.toString()));
        return this.currentPage;
    }

    public void closePage() {

        log.debug(("closePage(): Generic page is closing: %s").formatted(currentPage.toString()));
        BrowserManager.tearDown();
    }

    public void setCurrentPage(Page currentPage) {

        log.debug(("setCurrentPage(Page currentPage): Current Page is set: %s").formatted(currentPage.toString()));
        this.currentPage = currentPage;
    }

    public void waitForPageToBeVisible(Constants timeoutMillis) {

       try {
           BrowserManager.getInstance();
           this.setCurrentPage(BrowserManager.getPage());

           Page currentPage = this.getCurrentPage();
           currentPage.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(WAIT_TIMEOUT.getValue()));
           log.info(("waitForPageToBeVisible(Constants timeoutMillis): The Page is visible: %s").formatted(currentPage.url()));
       } catch (TimeoutError e) {
          log.error(("waitForPageToBeVisible(Constants timeoutMillis): The Current Page is NOT visible: . Error: %s").formatted(e.getMessage()));
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