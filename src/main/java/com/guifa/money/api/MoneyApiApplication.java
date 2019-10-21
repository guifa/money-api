package com.guifa.money.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.guifa.money.api.configuration.properties.MoneyApiProperties;

@SpringBootApplication
@EnableConfigurationProperties(MoneyApiProperties.class)
public class MoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyApiApplication.class, args);
	}

}
