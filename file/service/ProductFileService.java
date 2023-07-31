package com.monocept.file.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.monocept.file.entities.ProductFile;

public interface ProductFileService {
	ProductFile uploadFile(MultipartFile file)throws IllegalStateException, IOException;
	byte[] downloadFile(String fileName)throws IOException;
}
