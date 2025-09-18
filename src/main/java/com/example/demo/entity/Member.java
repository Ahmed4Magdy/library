package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String full_name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String address;

    private LocalDate membership_date = LocalDate.now();


    // One Member can borrow many transactions
    @OneToMany(mappedBy = "member")  // member no owner because no have foreign key
    @JsonIgnore
    private Set<BorrowTransaction> transactions = new HashSet<>();


}
