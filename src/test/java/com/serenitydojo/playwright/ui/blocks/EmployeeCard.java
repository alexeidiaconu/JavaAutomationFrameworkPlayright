package com.serenitydojo.playwright.ui.blocks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.ui.pages.GenericPage;

public class EmployeeCard extends GenericPage {

    private final Locator employeeCardRow;
//    private final Locator divEmployeeId;
//    private final Locator divEmployeeFirstName;
//    private final Locator divEmployeeLastName;
    private final Locator buttonDeleteEmployee;
    private final Locator buttonEditEmployee;

public EmployeeCard(String employeeFirstname, String employeeLastname, String employeeId) {
    this.employeeCardRow = super.getCurrentPage().getByRole(AriaRole.ROW)
            .filter(new Locator.FilterOptions().setHasText(employeeFirstname))
            .filter(new Locator.FilterOptions().setHasText(employeeLastname))
            .filter(new Locator.FilterOptions().setHasText(employeeId));
    this.buttonDeleteEmployee = this.employeeCardRow.locator("button:has(i.bi-trash)").first();
    this.buttonEditEmployee = this.employeeCardRow.locator("button:has(i.bi-pencil-fill)").first();
}

    public Locator getEmployeeCardRow() {
        return employeeCardRow;
    }

    public Locator getButtonDeleteEmployee() {
        return buttonDeleteEmployee;
    }

    public Locator getButtonEditEmployee() {
        return buttonEditEmployee;
    }
}
