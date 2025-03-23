package com.purbita.SpringSecurityV6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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

import com.purbita.SpringSecurityV6.service.UserAuthService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class CustomSecurityConfig {
	@Autowired
	CustomAccessDeniedHandler customAccessDeniedHandler;
	@Autowired
	CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
	
	@Bean
	public UserDetailsService userDetailsService() {return new UserAuthService();}
	@Bean
    public PasswordEncoder passwordEncoder() { return NoOpPasswordEncoder.getInstance();}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http.csrf().disable()
			.authorizeHttpRequests().requestMatchers("/","/welcome","/login").permitAll()
			.requestMatchers("/user/**","/adminuser/**").hasAuthority("USER")
			.requestMatchers("/admin/**","/adminuser/**").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and().exceptionHandling(e->e.accessDeniedHandler(customAccessDeniedHandler)
					.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
			.httpBasic()
			.and().sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//			.formLogin();
			return http.build();
	}

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
    	return config.getAuthenticationManager();
    }
    
}

