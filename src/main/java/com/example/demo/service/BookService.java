package com.example.demo.service;


import com.example.demo.Dto.BookRequest;
import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.entity.Publisher;
import com.example.demo.repo.AuthorRepository;
import com.example.demo.repo.BookRepository;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository,
                       PublisherRepository publisherRepository,
                       AuthorRepository authorRepository,
                       CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }


    public Book addbook(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setEdition(request.getEdition());
        book.setLanguage(request.getLanguage());
        book.setPublicationYear(request.getPublicationYear());
        book.setSummary(request.getSummary());
        book.setCoverImage(request.getCoverImageUrl());
        // request.getpushihid معناه ان اقيمه ال حطيتها بتاعت واحد هروح اشوفها موجوده ولا لا علشان لو موجوده هحطها ف البوك
        Publisher publisher = publisherRepository.findById(request.getPublisherId()).orElseThrow(() -> new RuntimeException("Publisher not found"));
        book.setPublisher(publisher);
        book.setAuthors(new HashSet<>(authorRepository.findAllById(request.getAuthorIds())));

        // Categories
        book.setCategories(new HashSet<>(categoryRepository.findAllById(request.getCategoryIds())));


        return bookRepository.save(book);

    }


    public Book updateBook(Long id, Book book) {

        Optional<Book> existbook = bookRepository.findById(id);

        if (existbook.isPresent()) {
            Book existing = existbook.get();

            existing.setTitle(book.getTitle());
            existing.setIsbn(book.getIsbn());
            existing.setEdition(book.getEdition());
            existing.setLanguage(book.getLanguage());
            existing.setLanguage(book.getLanguage());
            existing.setPublicationYear(book.getPublicationYear());
            existing.setSummary(book.getSummary());
            existing.setCoverImage(book.getCoverImage());
            existing.setPublisher(book.getPublisher());
            existing.setAuthors(book.getAuthors());
            existing.setCategories(book.getCategories());
            return bookRepository.save(existing);

        }

        throw new RuntimeException("book not found id " + id);

    }


    public void deleteByBook(Long id) {

        bookRepository.deleteById(id);

    }


    public Optional<Book> getbookyById(Long id) {

        return bookRepository.findById(id);

    }


    public List<Book> getAllBook() {

        return bookRepository.findAll();
    }


}
