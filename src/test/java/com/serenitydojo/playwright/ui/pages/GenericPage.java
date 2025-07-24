package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.utils.BrowserManager;

public abstract class GenericPage {
    private Page currentPage;

    public GenericPage() {
        BrowserManager browser = BrowserManager.getInstance();
        currentPage = BrowserManager.getPage();
    }

    public Page getCurrentPage() {
        return this.currentPage;
    }
}
