package com.serenitydojo.playwright.utils;

public enum ContextKeys {
    LOGIN_PAGE("loginPage"),
    SIDE_MENU("sideMenu"),
    TOP_BAR("topBar"),
    ADMIN_USER("adminUser");

    private final String keyName;

    ContextKeys(String keyName) {
        this.keyName = keyName;
    }

    public String getName() {
        return keyName;
    }
}
