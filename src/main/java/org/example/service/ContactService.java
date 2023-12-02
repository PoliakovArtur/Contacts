package org.example.service;

import org.example.model.Contact;
import org.example.repository.ContactRepository;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ContactService {
    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public boolean save(Contact contact) {
        if(contact == null ||
                contact.getFirstName() == null ||
                contact.getLastName() == null ||
                contact.getPatronymic() == null ||
                contact.getPhoneNumber() == null ||
                contact.getEmail() == null)
            throw new ServiceException("not enough data to save contact");
        return repository.save(contact);
    }

    public boolean remove(Contact contact) {
        return repository.remove(contact);
    }

    public Set<Contact> findAll() {
        return repository.findAll();
    }


}
