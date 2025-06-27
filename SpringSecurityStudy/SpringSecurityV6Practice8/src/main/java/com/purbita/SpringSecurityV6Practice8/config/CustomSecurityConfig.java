package com.purbita.SpringSecurityV6Practice8.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.purbita.SpringSecurityV6Practice8.service.UserAuthService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class CustomSecurityConfig {
	
	@Autowired
	CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Autowired
	CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
	
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
		http.csrf().disable()
		.authorizeRequests().requestMatchers("/public/**").permitAll()
		.requestMatchers("/private/admin/**").hasAuthority("ADMIN")
		.requestMatchers("/private/user/**").hasAuthority("USER")
		.and()
		.exceptionHandling(e->e.accessDeniedHandler(customAccessDeniedHandler)
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		;
		return http.httpBasic().and().build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setUserDetailsService(userDetailsService());
		daoAuth.setPasswordEncoder(passwordEncoder());
		return daoAuth;
	}
	
	@Bean
	public AuthenticationManager AuthenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
