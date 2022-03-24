package com.bunbusoft.ayakashi.commons;

public class Constants {
    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final char DEFAULT_ESCAPE_CHAR = '\\';
    public static final Integer numberPerPage = 10;

    private Constants() {}
}
