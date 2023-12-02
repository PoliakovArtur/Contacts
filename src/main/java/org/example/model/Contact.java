package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contact {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phoneNumber;

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Contact contact)) return false;
        return this.email.equals(contact.email);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", lastName, firstName, patronymic, phoneNumber, email);
    }
}
