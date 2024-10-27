package com.purbita.springSecurityT4Nov24.models;


import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer userId;
	@Column(unique = true)
	private String username;
	private String password;
	
	@ElementCollection(targetClass=Role.class, fetch=FetchType.EAGER)
	@CollectionTable(name="user_role",joinColumns=@JoinColumn(name="user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
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
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ "]";
	}
	
	
	
	
}
