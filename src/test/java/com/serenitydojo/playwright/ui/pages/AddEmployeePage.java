package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AddEmployeePage extends GenericPage {

    private final Locator titleAddEmployee;
    private final Locator firstNameField;
    private final Locator lastNameField;
    private final Locator employeeIdField;
    private final Locator buttonSave;
    private final Locator buttonCancel;

    public AddEmployeePage() {
        super();
       this.titleAddEmployee = super.getCurrentPage().getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Add Employee"));
       this.firstNameField = super.getCurrentPage().getByPlaceholder("First Name");
       this.lastNameField = super.getCurrentPage().getByPlaceholder("Last Name");
       this.employeeIdField = super.getCurrentPage().getByRole(AriaRole.TEXTBOX).locator("(//input[@class='oxd-input oxd-input--active'])[2]");
       this.buttonSave = super.getCurrentPage().getByRole(AriaRole.BUTTON).getByText("Save");
       this.buttonCancel = super.getCurrentPage().getByRole(AriaRole.BUTTON).getByText("Cancel");
    }

    public Locator getTitleAddEmployee() {
        return titleAddEmployee;
    }

    public Locator getFirstNameField() {
        return firstNameField;
    }

    public Locator getLastNameField() {
        return lastNameField;
    }

    public Locator getEmployeeIdField() {
        return employeeIdField;
    }

    public Locator getButtonSave() {
        return buttonSave;
    }

    public Locator getButtonCancel() {
        return buttonCancel;
    }
}
