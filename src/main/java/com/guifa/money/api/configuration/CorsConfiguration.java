package com.guifa.money.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.guifa.money.api.configuration.properties.MoneyApiProperties;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {
	
	@Autowired
	MoneyApiProperties moneyApiProperties;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(moneyApiProperties.getSecurity().getAllowedOrigins())
				.allowCredentials(true)
				.maxAge(3600);
	}
}
