package com.serenitydojo.playwright;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/serenitydojo/playwright/resources/features",
        glue = {"com.serenitydojo.playwright.steps"},
        tags = "@Smoke",
        plugin = {"pretty", "html:target/cucumber-reports"}
)

public class CucumberTestRunner {

}
