package com.serenitydojo.playwright.api.actions;

import com.serenitydojo.playwright.utils.ApiUtils;
import com.serenitydojo.playwright.utils.enums.ApiUrls;
import com.serenitydojo.playwright.utils.ConfigReaderManager;
import com.serenitydojo.playwright.utils.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.serenitydojo.playwright.utils.enums.ContextKeys.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;


public class ApiActions {

    public static int DeleteAPI(ApiUrls endpointURL,String empNumber) {

        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        String accessToken = (String)scenarioContext.getScenarioContext(ACCESS_TOKEN);

        String requestBody = "{\"ids\":[" + empNumber + "]}";


        Response deleteAPIResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
//                .formParam("ids", "[" + empNumber + "]")
                .when()
                .delete(endpointURL.getUrl());

        scenarioContext.setScenarioContext(API_RESPONSE_CODE, deleteAPIResponse.statusCode());
        return deleteAPIResponse.statusCode();
    }

    public  static int PostAPI(ApiUrls endpointURL,DataTable inputData){

        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        String accessToken = (String)scenarioContext.getScenarioContext(ACCESS_TOKEN);

        // Convert DataTable into a Map
        Map<String, String> requestBody = inputData.asMap(String.class, String.class);

        // Performing POST request and validating its response.
        Response postAPIResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .body(requestBody)  // RestAssured will serialize Map -> JSON
                .when()
                .post(endpointURL.getUrl())
                .then()
                .extract()
                .response();
        if (postAPIResponse.statusCode() == 200) {
            setScenarioContextWithRequestResponse(postAPIResponse);
        }

        scenarioContext.setScenarioContext(API_RESPONSE_CODE, postAPIResponse.statusCode());
        return postAPIResponse.statusCode();

    }

    public static int GetAPI(String endPointURL) {

        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        String accessToken = (String)scenarioContext.getScenarioContext(ACCESS_TOKEN);


        Response getAPIResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                //HTTP Request Method.
                .get(endPointURL);

                // Ensuring that status code is 200 OK.
    if (getAPIResponse.statusCode() == 200) {
        setScenarioContextWithRequestResponse(getAPIResponse);
    }

        scenarioContext.setScenarioContext(API_RESPONSE_CODE, getAPIResponse.statusCode());
        return getAPIResponse.statusCode();
    }

    public static void setScenarioContextWithRequestResponse(Response requestResponse) {
        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        if (requestResponse.contentType().equals("application/json")) {
//        String regexToMatch = "^\\{\\s*\"(\\w+)\":";
            scenarioContext.setScenarioContext(API_RESPONSE, requestResponse.jsonPath().getMap(ApiUtils.matchFirstTagFromJsonResponse(requestResponse)));
        } else {
            scenarioContext.setScenarioContext(API_RESPONSE, requestResponse.asString());
        }
    }

}
