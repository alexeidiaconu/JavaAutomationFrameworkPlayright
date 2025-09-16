package com.serenitydojo.playwright.utils;

import com.serenitydojo.playwright.utils.enums.ContextKeys;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static ScenarioContext instance;
    private Map<String,Object> scenarioContext;

    private ScenarioContext() {

        this.scenarioContext = new HashMap<String,Object> ();
    }

    public static ScenarioContext getInstance() {

        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public Object getScenarioContext(ContextKeys key) {
        return scenarioContext.get(key.name());
    }


    public void setScenarioContext(ContextKeys key, Object value) {
        this.scenarioContext.put(key.name(), value);
    }
}

//de tratat situatia cand cheia nu exista
//Cheile tre sa fie in enum @