package org.example.webproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @NonNull
    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    @OneToMany
    private List<Card> ownCards;

    @OneToMany
    private List<Card> cart;

    public void addCart(Card card) {
        cart.add(card);
    }



}