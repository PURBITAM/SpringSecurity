package com.purbita.SpringSecurityPracticeRoleAccess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.purbita.SpringSecurityPracticeRoleAccess.service.UserAuthService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserAuthService userAuthService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAuthService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/welcome").permitAll()
		.antMatchers("/user","/admin").authenticated() // only having this line means, any valid credential will access this api irrespective of roles.
		.antMatchers("/user").hasAnyAuthority("USER")
		.antMatchers("/admin").hasAnyAuthority("ADMIN")
		.and().formLogin().and().httpBasic(); 
		
		
//		.and().formLogin().and().httpBasic(); //-------> if we want the pre-build form to test the api
//		.and().httpBasic(); //-------> we can keep this if we want to keep login simple with popup and don't want the pre-build form 
//		; //-------------> this will throw error that access denied 403 without any form to give credentials.
		
		
//		SWAGGER Implementeed --- ignore this part ========================
		 
		final String[] SWAGGER_AUTH_WHITELIST = new String[] { "/**/swagger-ui.html", "/**/webjars/**",
				"/**/swagger-resources", "/**/swagger-resources/**", "/**/v2/api-docs", "/**/configuration/ui",
				"/**/configuration/security", "/**/swagger-ui/**", "/**/v3/api-docs/**" };
		
		http.authorizeRequests().antMatchers("/swagger-ui.html", "swagger-ui", "/v3/api-docs/**", "/v2/api-docs").permitAll()
		.antMatchers(SWAGGER_AUTH_WHITELIST).permitAll();

//		SWAGGER ENDs =======================================================

	}	
}
