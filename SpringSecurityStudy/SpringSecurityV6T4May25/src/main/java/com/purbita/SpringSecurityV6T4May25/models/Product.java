package com.purbita.SpringSecurityV6T4May25.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "WingsT4Schema")
public class Product {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer productId;
	private String productName;
	private Double price;
	
	@ManyToOne()
	@JoinColumn(name="seller_id", referencedColumnName = "userId", updatable = false)
	private User seller;
	
	@ManyToOne()
	@JoinColumn(name="category_id",referencedColumnName = "categoryId")
	private Category category;
	
	public Product() {
		super();
	}
	
	public Product(Double price, String productName, Category category,  User seller) {
		super();
		this.productName = productName;
		this.price = price;
		this.seller = seller;
		this.category = category;
	}
	
	public Product(Integer productId, String productName, Double price, User seller, Category category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.seller = seller;
		this.category = category;
	}

	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", seller="
				+ seller + ", category=" + category + "]";
	}
	
	
	
}
