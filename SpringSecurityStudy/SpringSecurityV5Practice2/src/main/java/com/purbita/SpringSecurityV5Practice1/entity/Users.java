package com.purbita.SpringSecurityV5Practice1.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="SecurityV5UsersPrac" , schema = "myschema")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(sequenceName = "SecurityV6UsersPrac2Sequence",initialValue = 1,name = "SecurityV6UsersPrac2Sequence1")
	private Integer id;
	private String username;
	private String password;
	
	@ElementCollection(targetClass=Role.class, fetch=FetchType.EAGER)
	@CollectionTable(name="user_role",joinColumns= @JoinColumn(name="user_id"),schema = "wingst4schema")
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
	public Users() {
		super();
	}

	public Users(String username, String password, Set<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
}
