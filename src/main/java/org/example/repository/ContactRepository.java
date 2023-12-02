package org.example.repository;

import org.example.model.Contact;

import java.util.Set;

public interface ContactRepository {

   boolean remove(Contact contact);
   boolean save(Contact contact);
   Set<Contact> findAll();
}
