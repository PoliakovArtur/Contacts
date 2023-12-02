package org.example;

import org.example.application.Application;
import org.example.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class ApplicationRunner {
    private final static String WELCOME = """
            Добро пожаловать в приложение Контакты!
            Данное приложение умеет сохранять и удалять контакты.
            Если выбран профиль default, то контакты будут храниться в оперативной памяти,
            и когда вы из него выйдете, то данные не сохранятся.
            Если выбран профиль init, при вызове в конце команды EXIT, контакты будут сохранены во внутреннем файле приложения.
            При запуске приложения в данном профиле, из данного файла также подгружаются ранее сохраненные контакты.
            Чтобы посмотреть список доступных команд введите команду help""";

    public static void main(String[] args) {
        System.out.println(WELCOME);
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Application application = context.getBean(Application.class);
        application.start();
        context.getBeanFactory().destroySingletons();
    }
}