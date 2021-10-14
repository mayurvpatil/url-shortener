package com.project.urlshortener.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.urlshortener.dto.UrlShortenerDto;
import com.project.urlshortener.service.UrlShortenerService;

/**
 * @author mayurpatil
 */

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class UrlShortenerControllerTest extends AbstractControllerTest {

	@MockBean
	private UrlShortenerService urlShortenerService;

	private UrlShortenerDto urlShortenerDto;
	protected ObjectMapper mapper;
	private static final String POST_LONG_URL = "http://localhost:8080/url-shortener";
	private static final String GET_SHORTEN_URL = "http://localhost:8080/url-shortener/abcg73gdf";
	private static final String DUMMY_SHORT_URL = "abcg73gdf";

	public void testIfAllRequiredServicesAreMocked() {
		assertNotNull(mvc);
		assertNotNull(urlShortenerService);
	}

	@Before
	@Override
	public void setup() {
		super.setup();
		this.mapper = new ObjectMapper();
		urlShortenerDto = new UrlShortenerDto();
		urlShortenerDto.setLongUrl(DUMMY_LONG_URL);
		testIfAllRequiredServicesAreMocked();
	}

	@Test
	public void storeShortUrl() throws Exception {
		String requestJson = mapper.writeValueAsString(urlShortenerDto);
		executePostAPI(POST_LONG_URL, requestJson);
	}

	@Test
	public void getLongUrl() throws Exception {
		doReturn(DUMMY_LONG_URL).when(urlShortenerService)
				.getLongUrl(DUMMY_SHORT_URL);
		MvcResult mvcResult = executeGetAPI(GET_SHORTEN_URL);
		String jobString = mvcResult.getResponse()
				.getContentAsString();
		assertNotNull(jobString);
	}

}