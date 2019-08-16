package com.guifa.money.api.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.guifa.money.api.event.CreatedResourceEvent;
import com.guifa.money.api.model.Customer;
import com.guifa.money.api.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	public Customer save(Customer customer, HttpServletResponse response) {
		applicationEventPublisher.publishEvent(new CreatedResourceEvent(this, response, customer.getId()));
		
		return customerRepository.save(customer);
	}
	
	public Customer findById(Long id) {
		Customer customer;
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		
		customer = optionalCustomer.orElseThrow(() -> new EmptyResultDataAccessException("No class " + Customer.class.getName() +  " entity with id " + id +  " exists!", 1));
		
		return customer;
	}
	
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
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
