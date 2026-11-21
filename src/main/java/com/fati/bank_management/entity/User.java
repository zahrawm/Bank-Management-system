package com.fati.bank_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity                      // Hibernate will make a table from this class
@Table(name = "users")       // the table is called "users"
public class User {

    @Id                                                   // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Postgres auto-increments it
    private Long id;

    @Column(nullable = false)                             // cannot be empty
    private String fullName;

    @Column(nullable = false, unique = true)              // cannot be empty, no duplicates
    private String email;

    private LocalDateTime createdAt;

    // Hibernate needs an empty constructor to build objects from database rows
    public User() {}

    // @PrePersist 	What happens before an entity is first saved
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}