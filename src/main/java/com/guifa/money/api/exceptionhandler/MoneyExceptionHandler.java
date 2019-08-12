package com.guifa.money.api.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.guifa.money.api.error.ErrorHandler;
import com.guifa.money.api.error.ErrorMessage;

@ControllerAdvice
public class MoneyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	ErrorHandler errorHandler;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String userMessage = messageSource.getMessage("bad.json", null, LocaleContextHolder.getLocale());
		String debugMessage = ex.getMessage();
		List<ErrorMessage> errorMessages = Arrays.asList(new ErrorMessage(userMessage, debugMessage));
		
		return handleExceptionInternal(ex, errorMessages, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErrorMessage> errorMessages = errorHandler.createErrorMessages(ex.getBindingResult());
		
		return handleExceptionInternal(ex, errorMessages, headers, status, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,  WebRequest request) {
		String resourceName = getResourceNameFromEmptyResultDataAccessException(ex);
		String userMessage = messageSource.getMessage("resource.notFound", new Object[] {resourceName}, LocaleContextHolder.getLocale());
		String debugMessage = ex.getMessage();
		List<ErrorMessage> errorMessages = Arrays.asList(new ErrorMessage(userMessage, debugMessage));
		
		return handleExceptionInternal(ex, errorMessages, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
//	TODO: Refactor into an interface ResourceExtractor with getResourceNameFromException() method, this method in this class go against the Single Responsibility Principle
	private String getResourceNameFromEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
		final int FULL_CLASS_NAME_LOCATION = 2;
		String fullClassName = ex.getMessage().split(" ")[FULL_CLASS_NAME_LOCATION];
		String[] fullClassNameDotSplited = fullClassName.split("\\.");
		String resourceName = fullClassNameDotSplited[fullClassNameDotSplited.length - 1];
		return resourceName;
	}

}
