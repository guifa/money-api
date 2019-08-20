package com.guifa.money.api.service.exception;

public class InactiveCustomerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InactiveCustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public InactiveCustomerException(String message) {
		super(message);
	}
	
	public InactiveCustomerException(Throwable cause) {
		super(cause);
	}
}
