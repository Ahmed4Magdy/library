package com.example.demo.service;


import com.example.demo.Dto.BorrowTransactionRequest;
import com.example.demo.entity.*;
import com.example.demo.repo.BookRepository;
import com.example.demo.repo.BorrowTransactionRepository;
import com.example.demo.repo.MemberRepository;
import com.example.demo.repo.SystemUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowTransactionService {


    private final BorrowTransactionRepository transactionRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final SystemUserRepository systemUserRepository;

    public BorrowTransactionService(BorrowTransactionRepository transactionRepository, MemberRepository memberRepository, BookRepository bookRepository, SystemUserRepository systemUserRepository) {
        this.transactionRepository = transactionRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.systemUserRepository = systemUserRepository;
    }

    public BorrowTransaction createBorrowTransaction(BorrowTransactionRequest request) {


        BorrowTransaction borrowTransaction = new BorrowTransaction();

        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        borrowTransaction.setBook(book);

        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new RuntimeException("Member not found"));
        borrowTransaction.setMember(member);


        SystemUser systemUser = systemUserRepository.findById(request.getProcessedById()).orElseThrow(() -> new RuntimeException("system_user not found"));
        borrowTransaction.setProcessedBy(systemUser);

        borrowTransaction.setBorrowDate(request.getBorrowDate());
        borrowTransaction.setDueDate(request.getDueDate());
        borrowTransaction.setReturnDate(request.getReturnDate());


        return transactionRepository.save(borrowTransaction);

    }



    public BorrowTransaction updateBorrowTransaction(Long id, BorrowTransaction borrow) {

        Optional<BorrowTransaction> existborrowtransactio = transactionRepository.findById(id);

        if (existborrowtransactio.isPresent()) {
            BorrowTransaction existing = existborrowtransactio.get();

            existing.setBook(borrow.getBook());
            existing.setMember(borrow.getMember());
            existing.setProcessedBy(borrow.getProcessedBy());
            existing.setBorrowDate(borrow.getBorrowDate());
            existing.setDueDate(borrow.getDueDate());
            existing.setReturnDate(borrow.getReturnDate());

            return transactionRepository.save(existing);

        }

        throw new RuntimeException("borrowtransaction not found id " + id);

    }






}
