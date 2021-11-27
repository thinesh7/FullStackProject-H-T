package com.flightapp.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UsersConfiguration {

	@Bean
	@LoadBalanced
	public RestTemplate getRestAPITemplate() {
		return new RestTemplate();
	}
}
