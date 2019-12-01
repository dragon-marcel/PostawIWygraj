package com.example.PostawIWygraj.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	private final String PATH = "src/main/resources/static/avatar";

	@Override
	public String save(MultipartFile file) {
		String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		Path rothPath = Paths.get(PATH).resolve(uniqueFileName);
		Path rootAbsolutePath = rothPath.toAbsolutePath();
		try {
			Files.copy(file.getInputStream(), rootAbsolutePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uniqueFileName;
	}

}
