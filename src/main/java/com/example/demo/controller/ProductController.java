package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProductCollectionException;
import com.example.demo.model.productDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductService productService;
	
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
			productService.createProduct(product);
			return new ResponseEntity<productDTO> (product, HttpStatus.OK);
		}catch(ConstraintViolationException e){
			return new ResponseEntity<> (e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);	
		}catch (ProductCollectionException e) {
			return new ResponseEntity<> (e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getSingleProduct(@PathVariable("id") String id){
		Optional<productDTO> productOptional = productRepo.findById(id);
		if(productOptional.isPresent()) {
			return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Product not found with id " + id, HttpStatus.NOT_FOUND);					
		}
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> putById(@PathVariable("id") String id, @RequestBody productDTO productNew){
		Optional<productDTO> productOptional = productRepo.findById(id);
		if(productOptional.isPresent()) {
			productDTO productSave = productOptional.get();
			productSave.setProductName(productNew.getProductName() == null ? productSave.getProductName() : productNew.getProductName());
			productSave.setPrice(productNew.getPrice() == 0 ? productSave.getPrice() : productNew.getPrice());
			productSave.setUpdatedAt(productNew.getUpdatedAt() == null ? productSave.getUpdatedAt() : productNew.getUpdatedAt());
			productRepo.save(productSave);
			return new ResponseEntity<>(productSave, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Product not found with id " + id, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id){
		try {
			productRepo.deleteById(id);
			return new ResponseEntity<>("Product with id "+ id + " successfully deleted!", HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
}




















