package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Category parent;  // انا دلوقتي عايز الفورن كي بتاع نوع الفءه  فلازم يكون ف نفس الجدول يعني من الكاتجوري وغير كده فلازم يكون الماللك علشان اقدر احط الفورن كي فالعلاقه هتبقي مني ل ون


    @OneToMany(mappedBy = "parent")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)// because prevent subcategories[]
    @JsonIgnore
    // وطالما منها فيها هقول الفءه الواحده ليها فروع  وطالما مش هي الميني مش هتبقي ال  owner
    Set<Category> subcategories = new HashSet<>();


    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    Set<Book> books = new HashSet<>();


}
