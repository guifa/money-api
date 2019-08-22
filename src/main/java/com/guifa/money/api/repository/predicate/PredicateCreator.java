package com.guifa.money.api.repository.predicate;

import javax.persistence.criteria.Predicate;

public interface PredicateCreator {
	
	public Predicate[] createRestrictions();
}
