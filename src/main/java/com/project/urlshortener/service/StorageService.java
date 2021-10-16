package com.project.urlshortener.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.urlshortener.dto.UrlShortenerDto;

/**
 * @author mayurpatil
 */

@Service
public class StorageService {

	public Map<String, UrlShortenerDto> memory;

	@Value("${storage.file.path}")
	private String storageFilePath;

	private static final Logger logger = LogManager.getLogger(StorageService.class);

	public StorageService() {
		memory = new HashMap<>();
	}

	@PostConstruct
	private void loadData() throws Exception {

		try {

			logger.info("loding storage file ...");
			File file = new File(storageFilePath);

			logger.info("storage file path: " + storageFilePath);

			if (!file.getParentFile()
					.exists()) {
				logger.info("storage directory not exists.");
			} else {
				logger.info("storage directory exists.");
			}

			if (!file.exists()) {
				logger.info("storage file not exists.");

				file.createNewFile();

				logger.info("storage file created.");
				return;
			}

			Properties properties = new Properties();
			properties.load(new FileInputStream(storageFilePath));

			for (String key : properties.stringPropertyNames()) {
				UrlShortenerDto urlShortenerDto = new UrlShortenerDto();
				urlShortenerDto.setLongUrl(properties.get(key)
						.toString());
				memory.put(key, urlShortenerDto);
			}

			logger.info("storage file loaded.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void putData(String shortUrl, UrlShortenerDto urlShortenerDto) {

		try (FileOutputStream fileOutputStream = new FileOutputStream(storageFilePath, true)) {

			this.memory.put(shortUrl, urlShortenerDto);

			Properties properties = new Properties();
			properties.put(shortUrl, urlShortenerDto.getLongUrl());
			properties.store(fileOutputStream, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
