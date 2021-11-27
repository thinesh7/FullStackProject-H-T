package com.flightapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FlightServiceAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightServiceAwsApplication.class, args);
	}
	
}
