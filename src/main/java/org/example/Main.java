package org.example;

import org.example.model.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.example.model");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.next();
        }
    }
}