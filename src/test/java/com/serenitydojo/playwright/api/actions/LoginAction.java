package com.serenitydojo.playwright.api.actions;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.serenitydojo.playwright.utils.AdminUser;
import com.serenitydojo.playwright.utils.ConfigReaderManager;
import com.serenitydojo.playwright.utils.ScenarioContext;
import io.restassured.response.Response;
import io.restassured.RestAssured;

public class LoginAction {
    String loginURL = "http://172.23.176.163:8200";

    public Playwright environment;
    public APIRequestContext request;
    public static final String LOGIN_PAGE_URL = ConfigReaderManager.getProperty("login_page_url");
    public APIResponse response;
    public ScenarioContext scenarioContext = ScenarioContext.getInstance();
    public AdminUser adminUser = new AdminUser();

    public void loginAction() {
        adminUser.loadCredentials();
        String employeeUrl = "http://172.23.176.163:8200/web/index.php/api/v2/pim/employees/6";
        String accessToken = "def5020094f9b6e14bd47ba2264fd60420ed6fcb8f84afc7383acbf4756837af2bc6f8a42e16670621784ad9c657eec406191c7c06df56128cc7986e3aa480f0d63830b6b4ffcd38bf5ce5ed42c5c4e2d21c532402784cb52922f367108e0e3eadf6203fee8c14748a24657e10a19978a538ba1dc2cc2a32311940c511ec352c3141d8a5b262ff3f5fd63714b2f11ff952fc2fdce35660905120dba99a20e1ff78a899e2";

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .get(employeeUrl)
                .then()
                .extract()
                .response();


        // Print status and body
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.getBody().asString());
    }

}

//    public AdminUser adminUser = (AdminUser) scenarioContext.getScenarioContext(ADMIN_USER);


//    public void createAPIRequestContext() {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        environment = BrowserManager.getEnvironment();
//
//        request = environment.request().newContext(new APIRequest.NewContextOptions()
//                .setBaseURL(LOGIN_PAGE_URL)
//                .setExtraHTTPHeaders(headers));
//    }
//        this.response = this.request.get(LOGIN_PAGE_URL, RequestOptions.create()
//                );
//        JSONObject responseObject = new JSONObject(response.text());
//System.out.println(responseBody.contains("OrangeHRM"));




