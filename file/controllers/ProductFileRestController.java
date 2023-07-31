package com.monocept.file.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monocept.file.entities.ProductFile;
import com.monocept.file.repository.ProductFileRepo;
import com.monocept.file.service.ProductFileService;

@RestController
@RequestMapping("/fileapp")
public class ProductFileRestController {

	@Autowired
	private ProductFileService productFileService;
	
	@Autowired
	private ProductFileRepo productFileRepo;
	
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping("/upload")
	public void uploadFile(@RequestParam("file")MultipartFile file) throws IOException{
		productFileService.uploadFile(file);
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
		byte[] file = productFileService.downloadFile(fileName);
		ProductFile productFile = productFileRepo.findByName(fileName);
		String mediaType = productFile.getType();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(mediaType)).body(file);
	}
	
}
