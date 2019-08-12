package com.guifa.money.api.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class ErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	List<ErrorMessage> errorMessages;
	
	public ErrorHandler() {
		errorMessages = new ArrayList<ErrorMessage>();
	}
	
	public List<ErrorMessage> createErrorMessages(BindingResult bindingResult) {
		errorMessages = new ArrayList<ErrorMessage>();
		String userMessage = "";
		String debugMessage = "";
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			debugMessage = fieldError.toString();
			errorMessages.add(new ErrorMessage(userMessage, debugMessage));
		}
		
		return errorMessages;
	}
}
