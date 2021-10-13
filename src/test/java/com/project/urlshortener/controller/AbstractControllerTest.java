package com.project.urlshortener.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.project.urlshortener.UrlshortenerApplication;

/**
 * @author mayurpatil
 */

@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = { UrlshortenerApplication.class })
public class AbstractControllerTest {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected static final String DUMMY_LONG_URL = "http://dummyurl.com";

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
				.build();

	}

	/**
	 * Method to execute post call.
	 * 
	 * @param uri
	 * @param requestBodyContent
	 * @return
	 */
	public MvcResult executePostAPI(String uri, String requestBodyContent) throws Exception {
		return this.mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyContent))
				.andExpect(status().isOk())
				.andReturn();
	}

	/**
	 * Method to execute get call.
	 * 
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public MvcResult executeGetAPI(String uri) throws Exception {
		return this.mvc.perform(MockMvcRequestBuilders.get(uri))
				.andReturn();
	}

}