package com.purbita.SpringSecurityV6Practice8.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="SecurityV6UsersPrac2" , schema = "myschema")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(sequenceName = "SecurityV6UsersPrac2Sequence",initialValue = 1,name = "SecurityV6UsersPrac2Sequence1")
	private Integer id;
	private String username;
	private String password;
	
	@ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
	private List<Roles> roles;
	
	public Users() {
		super();
	}

	public Users(String username, String password, List<Roles> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
}
