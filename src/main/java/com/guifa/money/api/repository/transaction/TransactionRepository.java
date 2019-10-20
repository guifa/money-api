package com.guifa.money.api.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifa.money.api.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryQuery {

}
