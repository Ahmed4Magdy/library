package com.example.demo.service;


import com.example.demo.entity.Author;
import com.example.demo.repo.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {


    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Author addAuthor(Author author) {

        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author author) {

        Optional<Author> existAuthor = authorRepository.findById(id);

        if (existAuthor.isPresent()) {
            Author existing = existAuthor.get();

            existing.setName(author.getName());
            return authorRepository.save(existing);
        }

        throw new RuntimeException("Author not found id " + id);
    }


    public Optional<Author> findById(Long id) {

        return authorRepository.findById(id);

    }


    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }



    public List<Author> findAll(){
        return authorRepository.findAll();
    }


}
