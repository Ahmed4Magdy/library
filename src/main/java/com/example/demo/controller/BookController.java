package com.example.demo.controller;

import com.example.demo.Dto.BookRequest;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {


    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/add")
    public Book addbook(@RequestBody BookRequest request) {
        return bookService.addbook(request);
    }


    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {

        return bookService.updateBook(id, book);


    }


    @DeleteMapping("/{id}")
    public void deleteByBook(@PathVariable Long id) {

        bookService.deleteByBook(id);

    }


    @GetMapping("/{id}")
    public Optional<Book> getbookyById(@PathVariable Long id) {

        return bookService.getbookyById(id);

    }


    @GetMapping("")
    public List<Book> getAllBook() {

        return bookService.getAllBook();
    }


}
