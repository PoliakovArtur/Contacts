package org.example.application;

import org.example.model.Contact;
import org.example.service.ContactService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Application {

    private final static String UNKNOWN_COMMAND_FORMAT =
            "%s не является допустимой командой в данном приложении.\n";
    private final static String INVALID_FIRST_NAME =
            "Неверно введено имя. Имя должно состоять только из кириллических символов. Регистр не важен.";
    private final static String INVALID_LAST_NAME =
            "Неверно введена фамилия. Фамилия должна состоять только из кириллических символов. Регистр не важен.";
    private final static String INVALID_PATRONYMIC =
            "Неверно введено отчество. Отчество должно состоять только из кириллических символов. Регистр не важен.";
    private final static String INVALID_PHONE_NUMBER =
            "Неверно введен номер телефона. Верный формат: (+7/8)9998887777.";
    private final static String INVALID_EMAIL =
            """
            Неверно введен email. Имя почтового ящика может состоять из латинских букв и цифр.
            Доменное имя может состоять только из латинских букв нижнего регистра.
            """;
    public final static String HELP =
            """
            ADD Фамилия Имя Отчество (+7/8)9998887777 example@example.example - добавить новый контакт.
            REMOVE example@example.example - удалить контакт по email.
            FINDALL - получить список всех сохраненных контактов.
            EXIT - завершить программу и сохранить изменения.
            """;

    private final static String NAME_REGEX = "[а-яА-Я]+";
    private final static String PHONE_NUMBER_REGEX = "((\\+7)|8)\\d{10}";
    private final static String EMAIL_REGEX = "(\\p{Alnum}+\\.?)+@\\p{Alnum}+\\.\\p{Lower}+";

    private final ContactService service;
    private final Scanner scanner;

    public Application(ContactService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            String request = scanner.nextLine().strip();
            String[] components = request.split("\\s+");
            String command = components[0];
            switch (command.toUpperCase()) {
                case "ADD":
                    if(isContactValid(components)) {
                        Contact contact = createContact(components);
                        System.out.println(service.save(contact) ? "Контакт сохранен" : "Контакт обновлен");
                    }
                    break;
                case "REMOVE": {
                    if(components.length == 2 && validateComponent(components[1], EMAIL_REGEX, INVALID_EMAIL)) {
                        Contact contact = new Contact();
                        contact.setEmail(components[1]);
                        System.out.println(service.remove(contact) ? "Контакт удален" : "Такого контакта нет");
                    }
                    break;
                }
                case "HELP": {
                    if(components.length == 1) {
                        System.out.println(HELP);
                        break;
                    }
                }
                case "FINDALL": {
                    if(components.length == 1) {
                        String response = service.findAll()
                                .stream()
                                .map(Contact::toString)
                                .reduce((c1, c2) -> String.join("\n", c1, c2))
                                .orElse("Нет сохраненных контактов");
                        System.out.println(response);
                        break;
                    }
                }
                case "EXIT": {
                    if(components.length == 1) {
                        isRunning = false;
                        break;
                    }
                }
                default: System.err.printf(UNKNOWN_COMMAND_FORMAT, request);
            }
        }
    }

    private Contact createContact(String[] components) {
        String firstName = normalizeName(components[2]);
        String lastName = normalizeName(components[1]);
        String patronymic = normalizeName(components[3]);
        return new Contact(firstName,
                lastName,
                patronymic,
                components[5],
                components[4]
                );
    }

    private String normalizeName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }


    private boolean isContactValid(String[] components) {
        if(components.length != 6) return false;
        boolean isFirstNameValid = validateComponent(components[2], NAME_REGEX, INVALID_FIRST_NAME);
        boolean isLastNameValid = validateComponent(components[1], NAME_REGEX, INVALID_LAST_NAME);
        boolean isPatronymicValid = validateComponent(components[3], NAME_REGEX, INVALID_PATRONYMIC);
        boolean isValidPhoneNumber = validateComponent(components[4], PHONE_NUMBER_REGEX, INVALID_PHONE_NUMBER);
        boolean isValidEmail = validateComponent(components[5], EMAIL_REGEX, INVALID_EMAIL);
        return isFirstNameValid && isLastNameValid && isPatronymicValid && isValidPhoneNumber && isValidEmail;
    }

    private boolean validateComponent(String component, String regex, String errorMessage) {
        if(component.matches(regex)) {
            return true;
        }
        System.err.println(errorMessage);
        return false;
    }
}
