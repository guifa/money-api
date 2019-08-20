package com.guifa.money.api.repository.transaction;

import java.util.List;

import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.repository.filter.TransactionFilter;

public interface TransactionRepositoryQuery {

	public List<Transaction> search(TransactionFilter filter);
}
