package com.lananakai.cardgame;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Represents a user account with a unique username and a hashed password.
// Named AppUser (table app_user) because "user" is a reserved word in SQL.
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    private String id;
    @Column(unique = true, nullable = false)
    private String username;
    // BCrypt hash of the password, never the password itself
    @Column(nullable = false)
    private String passwordHash;

    // EFFECTS: empty constructor for JPA to fill in fields when loading from the database
    protected AppUser() {
    }

    /*
     * REQUIRES: username has a non-zero length; passwordHash is already hashed
     * EFFECTS: creates a user with a random id, the given username and password hash
     */
    public AppUser(String username, String passwordHash) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // getters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
