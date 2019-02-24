package com.sumant.boot.learning;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {
	
	private final RestTemplate template;
	private final String url;
	
	public TestService(
			RestTemplate template, 
			String url
			) {
		this.url = url;
		this.template = template;
	}

	public String callRestEndpoint() {
		
		return template.getForObject(url + "/pc", String.class);
		
	}

	
}
