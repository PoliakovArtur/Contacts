package org.example.model;

public class UserValidator {
    private final static String FULL_NAME_FORMAT = "(?<letters>[а-яА-Я])+\\s\\k<letters>\\s<letters>";

    private final static String PHONE_NUMBER_FORMAT = "\\+?[78]\\d{3}[1-9]{7}";

    private final static String EMAIL_FORMAT = "[\\p{Alnum}_-]*\\p{Alnum}@\\p{Alpha}+\\.\\p{Lower}{2,3}";
}
