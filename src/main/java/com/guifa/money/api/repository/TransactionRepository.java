package com.guifa.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.repository.transaction.TransactionRepositoryQuery;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryQuery {

}
