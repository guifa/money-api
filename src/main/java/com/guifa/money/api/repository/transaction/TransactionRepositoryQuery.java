package com.guifa.money.api.repository.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.repository.filter.TransactionFilter;

public interface TransactionRepositoryQuery {

	public Page<Transaction> search(TransactionFilter filter, Pageable pageable);
}
