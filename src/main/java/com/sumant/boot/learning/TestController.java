package com.sumant.boot.learning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private TestService service;
	
	public TestController( TestService service ) {
		this.service = service;
	}
	
	@GetMapping("/")
	public String initiateServiceCall() {
		return service.callRestEndpoint();
	}
	
}
