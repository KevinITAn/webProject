package org.example.webproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class User {

    @Id
    @Generated
    private long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String passwordConfirm;

}
