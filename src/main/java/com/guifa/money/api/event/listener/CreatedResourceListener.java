package com.guifa.money.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guifa.money.api.event.CreatedResourceEvent;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent>{

	@Override
	public void onApplicationEvent(CreatedResourceEvent createdResourceEvent) {
		HttpServletResponse response = createdResourceEvent.getResponse();
		Long resourceId = createdResourceEvent.getResourceId();
		
		addHeaderLocation(response, resourceId);
	}

	private void addHeaderLocation(HttpServletResponse response, Long resourceId) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(resourceId).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
