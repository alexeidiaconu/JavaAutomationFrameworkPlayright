package com.serenitydojo.playwright.utils.enums;

public enum ApiUrls {
    DELETE_EMPLOYEE("/api/v2/pim/employees"),
    EMPLOYEE("http://172.23.176.163:8200/web/index.php/api/v2/pim/employees"),
    GET_AN_EMPLOYEE("http://172.23.176.163:8200/web/index.php/api/v2/pim/employees/"),
//    CREATE_AN_EMPLOYEE("http://172.23.176.163:8200/web/index.php/api/v2/pim/employees"),
    GET_ACCESS_AND_REFRESH_TOKEN("http://172.23.176.163:8200/web/index.php/oauth2/token")
;
    private final String Url;

    ApiUrls(String path) {
        this.Url = path;
    }
    public String getUrl() {

        return this.Url;
    }
}
