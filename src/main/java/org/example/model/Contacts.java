package org.example.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Component
public class Contacts {
    @Value("${contacts.source}")
    private final String path;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final Set<User> users = new HashSet<>();

    public Contacts(String path) throws FileNotFoundException {
        this.path = path;
        File contacts = new File(path);
        this.reader = new BufferedReader(new FileReader(contacts));
        this.writer = new PrintWriter(contacts);
    }

    public void scan() {
        try {
            while (reader.read() != -1) {
                String contact = reader.readLine();
                addContact(contact);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void addContact(String contact) {
    }
}
