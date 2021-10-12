package com.project.urlshortener.dto;

import org.springframework.lang.NonNull;

/**
 * @author mayurpatil
 */

public class UrlShortenerDto {

	@NonNull
	private String longUrl;

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
}
