package com.example.PostawIWygraj.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
 public String save(MultipartFile file);
}
