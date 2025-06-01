package com.purbita.SpringSecurityV6PracticeJoin.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="joinPracscheme",schema = "myschema")
public class Scheme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@SequenceGenerator(sequenceName = "scheme_seq",name="scheme_seq",initialValue = 1)
	private Integer schemeId;
	private String schemeName;
	
	
	@OneToMany(mappedBy = "scheme" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cart> cart;
	
	

	public Scheme() {
		super();
	}

	public Scheme(String schemeName) {
		super();
		this.schemeName = schemeName;
	}

	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	
	
	

}
