package com.serenitydojo.playwright.ui.blocks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.ui.pages.GenericPage;
import lombok.extern.log4j.Log4j2;

@Log4j2
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

        super();

        Page superPage = super.getCurrentPage();

        this.menuItemAdmin = superPage.locator("//span[normalize-space()='Admin']");
        this.menuItemPIM = superPage.locator("//span[normalize-space()='PIM']");
        this.menuItemLeave = superPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Leave"));
        this.menuItemTime = superPage.locator("//span[normalize-space()='Time']");
        this.menuItemRecruitment = superPage.locator("//span[normalize-space()='Recruitment']");
        this.menuItemMyInfo = superPage.locator("//span[normalize-space()='My Info']");
        this.menuItemPerformance = superPage.locator("//span[normalize-space()='Performance']");
        this.menuItemDashboard = superPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dashboard"));
        this.menuItemDirectory = superPage.locator("//span[normalize-space()='Directory']");
        this.menuItemMaintenance = superPage.locator("//span[normalize-space()='Maintenance']");
        this.menuItemClaim = superPage.locator("//span[normalize-space()='Claim']");
        this.menuItemBuzz = superPage.locator("//span[normalize-space()='Buzz']");
        this.menuSidepanel = superPage.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Sidepanel"));
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

//    Actions

    public void clickOnSideMenuItem(String menuItemText) {
        switch (menuItemText.trim()) {
            case "Admin":
                getMenuItemAdmin().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "PIM":
                getMenuItemPIM().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "Leave":
                getMenuItemLeave().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "Time":
                getMenuItemTime().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "Recruitment":
                getMenuItemRecruitment().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "My Info":
                getMenuItemMyInfo().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "Performance":
                getMenuItemPerformance().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "Dashboard":
                getMenuItemDashboard().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "Directory":
                getMenuItemDirectory().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "Claim":
                getMenuItemClaim().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            case "Buzz":
                getMenuItemBuzz().click();
                log.info(("Menu Item <%s> was clicked").formatted(menuItemText.trim()));
                break;
            default:
                log.error(("No Menu Item with the name <%s> was found").formatted(menuItemText.trim()));

        }
    }
}