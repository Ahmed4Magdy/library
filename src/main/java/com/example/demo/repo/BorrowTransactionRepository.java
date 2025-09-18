package com.example.demo.repo;

import com.example.demo.entity.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction,Long> {
}
