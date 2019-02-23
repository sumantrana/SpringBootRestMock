package com.sumant.boot.learning;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment=WebEnvironment.RANDOM_PORT )
public class TestControllerTests {

	@LocalServerPort
	int port;

    @Autowired
	private TestRestTemplate testTemplate;
    
    MockRestServiceServer theMockService;
    
    
    @Before
    public void setup() {
    	
    	theMockService = MockRestServiceServer.bindTo(testTemplate.getRestTemplate()).build();
    	 
		String URI = "http://localhost:" + port + "/pc";
		
    	theMockService.expect(ExpectedCount.manyTimes(), MockRestRequestMatchers.requestTo(URI)).andExpect(method(HttpMethod.GET))
		.andRespond(withSuccess("Yes", MediaType.TEXT_PLAIN));    	
    }
	
	@Test
	public void testMockService() {
	
		String URI = "http://localhost:" + port + "/pc";
		
		String theResponse = this.testTemplate.getForObject(URI, String.class);
		
		assertThat(theResponse, is(equalTo("Yes")));
		
		theMockService.verify();
	
	}

	
}
