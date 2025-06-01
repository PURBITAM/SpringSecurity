package com.purbita.SpringSecurityV6PracticeJoin.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="joinPracProduct",schema = "myschema")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@SequenceGenerator(sequenceName = "product_seq",name="product_seq",initialValue = 1)
	private Integer productId;
	private String productname;
	
	@ManyToMany(mappedBy = "product")
	private List<Cart> cart;
	
	

	public Product(String productname) {
		super();
		this.productname = productname;
	}

	public Product() {
		super();
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}
	
	
	
}
