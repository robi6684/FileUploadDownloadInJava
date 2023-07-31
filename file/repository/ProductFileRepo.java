package com.monocept.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.file.entities.ProductFile;

public interface ProductFileRepo extends JpaRepository<ProductFile,Long>{

	ProductFile findByName(String name);
}
