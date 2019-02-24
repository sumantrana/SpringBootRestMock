package com.sumant.boot.learning;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static io.restassured.RestAssured.*;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment=WebEnvironment.RANDOM_PORT )
public class TestControllerTests {

	@LocalServerPort
	int port;

//    @Autowired
//	private TestRestTemplate testTemplate;
    
//    MockRestServiceServer theMockService; 
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(1234); // No-args constructor defaults to port 8080
    
    
    @Before
    public void setup() {

    	wireMockRule.stubFor(get(urlEqualTo("/pc")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "text/plain")
                .withBody("Yes")));
//    	theMockService = MockRestServiceServer.bindTo(testTemplate.getRestTemplate()).build();
    	 
//		String URI = "http://localhost:" + port + "/pc";
		
//    	theMockService.expect(ExpectedCount.manyTimes(), MockRestRequestMatchers.requestTo(URI)).andExpect(method(HttpMethod.GET))
//		.andRespond(withSuccess("Yes", MediaType.TEXT_PLAIN));    	
    }
	
	@Test
	public void testMockService() {
	
		String URI = "http://localhost:" + port + "/pc";
		
//		String theResponse = this.testTemplate.getForObject(URI, String.class);
		
		given().when().get("http://localhost:" + port + "/")
		.then().statusCode(200).and().body(equalTo("Yes"));
		
		
		verify(getRequestedFor(urlMatching("/pc")));
//		assertThat( theResponse, is(equalTo("Yes")));
		
//		theMockService.verify();
	
	}
	

	
}
