package com.sumant.boot.learning;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {
	
	private final RestTemplate template;
	
	public TestService(RestTemplateBuilder builder) {
		this.template = builder.build();
	}

	public String callRestEndpoint() {
		
		return template.getForObject("/pc", String.class);
		
	}

	
}
