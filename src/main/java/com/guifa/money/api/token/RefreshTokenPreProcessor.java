package com.guifa.money.api.token;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenPreProcessor implements Filter {
	
	static final String OAUTH_URI = "/oauth/token";
	static final String PARAMETER_GRANT_TYPE = "grant_type";
	static final String PARAMETER_NAME = "refresh_token";
	static final String COOKIE_NAME = "refreshToken";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		if (httpServletRequest.getRequestURI().equalsIgnoreCase(OAUTH_URI)
				&& httpServletRequest.getParameter(PARAMETER_GRANT_TYPE).equalsIgnoreCase(PARAMETER_NAME)
				&& httpServletRequest.getCookies() != null) {
			for (Cookie cookie : httpServletRequest.getCookies()) {
				if (cookie.getName().equals(COOKIE_NAME)) {
					String refreshToken = cookie.getValue();
					httpServletRequest = new RefreshTokenServletRequestWarpper(httpServletRequest, refreshToken);
				}
			}
		}		
		
		chain.doFilter(httpServletRequest, response);
	}

}
