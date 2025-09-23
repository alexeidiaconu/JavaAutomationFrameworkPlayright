package com.serenitydojo.playwright.ui.blocks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.serenitydojo.playwright.ui.pages.GenericPage;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter

public class TopBar extends GenericPage {
    private final Locator topbarTitle;
    private final Locator userDropdown;
    private final Locator profilePicture;
    private final Locator userDropdownTab;


    public TopBar() {
        super();

        Page topBarCurrentPage = this.getCurrentPage();
        topbarTitle = topBarCurrentPage.locator("h6.oxd-topbar-header-breadcrumb-module");
        userDropdown = topBarCurrentPage.locator("//span[@class='oxd-userdropdown-tab']");
        profilePicture = topBarCurrentPage.getByRole(AriaRole.BANNER).getByRole(AriaRole.IMG, new Locator.GetByRoleOptions().setName("profile picture"));
        userDropdownTab = topBarCurrentPage.locator("span.oxd-userdropdown-tab");
        log.trace(("New TopBar object instantiated: %s").formatted(this.toString()));
    }

    public String getTopbarTitleText() {

        log.info(("getTopbarTitleText() executed successfully. Result: %s").formatted(topbarTitle.textContent()));
        return topbarTitle.textContent();
    }

//    public Locator getProfilePicture() {
//        return profilePicture;
//    }
//
//    public Locator getUserDropdown() {
//
//        return userDropdown;
//    }
//
//    public Locator getUserDropdownTab() {
//        return userDropdownTab;
//    }

    public Locator locateMenuItemByText(String menuItemText) {
        Locator menuItem = this.getCurrentPage().getByRole(AriaRole.MENUITEM).getByText(menuItemText);
        menuItem.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return menuItem;
    }
}
