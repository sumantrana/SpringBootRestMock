package com.sumant.boot.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRestMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestMockApplication.class, args);
	}

//	@Autowired
//	private RestTemplateBuilder builder;
//
//	@Bean
//	public TestService getTestService() {
//		return new TestService(builder);
//	}
}
