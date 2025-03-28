package com.purbita.springSecurityT4Nov24.models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
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
