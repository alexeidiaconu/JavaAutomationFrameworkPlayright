package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.utils.BrowserManager;
import com.serenitydojo.playwright.utils.WebElementActions;

public abstract class GenericPage {
    private Page currentPage;
    public WebElementActions webElementActions;

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

    /*Actions*/

//    public void populateField(Locator fieldToPopulate, String dataToBePopulated) {
//        fieldToPopulate.fill(dataToBePopulated);
//
//    }
}

//refresh
//isonpage
