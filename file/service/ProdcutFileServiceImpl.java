package com.monocept.file.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.monocept.file.entities.ProductFile;
import com.monocept.file.repository.ProductFileRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdcutFileServiceImpl implements ProductFileService{
	
	@Autowired
	private ProductFileRepo productFileRepo;

	private final String PATH = "C:\\Users\\robi6\\OneDrive\\Desktop\\Monocept Training\\Spring Assignments\\08-FileUploadDownload-app";
	
	public ProductFile uploadFile(MultipartFile file) throws IllegalStateException, IOException{
		String fullPath = PATH+file.getOriginalFilename();
		ProductFile productfile  = new ProductFile();
		productfile.setName(file.getOriginalFilename());
		productfile.setType(file.getContentType());
		productfile.setPath(fullPath);
		
		file.transferTo(new File(fullPath));
		return productFileRepo.save(productfile);
	}
	
	public byte[] downloadFile(String fileName) throws IOException{
		ProductFile productfile  = productFileRepo.findByName(fileName);
        String fullPath = productfile.getPath();
        return Files.readAllBytes(new File(fullPath).toPath());
    }

}
