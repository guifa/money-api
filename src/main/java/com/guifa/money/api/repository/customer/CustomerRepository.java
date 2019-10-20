package com.guifa.money.api.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifa.money.api.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
