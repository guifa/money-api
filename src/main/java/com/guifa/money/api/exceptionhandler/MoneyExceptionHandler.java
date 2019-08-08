package com.guifa.money.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MoneyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	MessageSource messageSource;
	
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
		List<ErrorMessage> errorMessages = createErrorMessages(ex.getBindingResult());
		
		return handleExceptionInternal(ex, errorMessages, headers, status, request);
	}
	
	private List<ErrorMessage> createErrorMessages(BindingResult bindingResult) {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		String userMessage = "";
		String debugMessage = "";
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			debugMessage = fieldError.toString();
			errorMessages.add(new ErrorMessage(userMessage, debugMessage));
		}
		
		return errorMessages;
	}
	
	public static class ErrorMessage {
		
		private String userMessage;
		private String debugMessage;
		
		public ErrorMessage(String userMessage, String debugMessage) {
			this.userMessage = userMessage;
			this.debugMessage = debugMessage;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public String getDebugMessage() {
			return debugMessage;
		}
		
	}

}
