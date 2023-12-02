package org.example.repository;

import org.example.model.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.*;

@Repository
@Primary
@PropertySource("classpath:application-init.properties")
@Profile("init")
public class FileRepository implements ContactRepository {

    @Value("${contacts.source}")
    private String filePath;
    private BufferedReader reader;
    private ContactRepository contactRepository;

    public FileRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostConstruct
    public void init() {
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException ex) {
            throw new DBException("invalid path " + filePath + " file not found", ex);
        }
        load();
    }

    private void load() {
        String[] currentLine = {""};
        try {
            reader.lines().forEach(l -> {
                currentLine[0] = l;
                String[] components = l.split(" ");
                Contact contact = new Contact();
                contact.setFirstName(components[1]);
                contact.setLastName(components[0]);
                contact.setPatronymic(components[2]);
                contact.setPhoneNumber(components[3]);
                contact.setEmail(components[4]);
                contactRepository.save(contact);
            });
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new DBException("illegal data in db file: " + currentLine[0], ex);
        }
    }

    @Override
    public boolean remove(Contact contact) {
        return contactRepository.remove(contact);
    }

    @Override
    public boolean save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Set<Contact> findAll() {
        return contactRepository.findAll();
    }

    @PreDestroy
    public void flushAndClose() {
        try(PrintWriter writer = new PrintWriter(filePath); BufferedReader reader = this.reader) {
        writer.write(findAll()
                    .stream()
                    .map(Contact::toString)
                    .reduce((s1, s2) -> String.join("\n", s1, s2))
                    .orElse(""));
        } catch (IOException ex) {
            throw new DBException(ex);
        }
    }
}
