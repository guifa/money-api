package com.guifa.money.api.repository.transaction;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.repository.filter.TransactionFilter;
import com.guifa.money.api.repository.predicate.transaction.TransactionSearchPredicate;

public class TransactionRepositoryImpl implements TransactionRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Transaction> search(TransactionFilter transactionFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
		Root<Transaction> root = criteriaQuery.from(Transaction.class);
		TransactionSearchPredicate transactionSearchPredicate = new TransactionSearchPredicate(transactionFilter, criteriaBuilder, root);
		
		Predicate[] restrictions = transactionSearchPredicate.createRestrictions();
		criteriaQuery.where(restrictions);

		TypedQuery<Transaction> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
