package com.guifa.money.api.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "money-api")
public class MoneyApiProperties {
	
	private final Security security = new Security();

	public Security getSecurity() {
		return security;
	}
	
	public static class Security {
		
		private boolean cookieSecure;
		
		private String allowedOrigins = "http://localhost:8000";

		public boolean isCookieSecure() {
			return cookieSecure;
		}

		public void setCookieSecure(boolean cookieSecure) {
			this.cookieSecure = cookieSecure;
		}

		public String getAllowedOrigins() {
			return allowedOrigins;
		}

		public void setAllowedOrigins(String allowedOrigins) {
			this.allowedOrigins = allowedOrigins;
		}

	}
	
}
