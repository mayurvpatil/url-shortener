package com.project.urlshortener.service;

import java.util.HashMap;
import java.util.Map;

import com.project.urlshortener.dto.UrlShortenerDto;

/**
 * @author mayurpatil
 */

public class Memory {

	private static Memory instance = null;
	public Map<String, UrlShortenerDto> memory = new HashMap<>();
	
	private Memory() {}
	
	public static Memory getInstance()
    {
        if (instance == null)
        	instance = new Memory();
 
        return instance;
    }
	
}
