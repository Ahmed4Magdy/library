package com.example.demo.controller;

import com.example.demo.Dto.BorrowTransactionRequest;
import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowTransaction;
import com.example.demo.entity.Member;
import com.example.demo.entity.SystemUser;
import com.example.demo.service.BorrowTransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/borrowwtrasaction")
public class BorrowTransactionController {

    private final BorrowTransactionService borrowTransactionService;

    public BorrowTransactionController(BorrowTransactionService borrowTransactionService) {
        this.borrowTransactionService = borrowTransactionService;
    }


    @PostMapping("/add")
    public BorrowTransaction create(@RequestBody BorrowTransactionRequest request) {


        return borrowTransactionService.createBorrowTransaction(request);


    }


    @PutMapping("/{id}")
    public BorrowTransaction updateBorrowTransaction(@PathVariable Long id, @RequestBody BorrowTransaction borrow) {

        return borrowTransactionService.updateBorrowTransaction(id, borrow);


    }


    @GetMapping("/{id}")
    public Optional<BorrowTransaction> findByIdBorrowTransaction(@PathVariable Long id) {

        return borrowTransactionService.findByIdBorrowTransaction(id);

    }


}
