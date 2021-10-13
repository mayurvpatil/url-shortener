package com.project.urlshortener.service;

import com.project.urlshortener.dto.UrlShortenerDto;

/**
 * @author mayurpatil
 */

public interface UrlShortenerService {

	/**
	 * Method to store url.
	 * 
	 * @param urlShortenerDto
	 */
	public void storeUrl(UrlShortenerDto urlShortenerDto);

	/**
	 * Method to check if short url is present.
	 * 
	 * @param shortUrl
	 * @return
	 */
	public boolean isShortUrlPresent(String shortUrl);

	/**
	 * Method to convert original url to short url .
	 * 
	 * @param urldto
	 * @return
	 */

	public String convertToShortUrl(UrlShortenerDto urlShortenerDto);

	/**
	 * Method to get long url.
	 * 
	 * @param shortUrl
	 * @return
	 */
	public String getLongUrl(String shortUrl);

}
