package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.serenitydojo.playwright.resources.Constants;
import com.serenitydojo.playwright.ui.blocks.EmployeeCard;

public class PimPage extends GenericPage{

    private final Locator menuItemConfiguration;
    private final Locator menuItemEmployeeList;
    private final Locator menuItemAddEmployee;
    private final Locator menuItemReports;
    private final Locator iconButtonEmployeeInformation;
    private final Locator buttonAddEmployee;
    private final Locator numberOfRecords;
    private final Locator listOfEmployees;

    private EmployeeCard employeeCard;

    //private Locator employeeCard = null;

    public PimPage() {
        super();
        this.menuItemConfiguration = super.getCurrentPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Configuration"));
        this.menuItemEmployeeList = super.getCurrentPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Employee List"));
        this.menuItemAddEmployee = super.getCurrentPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add Employee"));
        this.menuItemReports = super.getCurrentPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Reports"));
        this.iconButtonEmployeeInformation = super.getCurrentPage().getByRole(AriaRole.BUTTON).locator("//div[@class='--toggle']//button[@type='button']");
        this.buttonAddEmployee = super.getCurrentPage().getByRole(AriaRole.BUTTON).getByText("Add");
        this.numberOfRecords = super.getCurrentPage().locator("//span[@class='oxd-text oxd-text--span']").getByText("Records Found");
        this.listOfEmployees = super.getCurrentPage().getByRole(AriaRole.TABLE);
    }

    public void setEmployeeCard(String employeeFirstname, String employeeLastname, String employeeId) {
        this.employeeCard = new EmployeeCard(employeeFirstname, employeeLastname,employeeId);
    }

    public Locator getMenuItemConfiguration() {
        return menuItemConfiguration;
    }

    public Locator getNumberOfRecords() {
        return numberOfRecords;
    }

    public Locator getButtonAddEmployee() {
        return buttonAddEmployee;
    }

    public Locator getIconButtonEmployeeInformation() {
        return iconButtonEmployeeInformation;
    }

    public Locator getMenuItemReports() {
        return menuItemReports;
    }

    public Locator getMenuItemAddEmployee() {
        return menuItemAddEmployee;
    }

    public Locator getMenuItemEmployeeList() {
        return menuItemEmployeeList;
    }

    public Locator getListOfEmployees() {
        return listOfEmployees;
    }

    public EmployeeCard getEmployeeCard() {
        return this.employeeCard;
    }
//  Predicate byName = ()-> new Locator.FilterOptions().setHasText(employeeFirstname);
//    public void setEmployeeCardByIdFirstnameAndLastname(String employeeFirstname, String employeeLastname, String employeeId) {
//
//        super.waitForPageToBeVisible(Constants.WAIT_TIMEOUT);
//        super.getCurrentPage().evaluate("window.scrollBy(0, 1000)");
//        this.employeeCard = super.getCurrentPage().getByRole(AriaRole.ROW)
//                                    .filter(new Locator.FilterOptions().setHasText(employeeFirstname))
//                                    .filter(new Locator.FilterOptions().setHasText(employeeLastname))
//                                    .filter(new Locator.FilterOptions().setHasText(employeeId));
//    }
//
//
//    public Locator getEmployeeCardTrashButton () {
//        this.getEmployeeCard().scrollIntoViewIfNeeded();
//        this.getEmployeeCard().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        Locator trashButton = this.employeeCard.locator("button:has(i.bi-trash)").first();
//        return trashButton;
//    }

}
