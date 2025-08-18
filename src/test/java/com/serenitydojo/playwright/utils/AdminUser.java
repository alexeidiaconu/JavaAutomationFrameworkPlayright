package com.serenitydojo.playwright.utils;

public class AdminUser {
    public  String USERNAME;
    public  String PASSWORD;


    public void loadCredentials() {

            this.USERNAME = ConfigReaderManager.getProperty("username");
            this.PASSWORD = ConfigReaderManager.getProperty("password");
    }
}
