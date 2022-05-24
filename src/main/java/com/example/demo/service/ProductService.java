package com.example.demo.service;

import javax.validation.ConstraintViolationException;

import com.example.demo.exception.ProductCollectionException;
import com.example.demo.model.productDTO;

public interface ProductService {
	public void createProduct(productDTO product) throws ProductCollectionException, ConstraintViolationException ;
}
