package com.guifa.money.api.repository.predicate.transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.repository.filter.TransactionFilter;

public class TransactionPredicate {
	
	private TransactionFilter transactionFilter;
	
	private CriteriaBuilder criteriaBuilder;
	
	private Root<Transaction> root;
	
	public TransactionPredicate(TransactionFilter transactionFilter, CriteriaBuilder criteriaBuilder, Root<Transaction> root) {
		this.transactionFilter = transactionFilter;
		this.criteriaBuilder = criteriaBuilder;
		this.root = root;
	}

	public TransactionFilter getTransactionFilter() {
		return transactionFilter;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}

	public Root<Transaction> getRoot() {
		return root;
	}
	
	
}
