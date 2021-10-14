package com.project.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.project.urlshortener.dto.UrlShortenerDto;
import com.project.urlshortener.exception.InvalidUrlException;
import com.project.urlshortener.service.impl.UrlShortenerServiceImpl;

/**
 * @author mayurpatil
 */

@RunWith(MockitoJUnitRunner.class)
public class UrlShortenerServiceTest {

	@InjectMocks
	private UrlShortenerService urlShortenerService = new UrlShortenerServiceImpl();

	private UrlShortenerDto urlShortenerDto;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		urlShortenerDto = new UrlShortenerDto();
		urlShortenerDto.setLongUrl("https://google.com");
	}

	@Test
	public void convertToShortUrl() {
		String shortenerUrl = urlShortenerService.convertToShortUrl(urlShortenerDto);
		assertTrue(!shortenerUrl.isEmpty());
		Memory.getInstance().memory.clear();
	}

	@Test
	public void storeUrl() {
		urlShortenerService.storeUrl(urlShortenerDto);
		assert (Memory.getInstance().memory.size() > 0);
		Memory.getInstance().memory.clear();
	}

	@Test
	public void isShortUrlPresent() {
		String shortUlr = urlShortenerService.convertToShortUrl(urlShortenerDto);
		assertTrue(urlShortenerService.isShortUrlPresent(shortUlr));
		Memory.getInstance().memory.clear();
	}

	@Test
	public void getLongUrl() {
		String shortUlr = urlShortenerService.convertToShortUrl(urlShortenerDto);
		String longUrl = urlShortenerService.getLongUrl(shortUlr);
		assertEquals(longUrl, urlShortenerDto.getLongUrl());

		Memory.getInstance().memory.clear();

	}

	@Test(expected = InvalidUrlException.class)
	public void getLongUrlException() {
		urlShortenerService.getLongUrl(DigestUtils.sha256Hex(urlShortenerDto.getLongUrl())
				.toString()
				.substring(0, 10));
	}

}