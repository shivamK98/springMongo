package com.example.demo.service;

import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProductCollectionException;
import com.example.demo.model.productDTO;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImplement implements ProductService {
	@Autowired
	private ProductRepository productRepo;

//	If product name already exists then don't create object
	@Override
	public void createProduct(productDTO product) throws ProductCollectionException, ConstraintViolationException {
		Optional<productDTO> productOptional = productRepo.findByProductName(product.getProductName());
		if(productOptional.isPresent()) {
			throw new ProductCollectionException(ProductCollectionException.ProductAlreadyExists());
		}else {
			product.setCreatedAt(new Date(System.currentTimeMillis()));
			product.setUpdatedAt(new Date(System.currentTimeMillis()));
			productRepo.save(product);
		}
		
		
	}

}
