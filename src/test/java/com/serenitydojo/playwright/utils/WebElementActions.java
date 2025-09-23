package com.serenitydojo.playwright.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.serenitydojo.playwright.utils.enums.Constants;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WebElementActions {

//    public WebElementActions() {
//    }

    public static void populateField(Locator fieldToPopulate, String dataToBePopulated) {

        String fieldToPopulateName = fieldToPopulate.getAttribute("name");

        try {
            fieldToPopulate.fill(dataToBePopulated);
            log.info(("The field <%s> was populated successfully.").formatted(fieldToPopulateName));
            log.trace(("The field <%s> was populated successfully with: %s").formatted(fieldToPopulateName,dataToBePopulated));
        } catch (Exception e) {
            log.error(("The field < %s > was NOT populated. Reason: %s").formatted(fieldToPopulateName,e.getMessage()));
            throw new RuntimeException(e);
        }

    }

    public static void clickOnButton(Locator buttonToClick) {
        try {
            String buttonText = buttonToClick.innerText();
            buttonToClick.click();
            log.info(("The Button <%s> was clicked.").formatted(buttonText));
        } catch (Exception e) {

            log.error(("Unable to click on button <%s>. Reason: %s").formatted(buttonToClick.getAttribute("name"), e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    public static boolean waitForWebElementToBeVisible(Locator webElement, Constants timeoutMillis) {

        String webElementName = webElement.innerText();

        try {
            webElement.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(timeoutMillis.ordinal())
            );
            log.debug(("Web element <%s> became visible").formatted(webElementName));
            return true;
        } catch (TimeoutError e) {
            log.error(("Element was not visible within %s ms. Locator: %s").formatted(timeoutMillis,webElementName));
            return false;
        } catch (Exception e) {
            log.error(("Failed to wait for element <%s> visibility. Reason: %s").formatted(webElementName,e.getMessage()));
            return false;
        }
    }

    public static void clickOnMenuItem(Locator itemToClick) {

        try {
            itemToClick.click();
            log.info(("Clicked on Menu Item <%s>").formatted(itemToClick.textContent()));
        } catch (Exception e) {

            log.error(("Failed to click on Menu Item <%s>. Reason: %s").formatted(itemToClick.getAttribute("name"), e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    public static Locator locateHeadingByText(Page currentPage, String textToLocate) {



        try {
            Locator titleToLocate = currentPage
                    .getByRole(AriaRole.HEADING, new Page.GetByRoleOptions()
                    .setName(textToLocate))
                    .first();

            if (titleToLocate.isVisible()) {

                log.debug(("locateHeadingByText(Page currentPage, String textToLocate) executed successfully. Result: %s").formatted(titleToLocate.textContent()));

                return titleToLocate;
            }
            else return null;
        } catch (Exception e) {

            log.error(("locateHeadingByText(Page currentPage, String textToLocate) executed Unsuccessfully. Unable to locate <%s>. Reason: %s").formatted(textToLocate,e.getMessage()));
            throw new RuntimeException(e);
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

        try {
            Locator titleToLocate = currentPage.getByText(textToLocate);

            if (titleToLocate.isVisible()) {

                log.debug(("locateByText(Page currentPage, String textToLocate) executed successfully. Result: ").formatted(titleToLocate.toString()));
                return titleToLocate;
            } else {
                log.error(("Unable to locate text <%s>").formatted(textToLocate));
                return null;
            }

        } catch (Exception e) {

            log.error(("Unable to locate text <%s>. Reason: %s").formatted(textToLocate, e.getMessage()));
            throw new RuntimeException(e);
        }

    }
}
