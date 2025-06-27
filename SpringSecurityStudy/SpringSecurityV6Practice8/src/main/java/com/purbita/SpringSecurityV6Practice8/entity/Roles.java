package com.purbita.SpringSecurityV6Practice8.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "rolespractice2", schema = "myschema")
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq1",initialValue = 1, sequenceName = "seq2")
	private Integer id;
	private String name;
	
	@ManyToMany
	@JoinTable(name = "user_role_1", schema = "myschema",
	joinColumns = @JoinColumn(name = "role_id"),
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	List<Users> users;
	
	public Roles() {
		// TODO Auto-generated constructor stub
	}
	
	public Roles(String name) {
		this.name = name;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public List<Users> getUsers() {
		return users;
	}


	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	
	

}
