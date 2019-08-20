package com.guifa.money.api.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.guifa.money.api.event.CreatedResourceEvent;
import com.guifa.money.api.model.Customer;
import com.guifa.money.api.model.Transaction;
import com.guifa.money.api.repository.TransactionRepository;
import com.guifa.money.api.service.exception.InactiveCustomerException;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MessageSource messageSource;
	
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}
	
	public Transaction save(Transaction transaction, HttpServletResponse response) {
		Customer customer = customerService.findById(transaction.getCustomer().getId());
		
		if (customer.isInactive()) {
			throw new InactiveCustomerException(messageSource.getMessage("customer.isInactive", null, LocaleContextHolder.getLocale()));
		}
		
		Transaction savedTransaction = transactionRepository.save(transaction);

		applicationEventPublisher.publishEvent(new CreatedResourceEvent(this, response, savedTransaction.getId()));
		
		return savedTransaction;
	}
	
	public Transaction findById(Long id) {
		Transaction transaction;
		Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
		
		transaction = optionalTransaction.orElseThrow(() -> new EmptyResultDataAccessException("No class " + Transaction.class.getName() +  " entity with id " + id +  " exists!", 1));
		
		return transaction;
	}
	
	public void deleteById(Long id) {
		transactionRepository.deleteById(id);
	}
	
	public Transaction update(Transaction transaction, Long id) {
		Transaction transactionToUpdate = findById(id);
		
		BeanUtils.copyProperties(transaction, transactionToUpdate, "id");
		
		return transactionRepository.save(transactionToUpdate);
	}
}
