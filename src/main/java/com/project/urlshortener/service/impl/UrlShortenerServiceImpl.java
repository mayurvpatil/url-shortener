package com.project.urlshortener.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.urlshortener.dto.UrlShortenerDto;
import com.project.urlshortener.exception.InvalidUrlException;
import com.project.urlshortener.service.Memory;
import com.project.urlshortener.service.UrlShortenerService;

/**
 * @author mayurpatil
 */

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

	@Override
	public void storeUrl(UrlShortenerDto urlShortenerDto) {
		Memory.getInstance().memory.put(DigestUtils.sha256Hex(urlShortenerDto.getLongUrl())
				.toString()
				.substring(0, 10), urlShortenerDto);
	}

	@Override
	public boolean isShortUrlPresent(String shortUrl) {
		return Memory.getInstance().memory.containsKey(shortUrl) ? true : false;
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
			return Memory.getInstance().memory.get(shortUrl)
					.getLongUrl();

		} else {
			throw new InvalidUrlException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "Invalid short url.");
		}

	}

}
