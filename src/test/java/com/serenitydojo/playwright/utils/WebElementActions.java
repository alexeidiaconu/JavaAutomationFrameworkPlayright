package com.serenitydojo.playwright.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.serenitydojo.playwright.resources.Constants;

public class WebElementActions {

    public WebElementActions() {
    }

    public static void populateField(Locator fieldToPopulate, String dataToBePopulated) {
        fieldToPopulate.fill(dataToBePopulated);

    }

    public static void clickOnButton(Locator buttonToClick) {
        buttonToClick.click();
    }

    public static boolean waitForWebElementToBeVisible(Locator webElement, Constants timeoutMillis) {

        try {
            webElement.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(timeoutMillis.ordinal())
            );
            return true;
        } catch (TimeoutError e) {
            System.err.println("Element was not visible within " + timeoutMillis + " ms. Locator: " + webElement);
            return false;
        } catch (Exception e) {
            System.err.println("Failed to wait for element visibility: " + e.getMessage());
            return false;
        }
    }

    public static void clickOnMenuItem(Locator itemToClick) {
        itemToClick.click();
    }

    public static Locator locateHeadingByText(Page currentPage, String textToLocate) {

        Locator titleToLocate = currentPage
                .getByRole(AriaRole.HEADING, new Page.GetByRoleOptions()
                .setName(textToLocate))
                .first();

          if (titleToLocate != null) {
            return titleToLocate;
        } else {
            return null;
        }

    }

    public static Locator locateParagraphByText(Page currentPage, String textToLocate) {

        Locator titleToLocate = currentPage.getByRole(AriaRole.PARAGRAPH, new Page.GetByRoleOptions().setName(textToLocate));
        if (titleToLocate != null) {
            return titleToLocate;
        } else {
            return null;
        }

    }

    public static Locator locateByText(Page currentPage, String textToLocate) {

        Locator titleToLocate = currentPage.getByText(textToLocate);

        if (titleToLocate.isVisible()) {
            return titleToLocate;
        } else {
            return null;
        }

    }
}
