package com.purbita.SpringSecurityV6PracticeJoin.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="joinPracUser",schema = "myschema")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@SequenceGenerator(sequenceName = "user_seq",name="user_seq",initialValue = 1)
	private Integer userId;
	private String username;
	private String password;
	
	@OneToOne(mappedBy = "users" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Cart cart;
	
	@ManyToMany(mappedBy = "users")
	private List<Roles> roles;
	
	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", password=" + password + ", cart=" + cart
				+ ", roles=" + roles + "]";
	}
	
	

}
