package com.guifa.money.api.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false); //TODO: will be true in production
		cookie.setPath(httpServletRequest.getContextPath() + "ouath/token");
		cookie.setMaxAge(0);
		
		httpServletResponse.addCookie(cookie);
		httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
	}
	
}