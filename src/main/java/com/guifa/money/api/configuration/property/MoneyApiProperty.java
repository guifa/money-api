package com.guifa.money.api.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "money-api")
public class MoneyApiProperty {
	
	private final Security security = new Security();

	public Security getSecurity() {
		return security;
	}
	
	public static class Security {
		
		private boolean cookieSecure;

		public boolean isCookieSecure() {
			return cookieSecure;
		}

		public void setCookieSecure(boolean cookieSecure) {
			this.cookieSecure = cookieSecure;
		}
		
	}
	
}
