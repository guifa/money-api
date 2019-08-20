package com.guifa.money.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, Address> address;
	public static volatile SingularAttribute<Customer, Boolean> activeStatus;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, Long> id;

	public static final String ADDRESS = "address";
	public static final String ACTIVE_STATUS = "activeStatus";
	public static final String NAME = "name";
	public static final String ID = "id";

}

