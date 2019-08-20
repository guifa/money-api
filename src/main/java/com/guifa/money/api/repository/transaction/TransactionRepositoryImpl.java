package com.guifa.money.api.repository.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.model.Transaction_;
import com.guifa.money.api.repository.filter.TransactionFilter;

public class TransactionRepositoryImpl implements TransactionRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Transaction> search(TransactionFilter transactionFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
		Root<Transaction> root = criteriaQuery.from(Transaction.class);
		
		Predicate[] restrictions = createRestrictions(transactionFilter, criteriaBuilder, root);
		criteriaQuery.where(restrictions);

		TypedQuery<Transaction> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	private Predicate[] createRestrictions(TransactionFilter transactionFilter, CriteriaBuilder criteriaBuilder,
			Root<Transaction> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!StringUtils.isEmpty(transactionFilter.getDescription())) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(Transaction_.DESCRIPTION)), "%" + transactionFilter.getDescription().toLowerCase() + "%"));
		}
		
		if (transactionFilter.getDueDateFrom() != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Transaction_.DUE_DATE), transactionFilter.getDueDateFrom()));
		}

		if (transactionFilter.getDueDateTo() != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Transaction_.DUE_DATE), transactionFilter.getDueDateTo()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
