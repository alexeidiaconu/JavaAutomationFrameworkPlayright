package com.serenitydojo.playwright.utils.enums;

public enum ContextKeys {
    LOGIN_PAGE("loginPage"),
    SIDE_MENU("sideMenu"),
    TOP_BAR("topBar"),
    ADMIN_USER("adminUser"),
    ACCESS_TOKEN("access_token"),
    API_RESPONSE("ApiResponse"),
    API_RESPONSE_CODE("ApiResponseCode"),
    ;
    private final String keyName;

    ContextKeys(String keyName) {
        this.keyName = keyName;
    }

    public String getName() {
        return keyName;
    }
}
