package com.example.demo.controller;


import com.example.demo.entity.Publisher;
import com.example.demo.repo.PublisherRepository;
import com.example.demo.service.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publihser")
public class PublisherController {


    private final PublisherService publisherService;


    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @PostMapping("/add")
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherService.addPublisher(publisher);
    }


    @PutMapping("/{id}")
    public Publisher updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {

        return publisherService.updatePublisher(id, publisher);

    }


    @DeleteMapping("/{id}")
    public void deletepublisher(@PathVariable Long id) {

        publisherService.deletepublisher(id);

    }


    @GetMapping("/{id}")
    public Optional<Publisher> getPublisherById(@PathVariable Long id) {

        return publisherService.getPublisherById(id);

    }

@GetMapping("")
    public List<Publisher> getAllPublisher() {

        return publisherService.getAllPublisher();
    }

}
