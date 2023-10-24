package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Scope("prototype")
@Getter
public class User {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String email;
    private final String phoneNumber;

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof User user)) return false;
        return this.email.equals(user.email);
    }
}
