package com.purbita.SpringSecurityV5Practice1.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role {
	
	ADMIN , USER;

}

class RoleGrantedAuthority implements GrantedAuthority {
	private static final long serialVersionUID = -3408298481881657796L;
	String role; 

	public RoleGrantedAuthority(String role) {
		this.role= role;
	}

	@Override 
	public String getAuthority() {
		return this.role;
	}
}