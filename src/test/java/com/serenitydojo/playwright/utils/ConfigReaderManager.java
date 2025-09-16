package com.serenitydojo.playwright.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.io.IOException;

public class ConfigReaderManager {
    private static final String CONFIG_FILE_PATH = "src/test/resources/properties/credentials.properties";
    private static Properties properties;

    public static void initPropreties() {

        try (FileInputStream propertiesFile = new FileInputStream(CONFIG_FILE_PATH)) {
            properties = new Properties();

            if (propertiesFile == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE_PATH);
                return;
            }
            properties.load(propertiesFile);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public static String getProperty(String propertyKey) {
        if (properties == null) {
            initPropreties();
        }

        return properties.getProperty(propertyKey);

    }

    public static void setProperty(String propertyKey, String propertyValue) {

        if (properties == null) {
            initPropreties();
        }

        properties.setProperty(propertyKey,propertyValue);

        // Persist the change back to the file
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE_PATH)) {
            properties.store(fos, "Updated property " + propertyKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
//try with resources