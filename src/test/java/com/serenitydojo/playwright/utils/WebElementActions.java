package com.serenitydojo.playwright.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WebElementActions {

    public WebElementActions() {
    }

    public static void populateField(Locator fieldToPopulate, String dataToBePopulated) {
        fieldToPopulate.fill(dataToBePopulated);

    }

    public static void clickOnButton(Locator buttonToClick) {
        buttonToClick.click();
    }

    public static void waitForWebElementToBeVisible(Locator webElement) {
        webElement.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
    }

    public static void clickOnMenuItem(Locator itemToClick) {
        itemToClick.click();
    }

    public static Locator locateHeadingByText(Page currentPage, String textToLocate) {

        Locator titleToLocate = currentPage.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(textToLocate));
        if (titleToLocate != null) {
           // WebElementActions.waitForWebElementToBeVisible(titleToLocate);
            return titleToLocate;
        } else {
            return null;
        }

    }

    public static Locator locateParagraphByText(Page currentPage, String textToLocate) {

        Locator titleToLocate = currentPage.getByRole(AriaRole.PARAGRAPH, new Page.GetByRoleOptions().setName(textToLocate));
        //WebElementActions.waitForWebElementToBeVisible(titleToLocate);
        if (titleToLocate != null) {
            return titleToLocate;
        } else {
            return null;
        }

    }

    public static Locator locateByText(Page currentPage, String textToLocate) {

        Locator titleToLocate = currentPage.getByText(textToLocate);

        //WebElementActions.waitForWebElementToBeVisible(titleToLocate);
        if (titleToLocate.isVisible()) {
            return titleToLocate;
        } else {
            return null;
        }

    }
}
