package com.guifa.money.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guifa.money.api.model.Customer;
import com.guifa.money.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerResource {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	@PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_VIEWER')) and #oauth2.hasScope('read')")
	public ResponseEntity<List<Customer>> findAll() {
		List<Customer> customers = customerService.findAll();
		
		return ResponseEntity.ok(customers);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer, HttpServletResponse response) {
		Customer savedCustomer = customerService.save(customer, response);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_VIEWER')) and #oauth2.hasScope('read')")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Customer customer = customerService.findById(id);
		
		return ResponseEntity.ok(customer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public void deleteById(@PathVariable Long id) {
		customerService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<Customer> update(@PathVariable Long id, @Valid @RequestBody Customer customer) {
		Customer customerToUpdate = customerService.update(customer, id);
		
		return ResponseEntity.ok(customerToUpdate);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public void updateActiveStatus(@PathVariable Long id, @RequestBody Boolean activeStatus) {
		customerService.updateActiveStatus(id, activeStatus);
	}

}
