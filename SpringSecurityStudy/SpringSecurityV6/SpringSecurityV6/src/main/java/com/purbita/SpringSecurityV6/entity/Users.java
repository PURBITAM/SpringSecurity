package com.purbita.SpringSecurityV6.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "securityV6user")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@SequenceGenerator(sequenceName = "securityV6userSeq",initialValue = 1, 
	name = "securityV6userSeq")
	@Column(name = "user_id")
	private Integer id;
	private String username;
	private String password;
	private String role;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {this.id = id;}
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + role + "]";
	}

}
