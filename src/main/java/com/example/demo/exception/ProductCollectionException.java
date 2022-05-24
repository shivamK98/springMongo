package com.example.demo.exception;

public class ProductCollectionException extends Exception {

	private static final long serialVersionUID = 1L;
	
//	constructor
	public ProductCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Product with id: "+ id + " not found!";
	}
	
	public static String ProductAlreadyExists() {
		return "Product with given name already exists";
	}

}
