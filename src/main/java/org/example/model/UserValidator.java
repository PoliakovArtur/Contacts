package org.example.model;

import org.example.IllegalFormatException;

public class UserValidator {

    private final static String FULL_NAME_FORMAT = "(?<letters>[а-яА-Я]){2,}\\s\\k<letters>{2,}\\s<letters>{2,}";

    private final static String PHONE_NUMBER_FORMAT = "\\+?[78]\\d{10}";

    private final static String EMAIL_FORMAT = "[\\p{Alnum}_-]*\\p{Alnum}@\\p{Alpha}+\\.\\p{Lower}{2,3}";

    private final static String NAME_FORMAT = "[А-Я][а-я]+";

    private final static String ILLEGAL_FORMAT_INPUT = "Неверный формат ввода. Верный формат: " +
            "Фамилия Имя Отчество;номер телефона;адрес электронной почты";

    private final static String ILLEGAL_FULL_NAME = "Недопустимое значение ФИО. Верный формат: " +
            "Фамилия Имя Отчество. Можно использовать только кирилицу";

    private final static String ILLEGAL_EMAIL = "Недопустимое значение email. Имя почтового ящика может состоять из " +
            "латинский букв, цифр, а также символов _ и -, но не может заканчиваться на эти символы. " +
            "Названия доменов могут состоять только из латинских букв";

    private final static String ILLEGAL_PHONE_NUMBER = "Недопустимое значение номера телефона. Верный формат:" +
            " +7/8XXXXXXXXXX (Всего 11 цифр. Номер начинается с 7 или 8. + Писать необязательно";

    public static User validateUserAndGet(String user) {
        String[] userComponents = user.split(";");
        if(userComponents.length != 3) throw new IllegalFormatException(ILLEGAL_FORMAT_INPUT);
        validate(userComponents[0], FULL_NAME_FORMAT, ILLEGAL_FULL_NAME);
        validate(userComponents[1], PHONE_NUMBER_FORMAT, ILLEGAL_PHONE_NUMBER);
        validate(userComponents[2], EMAIL_FORMAT, ILLEGAL_EMAIL);
        return getUser(userComponents);
    }

    private static void validate(String value, String regex, String message) {
        if(!value.matches(regex)) {
            throw new IllegalFormatException(message);
        }
    }

    private static User getUser(String[] userComponents) {
        deleteSpaces(userComponents);
        String[] fullName = userComponents[0].split("\\s");
        normalizeFullName(fullName);


    }

    private static void deleteSpaces(String[] components) {
        for (int i = 0; i < components.length; i++) {
            components[i] = components[i].strip();
        }
    }


    private static void normalizeFullName(String[] fullName) {
        deleteSpaces(fullName);
        for (int i = 0; i < fullName.length; i++) {
            String component = fullName[i];
            if(!component.matches(NAME_FORMAT)) {
                char firstLetter = Character.toUpperCase(component.charAt(0));
                String otherLetters = component.substring(1).toLowerCase();
                fullName[i] = firstLetter + otherLetters;
            }
        }
    }

    private static String normalizePhoneNumber(String number) {

    }
}
