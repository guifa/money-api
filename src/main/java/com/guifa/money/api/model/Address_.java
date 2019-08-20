package com.guifa.money.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> zipcode;
	public static volatile SingularAttribute<Address, String> number;
	public static volatile SingularAttribute<Address, String> addressComplement;
	public static volatile SingularAttribute<Address, String> address;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, String> neighborhood;
	public static volatile SingularAttribute<Address, String> state;
	public static volatile SingularAttribute<Address, Customer> customer;

	public static final String ZIPCODE = "zipcode";
	public static final String NUMBER = "number";
	public static final String ADDRESS_COMPLEMENT = "addressComplement";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String ID = "id";
	public static final String NEIGHBORHOOD = "neighborhood";
	public static final String STATE = "state";
	public static final String CUSTOMER = "customer";

}

