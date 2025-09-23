package com.serenitydojo.playwright.utils;

import com.serenitydojo.playwright.utils.enums.ContextKeys;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class ScenarioContext {

    private static ScenarioContext instance;
    private Map<String,Object> scenarioContext;

    private ScenarioContext() {

        this.scenarioContext = new HashMap<String,Object> ();
        log.trace(("New scenario Context instantiated: <%s>").formatted(this.toString()));
    }

    public static ScenarioContext getInstance() {

        if (instance == null) {
            instance = new ScenarioContext();
        }
        log.debug(("Executing ScenarioContext getInstance(). Result: <%s>").formatted(instance.toString()));
        return instance;
    }

    public Object getScenarioContext(ContextKeys key) {

        log.debug(("Executing getScenarioContext(ContextKeys key). Value is requested for the key: <%s>").formatted(key.name()));
        return scenarioContext.get(key.name());
    }


    public void setScenarioContext(ContextKeys key, Object value) {

        log.debug((" Executing setScenarioContext(ContextKeys key, Object value) for key: <%s> ").formatted(key));
        this.scenarioContext.put(key.name(), value);
    }
}

//de tratat situatia cand cheia nu exista
//Cheile tre sa fie in enum @