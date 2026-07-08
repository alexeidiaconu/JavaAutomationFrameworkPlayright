package com.serenitydojo.playwright.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class AddEmployeePage extends GenericPage {

    private final Locator titleAddEmployee;
    private final Locator firstNameField;
    private final Locator lastNameField;
    private final Locator employeeIdField;
    private final Locator buttonSave;
    private final Locator buttonCancel;

    public AddEmployeePage() {
        super();

        Page empCurrentPage = super.getCurrentPage();

       this.titleAddEmployee = empCurrentPage.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Add Employee"));
       this.firstNameField = empCurrentPage.getByPlaceholder("First Name");
       this.lastNameField = empCurrentPage.getByPlaceholder("Last Name");
       this.employeeIdField = empCurrentPage.getByRole(AriaRole.TEXTBOX).locator("(//input[@class='oxd-input oxd-input--active'])[2]");
       this.buttonSave = empCurrentPage.getByRole(AriaRole.BUTTON).getByText("Save");
       this.buttonCancel = empCurrentPage.getByRole(AriaRole.BUTTON).getByText("Cancel");

       log.trace(("New Add Employee Page instantiated: <%s>").formatted(this.toString()));
    }



//    public Locator getTitleAddEmployee() {
//        return titleAddEmployee;
//    }
//
//    public Locator getFirstNameField() {
//        return firstNameField;
//    }
//
//    public Locator getLastNameField() {
//        return lastNameField;
//    }
//
//    public Locator getEmployeeIdField() {
//        return employeeIdField;
//    }
//
//    public Locator getButtonSave() {
//        return buttonSave;
//    }
//
//    public Locator getButtonCancel() {
//        return buttonCancel;
//    }
}
