package com.purbita.SpringSecurityV6PracticeJoin.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="joinPracRole",schema = "myschema")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@SequenceGenerator(sequenceName = "role_seq",name="role_seq",initialValue = 1)
	private Integer roleId;
	private String rolename;
	
	@ManyToMany
    @JoinTable(name = "user_roles", schema = "myschema",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<Users> users;
	
	public Roles() {
		// TODO Auto-generated constructor stub
	}
	
	public Roles(String rolename) {
		super();
		this.rolename = rolename;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
}
