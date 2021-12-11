package com.shop.ex.services.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.ex.config.StorageProperties;
import com.shop.ex.exception.StorageException;
import com.shop.ex.exception.StorageFileNotFoundException;
import com.shop.ex.services.StorageServices;
@Service
public class FileSystemStorageImpl implements StorageServices {
	//đường dẫn gốc lưu thông tin file hình
	private final Path rootLocation;
    
	//  tạo file lưu trữ dựa vào id truyền vào
	@Override
	public String getStoredFileName(MultipartFile file, String id) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return "p" + id + "." + ext;
	}
// truyền thông tin cấu  hình cho phần lưu trữ
	public FileSystemStorageImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}
// Lưu thông tin file
	@Override
	public void store(MultipartFile file, String storedFileName) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Fail to store emty file");
			}
			Path destinationFile = this.rootLocation.resolve(Paths.get(storedFileName)).normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("cannot store file outside current directory");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			throw new StorageException("Failed to store file", e);
		}
	}
// Nạp file dạng resource
	@Override
	public Resource loadAsResource(String fileName) {
		try {
			Path file = load(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + fileName);
			}
		} catch (Exception e) {
			throw new StorageFileNotFoundException("Could not read file: " + fileName);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}
// xoa file khi không cần
	@Override
	public void delete(String storedFileName) throws IOException {
		Path destinationFile = rootLocation.resolve(Paths.get(storedFileName)).normalize().toAbsolutePath();

		Files.delete(destinationFile);
	}
//Khởi tạo các thư mục
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			System.out.println(rootLocation.toString());
		} catch (Exception e) {
			throw new StorageException("could not initialize storage", e);
		}
	}
}
