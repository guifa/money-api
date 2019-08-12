package com.guifa.money.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.guifa.money.api.model.Customer;
import com.guifa.money.api.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findById(Long id) {
		Customer customer;
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		
		if (optionalCustomer.isPresent()) {
			customer = optionalCustomer.get();
		} else {
			throw new EmptyResultDataAccessException("No class " + Customer.class.getName() +  " entity with id " + id +  " exists!", 1);
		}
		
		return customer;
	}

	public Customer update(Customer customer, Long id) {
		Customer customerToUpdate = findById(id);
		
		BeanUtils.copyProperties(customer, customerToUpdate, "id");
		
		return customerRepository.save(customerToUpdate);
	}

	public void updateActiveStatus(Long id, Boolean activeStatus) {
		Customer customerToUpdate = findById(id);
		
		customerToUpdate.setActive(activeStatus);		
		customerRepository.save(customerToUpdate);
	}

}
