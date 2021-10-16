package com.project.urlshortener.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.urlshortener.dto.UrlShortenerDto;
import com.project.urlshortener.exception.InvalidUrlException;
import com.project.urlshortener.service.StorageService;
import com.project.urlshortener.service.UrlShortenerService;

/**
 * @author mayurpatil
 */

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

	private static final Logger logger = LogManager.getLogger(UrlShortenerServiceImpl.class);
	
	@Autowired
	StorageService storageService;

	@Override
	public void storeUrl(UrlShortenerDto urlShortenerDto) {
		storageService.putData(DigestUtils.sha256Hex(urlShortenerDto.getLongUrl())
				.toString()
				.substring(0, 10), urlShortenerDto);
		
		logger.info("URL stored.");
	}

	@Override
	public boolean isShortUrlPresent(String shortUrl) {
		if(storageService.memory.containsKey(shortUrl)) {
			logger.info("Short URL exists.");
			return true;
		}
		logger.info("ShortURL not exists.");
		return false;
	}

	@Override
	public String convertToShortUrl(UrlShortenerDto urlShortenerDto) {

		if (urlShortenerDto.getLongUrl() != null
				&& !isShortUrlPresent(DigestUtils.sha256Hex(urlShortenerDto.getLongUrl())
						.toString()
						.substring(0, 10))) {
			storeUrl(urlShortenerDto);
		}

		return DigestUtils.sha256Hex(urlShortenerDto.getLongUrl())
				.toString()
				.substring(0, 10);
	}

	@Override
	public String getLongUrl(String shortUrl) {

		if (isShortUrlPresent(shortUrl)) {
			return storageService.memory.get(shortUrl)
					.getLongUrl();

		} else {
			throw new InvalidUrlException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "Invalid short url.");
		}

	}

}
