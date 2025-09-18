package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "system_users")
@Getter
@Setter
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password; // should be encrypted (BCrypt)

    private String full_name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private LocalDateTime created_at = LocalDateTime.now();

    public enum Role {
        ADMIN, LIBRARIAN, STAFF
    }


    @OneToMany(mappedBy = "processedBy")
    Set<BorrowTransaction> borrowTransactions = new HashSet<>();

}
