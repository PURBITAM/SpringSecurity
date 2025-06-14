package com.purbita.SpringSecurityV6PracticeJoin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.purbita.SpringSecurityV6PracticeJoin.service.UserAuthService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class CustomSecurityConfig {
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserAuthService();

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().formLogin().and().build(); 
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuth = new  DaoAuthenticationProvider();
		daoAuth.setUserDetailsService(userDetailsService());
		daoAuth.setPasswordEncoder(passwordEncoder());
		return daoAuth; 
	}
	
}
