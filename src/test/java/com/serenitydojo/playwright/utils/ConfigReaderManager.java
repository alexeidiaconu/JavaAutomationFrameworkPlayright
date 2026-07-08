package com.serenitydojo.playwright.utils;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

@Log4j2
public class ConfigReaderManager {
    private static final String CONFIG_FILE_PATH = "src/test/resources/properties/configurations.properties";
    private static final String CREDENTIALS_FILE_PATH = "src/test/resources/properties/credentials.properties";
    private static Properties properties;

    public static void initPropreties() {

        try {

            if (properties == null) {
                properties = new Properties();
            }

            loadProperties(CONFIG_FILE_PATH);

            loadProperties(CREDENTIALS_FILE_PATH);

            log.debug("Properties Initialisation  loaded successfully! ");


        } catch (Exception e) {
            log.error("An exception occurred during loading the property file:" + e.getMessage());
           e.printStackTrace();
        }
    }

    // Generic loader that merges properties
    private static void loadProperties(String filePath) {
        try (FileInputStream propertiesFile = new FileInputStream(filePath)) {

            if (propertiesFile == null) {
                log.error("Sorry, unable to find " + filePath);
                return;
            }
            properties.load(propertiesFile);


            log.debug("Property file loaded successfully: " + filePath.substring(filePath.lastIndexOf("/") + 1));
        } catch (IOException e) {
            log.error("An exception occurred during loading the property file:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyKey) {
        if (properties == null) {
            initPropreties();
        }

//        String propertyValue = properties.getProperty(propertyKey);

        if (properties.getProperty(propertyKey) == null || properties.getProperty(propertyKey).isBlank()) {

            log.error(("Failed to load value for key <%s> from property file").formatted(propertyKey));
            throw new RuntimeException(("Failed to load value for key < %s> from property file").formatted(propertyKey));
        } else {

            log.debug(("Property <%s> successfully loaded from property file").formatted(propertyKey));
            return properties.getProperty(propertyKey);
        }

    }

    public static void setProperty(String propertyKey, String propertyValue) {

        if (properties == null) {
            initPropreties();
        }

        properties.setProperty(propertyKey,propertyValue);

        // Persist the change back to the file
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE_PATH)) {
            properties.store(fos, "Updated property " + propertyKey);
            log.debug(("Property <%s> updated succesfully").formatted(propertyKey));
        } catch (IOException e) {

            log.error(("Unable to update property <%s>. Reason: %s").formatted(propertyKey, e.getMessage()));
            e.printStackTrace();
        }
    }

    }
//try with resources
//string format