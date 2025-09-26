package com.serenitydojo.playwright.utils;

import com.serenitydojo.playwright.utils.enums.ContextKeys;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class ScenarioContext {

    private static ScenarioContext instance;
    private Map<ContextKeys,Object> scenarioContext;

    private ScenarioContext() {

        this.scenarioContext = new HashMap<ContextKeys,Object> ();
        log.trace(("New scenario Context instantiated: <%s>").formatted(this.toString()));
    }

    public static ScenarioContext getInstance() {

        if (instance == null) {
            instance = new ScenarioContext();
        }
        log.debug(("Executing ScenarioContext getInstance(). Result: <%s>").formatted(instance.toString()));
        return instance;
    }

    public <T> T getScenarioContext(ContextKeys key) {

        if (! scenarioContext.containsKey(key)) {
            log.error("Key NOT found in Scenario Context: " + key);
            throw new RuntimeException("Key NOT found in Scenario Context: " + key);
        }

        log.debug(("Executing getScenarioContext(ContextKeys key). Value is requested for the key: <%s>").formatted(key.name()));
        return (T) scenarioContext.get(key);
    }


    public void setScenarioContext(ContextKeys key, Object value) {

//        if (scenarioContext.containsKey(key)){
//            log.error(("The key < %s > already exists in Scenario Context!").formatted(key));
//            throw new RuntimeException(("The key < %s > already exists in Scenario Context!").formatted(key));
//        }

        log.debug((" Executing setScenarioContext(ContextKeys key, Object value) for key: <%s> ").formatted(key));
        this.scenarioContext.put(key, value);
    }

    public void clearScenarioContext() {
        log.debug("Clearing scenario context. All stored values will be removed.");
        scenarioContext.clear();
    }
}

//de tratat situatia cand cheia nu exista
//Cheile tre sa fie in enum @