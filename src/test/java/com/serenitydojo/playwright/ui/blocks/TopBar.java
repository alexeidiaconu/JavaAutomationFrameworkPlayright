package com.serenitydojo.playwright.ui.blocks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.serenitydojo.playwright.ui.pages.GenericPage;

public class TopBar extends GenericPage {
    private final Locator topbarTitle;
    private final Locator userDropdown;
    private final Locator profilePicture;
    private final Locator userDropdownTab;


    public TopBar() {
        super();
        topbarTitle = this.getCurrentPage().locator("h6.oxd-topbar-header-breadcrumb-module");
        userDropdown = this.getCurrentPage().locator("//span[@class='oxd-userdropdown-tab']");
        profilePicture = this.getCurrentPage().getByRole(AriaRole.BANNER).getByRole(AriaRole.IMG, new Locator.GetByRoleOptions().setName("profile picture"));
        userDropdownTab = this.getCurrentPage().locator("span.oxd-userdropdown-tab");
    }

    public String getTopbarTitleText() {

        return topbarTitle.textContent();
    }

    public Locator getProfilePicture() {
        return profilePicture;
    }

    public Locator getUserDropdown() {

        return userDropdown;
    }

    public Locator getUserDropdownTab() {
        return userDropdownTab;
    }

    public Locator locateMenuItemByText(String menuItemText) {
        Locator menuItem = this.getCurrentPage().getByRole(AriaRole.MENUITEM).getByText(menuItemText);
        menuItem.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return menuItem;
    }
}
