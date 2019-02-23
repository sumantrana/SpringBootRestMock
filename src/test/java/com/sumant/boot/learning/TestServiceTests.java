package com.sumant.boot.learning;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;

@RunWith(SpringRunner.class)
@RestClientTest(TestService.class)
public class TestServiceTests {

	@Autowired
	TestService testService;
	
	@Autowired
	MockRestServiceServer theMockService;
	
	@Test
	public void testMockService() {
	
		theMockService.expect(ExpectedCount.manyTimes(), MockRestRequestMatchers.requestTo("/pc")).andExpect(method(HttpMethod.GET))
																				.andRespond(withSuccess("Yes", MediaType.TEXT_PLAIN));
		
		String theResponse = testService.callRestEndpoint();
		
		assertThat(theResponse, is(equalTo("Yes")));
		
		theMockService.verify();
	
	}

	
}
