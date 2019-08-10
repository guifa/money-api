package com.guifa.money.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class CreatedResourceEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Long resourceId;
	
	public CreatedResourceEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.resourceId = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getResourceId() {
		return resourceId;
	}

}
