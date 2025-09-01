package com.serenitydojo.playwright.ui.blocks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.ui.pages.GenericPage;

public class SideMenu extends GenericPage {

    private final Locator menuItemAdmin;
    private final Locator menuItemPIM;
    private final Locator menuItemLeave;
    private final Locator menuItemTime;
    private final Locator menuItemRecruitment;
    private final Locator menuItemMyInfo;
    private final Locator menuItemPerformance;
    private final Locator menuItemDashboard;
    private final Locator menuItemDirectory;
    private final Locator menuItemMaintenance;
    private final Locator menuItemClaim;
    private final Locator menuItemBuzz;
    private final Locator menuSidepanel;

    public SideMenu() {
        this.menuItemAdmin = super.getCurrentPage().locator("//span[normalize-space()='Admin']");
        this.menuItemPIM = super.getCurrentPage().locator("//span[normalize-space()='PIM']");
        this.menuItemLeave = super.getCurrentPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Leave"));
        this.menuItemTime = super.getCurrentPage().locator("//span[normalize-space()='Time']");
        this.menuItemRecruitment = super.getCurrentPage().locator("//span[normalize-space()='Recruitment']");
        this.menuItemMyInfo = super.getCurrentPage().locator("//span[normalize-space()='My Info']");
        this.menuItemPerformance = super.getCurrentPage().locator("//span[normalize-space()='Performance']");
        this.menuItemDashboard = super.getCurrentPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dashboard"));
        this.menuItemDirectory = super.getCurrentPage().locator("//span[normalize-space()='Directory']");
        this.menuItemMaintenance = super.getCurrentPage().locator("//span[normalize-space()='Maintenance']");
        this.menuItemClaim = super.getCurrentPage().locator("//span[normalize-space()='Claim']");
        this.menuItemBuzz = super.getCurrentPage().locator("//span[normalize-space()='Buzz']");
        this.menuSidepanel = super.getCurrentPage().getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Sidepanel"));
    }

    public Locator getMenuItemAdmin() {
        return menuItemAdmin;
    }

    public Locator getMenuItemPIM() {
        return menuItemPIM;
    }

    public Locator getMenuItemLeave() {
        return menuItemLeave;
    }

    public Locator getMenuItemTime() {
        return menuItemTime;
    }

    public Locator getMenuItemRecruitment() {
        return menuItemRecruitment;
    }

    public Locator getMenuItemMyInfo() {
        return menuItemMyInfo;
    }

    public Locator getMenuItemPerformance() {
        return menuItemPerformance;
    }

    public Locator getMenuItemDashboard() {
        return menuItemDashboard;
    }

    public Locator getMenuItemDirectory() {
        return menuItemDirectory;
    }

    public Locator getMenuItemMaintenance() {
        return menuItemMaintenance;
    }

    public Locator getMenuItemClaim() {
        return menuItemClaim;
    }

    public Locator getMenuItemBuzz() {
        return menuItemBuzz;
    }

    public Locator getMenuSidepanel() {
        return menuSidepanel;
    }

    public void clickOnSideMenuItem(String menuItemText) {
        switch (menuItemText.trim()) {
            case "Admin":
                getMenuItemAdmin().click();
                break;
            case "PIM":
                getMenuItemPIM().click();
                break;
            case "Leave":
                getMenuItemLeave().click();
                break;
            case "Time":
                getMenuItemTime().click();
                break;
            case "Recruitment":
                getMenuItemRecruitment().click();
                break;
            case "My Info":
                getMenuItemMyInfo().click();
                break;
            case "Performance":
                getMenuItemPerformance().click();
                break;
            case "Dashboard":
                getMenuItemDashboard().click();
                break;
            case "Directory":
                getMenuItemDirectory().click();
                break;
            case "Claim":
                getMenuItemClaim().click();
                break;
            case "Buzz":
                getMenuItemBuzz().click();
                break;
        }
    }
}