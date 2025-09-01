package com.serenitydojo.playwright.resources;

public enum Constants {

    WAIT_TIMEOUT(3000);

    private final int value;

    Constants(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}

