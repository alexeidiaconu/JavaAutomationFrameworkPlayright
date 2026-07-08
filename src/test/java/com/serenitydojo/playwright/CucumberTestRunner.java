package com.serenitydojo.playwright;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features =  "classpath:features", //"src/test/resources/features/",
        glue = {"com.serenitydojo.playwright.steps","com.serenitydojo.playwright.hooks"},
        tags = "@Smoke",
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        }
)

public class CucumberTestRunner {

}

///"src/test/java/com/serenitydojo/playwright/resources/features",