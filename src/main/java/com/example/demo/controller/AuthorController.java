package com.example.demo.controller;


import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {


    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }



    @PostMapping("/add")
    public Author addAuthor(@RequestBody Author author) {

        return authorService.addAuthor(author);
    }



    @GetMapping("/{id}")
    public Optional<Author> findById(@PathVariable Long id) {

        return authorService.findById(id);

    }



    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id,@RequestBody Author author) {

        return authorService.updateAuthor(id, author);

    }


    @DeleteMapping("")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }


    @GetMapping("")
    public List<Author> findAll() {
        return authorService.findAll();
    }

}
