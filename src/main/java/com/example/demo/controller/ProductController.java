package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.productDTO;
import com.example.demo.repository.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/product")
	public ResponseEntity<?> getAllProducts(){
		List<productDTO> products = productRepo.findAll();
		if(products.size() > 0) {
			return new ResponseEntity<List<productDTO>> (products, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> createProduct(@RequestBody productDTO product){
		try {
			product.setCreatedAt(new Date(System.currentTimeMillis()));
			product.setUpdatedAt(new Date(System.currentTimeMillis()));
			productRepo.save(product);
			return new ResponseEntity<productDTO> (product, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
}





