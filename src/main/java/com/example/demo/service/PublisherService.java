package com.example.demo.service;


import com.example.demo.entity.Category;
import com.example.demo.entity.Publisher;
import com.example.demo.repo.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

     private final PublisherRepository publisherRepository;


    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }



    public Publisher addPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }


    public Publisher updatePublisher(Long id, Publisher publisher) {

        Optional<Publisher> existpublisher = publisherRepository.findById(id);

        if (existpublisher.isPresent()) {
            Publisher existing = existpublisher.get();

            existing.setName(publisher.getName());
            return publisherRepository.save(existing);

        }

        throw new RuntimeException("publisher not found id " + id);

    }


    public void deletepublisher(Long id) {

        publisherRepository.deleteById(id);

    }


    public Optional<Publisher> getPublisherById(Long id) {

        return publisherRepository.findById(id);

    }


    public List<Publisher> getAllPublisher() {

        return publisherRepository.findAll();
    }




}
