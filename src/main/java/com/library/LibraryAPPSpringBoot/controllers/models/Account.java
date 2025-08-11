package com.library.LibraryAPPSpringBoot.controllers.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(name="account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(name="username")
    @NotEmpty(message = "username should not be empty")
    //TODO Validator for unique name
    private String userName;

    @Column(name="password")
    @NotEmpty(message = "password should not be empty")
    private String password;
}
