package com.guifa.money.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guifa.money.api.event.CreatedResourceEvent;
import com.guifa.money.api.model.Customer;
import com.guifa.money.api.repository.CustomerRepository;
import com.guifa.money.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerResource {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@GetMapping
	public ResponseEntity<List<Customer>> list() {
		List<Customer> customers = customerRepository.findAll();
		
		return ResponseEntity.ok(customers);
	}
	
	@PostMapping
	public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer, HttpServletResponse response) {
		Customer savedCustomer = customerRepository.save(customer);
		
		applicationEventPublisher.publishEvent(new CreatedResourceEvent(this, response, savedCustomer.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Customer customer = customerService.findById(id);
		
		return ResponseEntity.ok(customer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		customerRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id, @Valid @RequestBody Customer customer) {
		Customer customerToUpdate = customerService.update(customer, id);
		
		return ResponseEntity.ok(customerToUpdate);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateActiveStatus(@PathVariable Long id, @RequestBody Boolean activeStatus) {
		customerService.updateActiveStatus(id, activeStatus);
	}

}
