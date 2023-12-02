package org.example.repository;

import org.example.model.Contact;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MemoryRepository implements ContactRepository {

    private final Set<Contact> contacts = new HashSet<>();

    @Override
    public boolean remove(Contact contact) {
        return contacts.remove(contact);
    }

    @Override
    public boolean save(Contact contact) {
        boolean isNew = true;
        if(contacts.contains(contact)) {
            contacts.remove(contact);
            isNew = false;
        }
        return contacts.add(contact) && isNew;
    }

    @Override
    public Set<Contact> findAll() {
        return Collections.unmodifiableSet(contacts);
    }
}
