package com.project.urlshortener.controller;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.urlshortener.dto.UrlShortenerDto;
import com.project.urlshortener.exception.InvalidUrlException;
import com.project.urlshortener.service.UrlShortenerService;

/**
 * @author mayurpatil
 */

@RestController
@RequestMapping("/url-shortener")
public class UrlShortenerController {

	private static final Logger logger = LogManager.getLogger(UrlShortenerController.class);

	@Autowired
	private UrlShortenerService urlShortenerService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping
	public Map<String, String> storeShortUrl(@RequestBody UrlShortenerDto urlShortenerDto) {

		if (logger.isInfoEnabled())
			logger.info("Action: {} ", "Store short URL request recieved.");
		
		UrlValidator validator = new UrlValidator(new String[] { "http", "https" });
		if (!validator.isValid(urlShortenerDto.getLongUrl())) {
			throw new InvalidUrlException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "Invalid url.");
		}

		return Collections.singletonMap("redirectUrl", httpServletRequest.getRequestURL()
				.toString() + "/" + urlShortenerService.convertToShortUrl(urlShortenerDto));

	}

	@GetMapping(value = "{shortUrl}")
	public ResponseEntity<String> get(@PathVariable String shortUrl) {

		if (logger.isInfoEnabled())
			logger.info("Action: {} ", "Redirecting original url from short url.");

		String url = urlShortenerService.getLongUrl(shortUrl);
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
	}

}
