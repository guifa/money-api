package com.guifa.money.api.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();		
		String refreshTokenValue = body.getRefreshToken().getValue();
		
		Cookie refreshToken = new Cookie("refreshToken", refreshTokenValue);
		configureRefreshTokenCookie(refreshToken, httpServletRequest);
		addCookieToResponse(refreshToken, httpServletResponse);
		removeRefreshTokenFromBody(body);
		
		return body;
	}

	private void removeRefreshTokenFromBody(OAuth2AccessToken body) {
		DefaultOAuth2AccessToken newBody = (DefaultOAuth2AccessToken) body;
		
		newBody.setRefreshToken(null);
	}

	private void addCookieToResponse(Cookie cookie, HttpServletResponse httpServletResponse) {
		httpServletResponse.addCookie(cookie);
	}

	private void configureRefreshTokenCookie(Cookie refreshTokenCookie, HttpServletRequest httpServletRequest) {
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(false); // TODO: Needs to be true in production
		refreshTokenCookie.setPath(httpServletRequest.getContextPath() + "/oauth/token");
		refreshTokenCookie.setMaxAge(3600 * 24 * 30);
	}

}
