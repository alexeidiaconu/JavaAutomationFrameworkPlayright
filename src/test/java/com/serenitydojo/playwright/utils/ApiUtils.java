package com.serenitydojo.playwright.utils;

import com.serenitydojo.playwright.utils.enums.ApiUrls;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.serenitydojo.playwright.utils.enums.ContextKeys.API_RESPONSE;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2

public class ApiUtils {

    public static void  assertResponseCode(int expectedResponseCode, int actualResponseCode) {

        log.debug(("Asserting response code of the API call.assertResponseCode(int expectedResponseCode, int actualResponseCode) Expected: <%s> / Actual <%s>").formatted(expectedResponseCode, actualResponseCode));
        assertThat(actualResponseCode)
                .withFailMessage("Status code mismatch: expected '%s', but got '%s'", expectedResponseCode, actualResponseCode)
                .isEqualTo(expectedResponseCode);
    }

    public static String extractFieldValueFromResponse(String fieldKey){
        ScenarioContext scenarioContext = ScenarioContext.getInstance();
        Map<String, Object> actualData = (Map<String, Object>) scenarioContext.getScenarioContext(API_RESPONSE);

        log.debug(("Extracting the field value from response: extractFieldValueFromResponse(String fieldKey <%s>)").formatted(fieldKey));
        log.trace(("Extracting the field value from response: extractFieldValueFromResponse(String fieldKey <%s>): Result <%s>").formatted(fieldKey,actualData.get(fieldKey)));
        return actualData.get(fieldKey).toString();
    }

    public static void getAccessAndRefreshTokens(String refreshToken, ApiUrls getAccessAndRefreshTokenUrl) {

        String endPointURL = getAccessAndRefreshTokenUrl.getUrl();
        int statusCode = 0;

        try {
            Response tokensResponse = given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("grant_type", "refresh_token")
                    .formParam("client_id", "b4727eb3d033017cb1aad86cd0519280")
                    .formParam("refresh_token", refreshToken)
                    .post(endPointURL);

            statusCode = tokensResponse.statusCode();

            log.debug(("Response received by the API call for new access and refresh tokens generation is: <%s>").formatted(statusCode));

            if (statusCode == 200) {
                String responseToString = tokensResponse.asString();
                log.trace(("The full response sent by the server is: %s").formatted(responseToString));

                String accessTokenRegex = "\"access_token\":\"(\\w+)\"";
                String refreshTokenRegex = "\"refresh_token\":\"(\\w+)\"";


                String newAccessToken = matchRegexFromJsonResponse(accessTokenRegex, tokensResponse);

                String newRefreshToken = matchRegexFromJsonResponse(refreshTokenRegex, tokensResponse);

                log.trace("access_token: " + newAccessToken);
                log.trace("refresh_token: " + newRefreshToken);

                ConfigReaderManager.setProperty("refresh_token", newRefreshToken);
                ConfigReaderManager.setProperty("access_token", newAccessToken);

                log.debug("access_token and refresh_token properties successfully set up in the property file");
            }
        } catch (RuntimeException e) {

            log.error(("Unsuccessful API call for new access and refresh tokens generation. The response  is: <%s>. The reason is: %s").formatted(statusCode, e.getMessage()));
    }


    }

    public static void compareExpectedWithActualData(io.cucumber.datatable.DataTable expectedData,Map<String, Object> actualData){

        for (Map<String, String> row : expectedData.asMaps()) {
            String field = row.get("field");
            String expected = row.get("value");

            Object actual = actualData.get(field);
            assertThat(actual.toString())
                    .withFailMessage("Field '%s' mismatch: expected '%s', but got '%s'", field, expected, actual)
                    .isEqualTo(expected);

            log.info("compareExpectedWithActualData(io.cucumber.datatable.DataTable expectedData,Map<String, Object> actualData) executed successfully");
        }
    }

    public static String matchFirstTagFromJsonResponse(Response jsonResponse) {

        String responseAsString = jsonResponse.asString();
        Pattern pattern = Pattern.compile("^\\{\\s*\"(\\w+)\":");
        Matcher matcher = pattern.matcher(responseAsString);

        if (matcher.find()) {
            String firstTag = matcher.group(1);

            log.debug(("matchFirstTagFromJsonResponse(Response jsonResponse) executed successfully. Result: <%s>").formatted(firstTag));
            return firstTag;
        }

        log.error("matchFirstTagFromJsonResponse(Response jsonResponse) executed unsuccessfully. Unable to retrieve first tag");
        return "";
    }

    public static String matchRegexFromJsonResponse(String regexToMatch, Response jsonResponse) {

//       String regex = "^\\{\\s*\"(\\w+)\":";
        String responseAsString = jsonResponse.asString();
        Pattern pattern = Pattern.compile(regexToMatch);
        Matcher matcher = pattern.matcher(responseAsString);

        if (matcher.find()) {

            log.debug(("matchRegexFromJsonResponse(String regexToMatch, Response jsonResponse) executed successfully. Result: <%s>").formatted( matcher.group(1)));
            return matcher.group(1);
        }

        log.error("matchRegexFromJsonResponse(String regexToMatch, Response jsonResponse) executed unsuccessfully. Unable to retrieve the result.");
        return "";
    }





}
