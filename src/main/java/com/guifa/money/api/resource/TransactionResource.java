package com.guifa.money.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guifa.money.api.error.ErrorMessage;
import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.repository.filter.TransactionFilter;
import com.guifa.money.api.service.TransactionService;
import com.guifa.money.api.service.exception.InactiveCustomerException;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping
	public ResponseEntity<List<Transaction>> search(TransactionFilter transactionFilter) {
		List<Transaction> transactions = transactionService.search(transactionFilter);
		
		return ResponseEntity.ok(transactions);
	}
	
	@PostMapping
	public ResponseEntity<Transaction> save(@Valid @RequestBody Transaction transaction, HttpServletResponse response) {
		Transaction savedTransaction = transactionService.save(transaction, response);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> findById(@PathVariable Long id) {
		Transaction transaction = transactionService.findById(id);
		
		return ResponseEntity.ok(transaction);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		transactionService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Transaction> update(@Valid @RequestBody Transaction transaction, Long id) {
		Transaction transactionToUpdate = transactionService.update(transaction, id);
		
		return ResponseEntity.ok(transactionToUpdate);
	}
	
	@ExceptionHandler({InactiveCustomerException.class})
	public ResponseEntity<List<ErrorMessage>> handleInactiveStatusCustomerException(InactiveCustomerException ex){
		String userMessage = ex.getMessage();
		String debugMessage = ex.toString();
		List<ErrorMessage> errorMessages = Arrays.asList(new ErrorMessage(userMessage, debugMessage));
		
		return ResponseEntity.badRequest().body(errorMessages);
	}
	
	
}
