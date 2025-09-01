package com.serenitydojo.playwright.resources;

public enum ApiUrls {
    DELETE_EMPLOYEE("/api/v2/pim/employees");

    private final String Url;

    ApiUrls(String path) {
        this.Url = path;
    }
    public String getUrl() {

        return this.Url;
    }
}
