package com.serenitydojo.playwright.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;

public class ConfigReaderManager {
    private static final String CONFIG_FILE_PATH = "src/test/resources/credentials.properties";
    private static Properties properties;

    public static void initPropreties() {

        FileInputStream propertiesFile = null;
        try {
            propertiesFile = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();

            //input = AdminUser.class.getClassLoader().getResourceAsStream("credentials.properties");

            // Load the properties file from the classpath

            if (propertiesFile == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE_PATH);
                return;
            }
            properties.load(propertiesFile);
        } catch (IOException e) {
           e.printStackTrace();
        } finally {
            if (propertiesFile != null) {
                try {
                    propertiesFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static String getProperty(String propertyKey) {
        if (properties == null) {
            initPropreties();
        }

        return properties.getProperty(propertyKey);

    }
}
