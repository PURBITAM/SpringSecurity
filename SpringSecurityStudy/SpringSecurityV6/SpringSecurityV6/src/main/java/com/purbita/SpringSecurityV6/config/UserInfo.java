package com.purbita.SpringSecurityV6.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.purbita.SpringSecurityV6.entity.Users;

public class UserInfo implements UserDetails {
	
	private String name;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserInfo(Users user) {
		this.name = user.getUsername();
		this.password = user.getPassword();
		this.authorities = Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {return authorities;}

	@Override
	public String getPassword() {return password;}

	@Override
	public String getUsername() {return name;}
	
	@Override
	public boolean isAccountNonExpired() {return true;}
	
	@Override
	public boolean isAccountNonLocked() {return true;}
	
	@Override
	public boolean isCredentialsNonExpired() {return true;}
	
	@Override
	public boolean isEnabled() {return true;}
}
