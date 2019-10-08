package com.guifa.money.api.token;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;

public class RefreshTokenServletRequestWarpper extends HttpServletRequestWrapper {
	
	private String refreshToken;
	
	public RefreshTokenServletRequestWarpper(HttpServletRequest request, String refreshToken) {
		super(request);
		this.refreshToken = refreshToken;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		ParameterMap<String, String[]>  parameterMap = new ParameterMap<>(getRequest().getParameterMap());		
		parameterMap.put("refresh_token", new String[] {refreshToken});
		parameterMap.setLocked(true);
		
		return parameterMap;
	}
}
