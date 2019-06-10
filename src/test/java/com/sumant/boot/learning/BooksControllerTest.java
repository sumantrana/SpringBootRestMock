package com.sumant.boot.learning;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {BookController.class, TestController.class})
public class BooksControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService service;

    @MockBean
    TestService testService;


    @Before
    public void setup(){

        Book book = new Book("TestTitle", "TestAuthor" );
        given(service.getBook()).willReturn(book);

        given(testService.callRestEndpoint()).willReturn("Called");
    }

    @Test
    public void test_whenGetBooks_thenReturn200OK() throws Exception {

        mockMvc.perform(get("/api/book").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void test_whenGetBook_thenReturnBook() throws Exception {

        mockMvc.perform(get("/api/book").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("TestTitle")))
                .andExpect(jsonPath("$.author", is("TestAuthor")));

    }

    @Test
    public void test_whenPostBook_thenReturn200OK() throws Exception {

        Book book = new Book("OneTitle", "OneAuthor" );
        String bookJson = new ObjectMapper().writeValueAsString(book);

        mockMvc.perform(post("/api/book").content(bookJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", is("Created")));
    }

    /**
     * Tests if 2 controllers can be tested using a single class for WebMvcTest
     * @throws Exception
     */
    @Test
    public void test_defaultEndpoint_Returns200OK() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
