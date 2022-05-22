package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="product")
public class productDTO {
	@Id
	private String id;
	public productDTO() {
		super();
	}
	public productDTO(String id, String productName, double price, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	private String productName;
	private double price;
	private Date createdAt;
	private Date updatedAt;
	public void setCreatedAt(Date date) {
		createdAt = date;
	}
	public void setUpdatedAt(Date date) {
		updatedAt=date;
	}	
}
