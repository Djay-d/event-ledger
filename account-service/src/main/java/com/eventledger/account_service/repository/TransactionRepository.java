package com.eventledger.account_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventledger.account_service.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}