package com.project.urlshortener.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.urlshortener.dto.UrlShortenerDto;

/**
 * @author mayurpatil
 */

@RestController
@RequestMapping("/url-shortener")
public class UrlShortenerController {

	private static final Logger logger = LogManager.getLogger(UrlShortenerController.class);

	/**
	 * Returns a short url.
	 * 
	 * @param urlShortenerDto
	 * @return
	 * 
	 */
	@PostMapping
	public String storeShortUrl(@RequestBody UrlShortenerDto urlShortenerDto) {

		if (logger.isInfoEnabled())
			logger.info("Action: {} ", "Store short URL request recieved.");

		return "short URL for service";

	}

	/**
	 * Returns a original url.
	 * 
	 * @param shortUrl
	 * @return
	 */
	@GetMapping(value = "{shortUrl}")
	public String get(@PathVariable String shortUrl) {

		if (logger.isInfoEnabled())
			logger.info("Action: {} ", "Redirecting original url from short url.");

		return "return original url and redirect";
	}

}
