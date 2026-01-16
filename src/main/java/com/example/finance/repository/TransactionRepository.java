package com.example.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finance.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction , Long> {

}
