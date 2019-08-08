package com.guifa.money.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
		
		return handleExceptionInternal(ex, new ErrorMessage(userMessage, debugMessage), headers, status, request);
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
