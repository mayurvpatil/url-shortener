package com.project.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.urlshortener.dto.UrlShortenerDto;
import com.project.urlshortener.exception.InvalidUrlException;
import com.project.urlshortener.service.impl.UrlShortenerServiceImpl;

/**
 * @author mayurpatil
 */

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(properties = { "storage.file.path=/tmp/storage.txt" })
public class UrlShortenerServiceTest {

	@InjectMocks
	private UrlShortenerService urlShortenerService = new UrlShortenerServiceImpl();

	@Mock
	private StorageService storageService;

	private UrlShortenerDto urlShortenerDto;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		storageService = new StorageService();
		urlShortenerDto = new UrlShortenerDto();
		urlShortenerDto.setLongUrl("https://google.com");
	}

	@Test
	public void convertToShortUrl() {
		String shortenerUrl = urlShortenerService.convertToShortUrl(urlShortenerDto);
		assertTrue(!shortenerUrl.isEmpty());
		storageService.memory.clear();
	}

	@Test
	public void storeUrl() {
		urlShortenerService.storeUrl(urlShortenerDto);
		assert (storageService.memory.size() > 0);
		storageService.memory.clear();
	}

	@Test
	public void isShortUrlPresent() {
		String shortUlr = urlShortenerService.convertToShortUrl(urlShortenerDto);
		assertTrue(urlShortenerService.isShortUrlPresent(shortUlr));
		storageService.memory.clear();
	}

	@Test
	public void getLongUrl() {
		String shortUlr = urlShortenerService.convertToShortUrl(urlShortenerDto);
		String longUrl = urlShortenerService.getLongUrl(shortUlr);
		assertEquals(longUrl, urlShortenerDto.getLongUrl());

		storageService.memory.clear();

	}

	@Test(expected = InvalidUrlException.class)
	public void getLongUrlException() {
		urlShortenerService.getLongUrl(DigestUtils.sha256Hex(urlShortenerDto.getLongUrl())
				.toString()
				.substring(0, 10));
	}

}