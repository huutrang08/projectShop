package com.shop.ex.services;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageServices {

	void init();

	void delete(String storedFileName) throws IOException;

	Path load(String filename);

	Resource loadAsResource(String fileName);

	void store(MultipartFile file, String storedFileName);

	String getStoredFileName(MultipartFile file, String id);

}
