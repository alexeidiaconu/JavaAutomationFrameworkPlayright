package com.serenitydojo.playwright.utils;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;



public class AdminUser {
    public static String USERNAME;
    public static String PASSWORD;

    Properties credentials = new Properties();
    InputStream input;

    public void loadCredentials() {
        try {
            input = AdminUser.class.getClassLoader().getResourceAsStream("credentials.properties");

            // Load the properties file from the classpath

            if (input == null) {
                System.out.println("Sorry, unable to find credentials.properties");
                return;
            }
            credentials.load(input);

            AdminUser.USERNAME = credentials.getProperty("username");
            System.out.println("Username: " + AdminUser.USERNAME);
            AdminUser.PASSWORD = credentials.getProperty("password");
            System.out.println("Password: " + AdminUser.PASSWORD);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
