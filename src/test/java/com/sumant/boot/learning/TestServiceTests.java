package com.sumant.boot.learning;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class TestServiceTests {

	TestService testService;
	RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
	
	@Before
	public void setup() {
		testService = new TestService(mockRestTemplate, "http://example.com/llama");
	}
	
	@Test
	public void testMockService() {
		// Setup
		Mockito.when(mockRestTemplate.getForObject(anyString(), any())).thenReturn("Perfect passthrough");
//	
//		theMockService.expect(ExpectedCount.manyTimes(), MockRestRequestMatchers.requestTo("/pc")).andExpect(method(HttpMethod.GET))
//																				.andRespond(withSuccess("Yes", MediaType.TEXT_PLAIN));
//		
		//ACtion
		String theResponse = testService.callRestEndpoint();
		
		
		//Assertion
		assertThat(theResponse, is(equalTo("Perfect passthrough")));
		ArgumentCaptor<URI> captor = ArgumentCaptor.forClass(URI.class);
		Mockito.verify(mockRestTemplate, times(1)).getForObject(captor.capture(), String.class);
		//String returnValue = mockRestTemplate.getForObject(captor.capture(), String.class);
		
		//assertThat(captor.getValue().getPath(), is(equalTo("http://example.com/llama")));
//		
//		theMockService.verify();
	
	}

	
}
