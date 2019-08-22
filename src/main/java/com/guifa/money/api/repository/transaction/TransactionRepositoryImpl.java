package com.guifa.money.api.repository.transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.repository.filter.TransactionFilter;
import com.guifa.money.api.repository.predicate.transaction.TransactionSearchPredicate;

public class TransactionRepositoryImpl implements TransactionRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Transaction> search(TransactionFilter transactionFilter, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
		Root<Transaction> root = criteriaQuery.from(Transaction.class);
		TransactionSearchPredicate transactionSearchPredicate = new TransactionSearchPredicate(transactionFilter, criteriaBuilder, root);
		
		Predicate[] restrictions = transactionSearchPredicate.createRestrictions();
		criteriaQuery.where(restrictions);

		TypedQuery<Transaction> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		typedQuery.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, total(transactionFilter));
	}
	
	private Long total(TransactionFilter transactionFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Transaction> root = criteriaQuery.from(Transaction.class);
		TransactionSearchPredicate transactionSearchPredicate = new TransactionSearchPredicate(transactionFilter, criteriaBuilder, root);
		
		Predicate[] restrictions = transactionSearchPredicate.createRestrictions();
		criteriaQuery.where(restrictions);
		criteriaQuery.select(criteriaBuilder.count(root));
		
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

}
