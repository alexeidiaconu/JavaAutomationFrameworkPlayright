package com.serenitydojo.playwright.utils;

import com.serenitydojo.playwright.utils.enums.ApiUrls;
import io.restassured.response.Response;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.serenitydojo.playwright.utils.enums.ContextKeys.API_RESPONSE;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiUtils {

    public static void  assertResponseCode(int expectedResponseCode, int actualResponseCode) {
        assertThat(actualResponseCode)
                .withFailMessage("Status code mismatch: expected '%s', but got '%s'", expectedResponseCode, actualResponseCode)
                .isEqualTo(expectedResponseCode);
    }

    public static String extractFieldValueFromResponse(String fieldKey){
        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        Map<String, Object> actualData = (Map<String, Object>) scenarioContext.getScenarioContext(API_RESPONSE);
        return actualData.get(fieldKey).toString();
    }

    public static void getAccessAndRefreshTokens(String refreshToken, ApiUrls getAccessAndRefreshTokenUrl) {

        String endPointURL = getAccessAndRefreshTokenUrl.getUrl();


        Response tokensResponse = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "refresh_token")
                .formParam("client_id", "b4727eb3d033017cb1aad86cd0519280")
                .formParam("refresh_token", refreshToken)
                .post(endPointURL);

        System.out.println(tokensResponse.statusCode());
        String responseToString = tokensResponse.asString();
        System.out.println(responseToString);

        String accessTokenRegex = "\"access_token\":\"(\\w+)\"";
        String refreshTokenRegex = "\"refresh_token\":\"(\\w+)\"";


        String newAccessToken = matchRegexFromJsonResponse(accessTokenRegex,tokensResponse);

        String newRefreshToken = matchRegexFromJsonResponse(refreshTokenRegex,tokensResponse);

        System.out.println("access_token: " + newAccessToken);
        System.out.println("refresh_token: " + newRefreshToken);

        ConfigReaderManager.setProperty("refresh_token",newRefreshToken);
        ConfigReaderManager.setProperty("access_token",newAccessToken);


    }

    public static void compareExpectedWithActualData(io.cucumber.datatable.DataTable expectedData,Map<String, Object> actualData){

        for (Map<String, String> row : expectedData.asMaps()) {
            String field = row.get("field");
            String expected = row.get("value");

            Object actual = actualData.get(field);
            assertThat(actual.toString())
                    .withFailMessage("Field '%s' mismatch: expected '%s', but got '%s'", field, expected, actual)
                    .isEqualTo(expected);
        }
    }

    public static String matchFirstTagFromJsonResponse(Response jsonResponse) {

        String responseAsString = jsonResponse.asString();
        Pattern pattern = Pattern.compile("^\\{\\s*\"(\\w+)\":");
        Matcher matcher = pattern.matcher(responseAsString);

        if (matcher.find()) {
            String firstTag = matcher.group(1);
            return firstTag;
        }
        return "";
    }

    public static String matchRegexFromJsonResponse(String regexToMatch, Response jsonResponse) {

//       String regex = "^\\{\\s*\"(\\w+)\":";
        String responseAsString = jsonResponse.asString();
        Pattern pattern = Pattern.compile(regexToMatch);
        Matcher matcher = pattern.matcher(responseAsString);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }





}
