package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.productDTO;

@Repository
public interface ProductRepository extends MongoRepository<productDTO, String> {
	
//	search for product name in database
	@Query("{'productName': ?0}")
	Optional<productDTO> findByProductName(String productName);
}
