package com.example.demo.Dto;


import com.example.demo.entity.Book;
import com.example.demo.entity.Member;
import com.example.demo.entity.SystemUser;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BorrowTransactionRequest {

    private Long bookId;

    private Long memberId;


    private Long processedById;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

}
