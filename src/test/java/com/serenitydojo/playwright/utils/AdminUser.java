package com.serenitydojo.playwright.utils;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;

@Log4j2

@NonNull

public class AdminUser {
    public  String USERNAME;
    public  String PASSWORD;


    public void loadCredentials() {

        this.USERNAME = ConfigReaderManager.getProperty("username");

        if (this.USERNAME == null || this.USERNAME.isBlank()) {
            log.error("UserName not loaded for Admin User: " + this.USERNAME);
            return;
        }

        this.PASSWORD = ConfigReaderManager.getProperty("password");

            if (this.PASSWORD == null || this.PASSWORD.isBlank()){
                log.error("Password not loaded for Admin User with username: " + this.USERNAME);
                return;
            }

            log.debug("Credentials loaded for Admin user with username: " + this.USERNAME);
    }
}
