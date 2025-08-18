package com.serenitydojo.playwright.ui.blocks;

import com.microsoft.playwright.Locator;
import com.serenitydojo.playwright.ui.pages.GenericPage;

public class TopBar extends GenericPage {
    private final Locator topbarTitle;
    private final Locator userDropdown;

    public TopBar() {
        super();
        topbarTitle = this.getCurrentPage().locator("h6.oxd-topbar-header-breadcrumb-module");
        userDropdown = this.getCurrentPage().locator("//span[@class='oxd-userdropdown-tab']");
    }

    public String getTopbarTitleText() {
        System.out.println(topbarTitle);
        return topbarTitle.textContent();
    }

    public Locator getUserDropdown() {
        return userDropdown;
    }
}
