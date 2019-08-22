package com.guifa.money.api.repository.predicate.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.model.Transaction_;
import com.guifa.money.api.repository.filter.TransactionFilter;
import com.guifa.money.api.repository.predicate.PredicateCreator;

public class TransactionSearchPredicate extends TransactionPredicate implements PredicateCreator{

	public TransactionSearchPredicate(TransactionFilter transactionFilter, CriteriaBuilder criteriaBuilder,
			Root<Transaction> root) {
		super(transactionFilter, criteriaBuilder, root);
	}

	@Override
	public Predicate[] createRestrictions() {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!StringUtils.isEmpty(getTransactionFilter().getDescription())) {
			predicates.add(getCriteriaBuilder().like(getCriteriaBuilder().lower(getRoot().get(Transaction_.DESCRIPTION)), "%" + getTransactionFilter().getDescription().toLowerCase() + "%"));
		}
		
		if (getTransactionFilter().getDueDateFrom() != null) {
			predicates.add(getCriteriaBuilder().greaterThanOrEqualTo(getRoot().get(Transaction_.DUE_DATE), getTransactionFilter().getDueDateFrom()));
		}

		if (getTransactionFilter().getDueDateTo() != null) {
			predicates.add(getCriteriaBuilder().lessThanOrEqualTo(getRoot().get(Transaction_.DUE_DATE), getTransactionFilter().getDueDateTo()));
		}
		
		if (getTransactionFilter().getAmountFrom() != null) {
			predicates.add(getCriteriaBuilder().greaterThanOrEqualTo(getRoot().get(Transaction_.AMOUNT), getTransactionFilter().getAmountFrom()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
