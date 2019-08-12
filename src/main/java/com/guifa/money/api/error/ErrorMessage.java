package com.guifa.money.api.error;

public class ErrorMessage {
	
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
