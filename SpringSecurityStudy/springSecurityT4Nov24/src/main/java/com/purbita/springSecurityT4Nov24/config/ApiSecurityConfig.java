package com.purbita.springSecurityT4Nov24.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.purbita.springSecurityT4Nov24.service.UserAuthService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

//    @Autowired
//    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserAuthService jwtUserDetailsService;
    
    private final CustomAccessDeniedHandler accessDeniedHandler;
    
    
    
public ApiSecurityConfig(CustomAccessDeniedHandler accessDeniedHandler) {
		super();
		this.accessDeniedHandler = accessDeniedHandler;
	}

//    @Autowired
//    private ApiAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//    @Autowired
//    private JwtUserDetailsService jwtUserDetailsService;

    //private AccountService accountService;
    public static final String[] SWAGGER_AUTH_WHITELIST = new String[]{"/**/swagger-ui.html", "/**/webjars/**", "/**/swagger-resources", "/**/swagger-resources/**", "/**/v2/api-docs", "/**/configuration/ui", "/**/configuration/security", "/**/swagger-ui/**", "/**/v3/api-docs/**"};

    
    @Autowired 
    private PasswordEncoder bCryptPasswordEncoder;
    
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // configure AuthenticationManager so that it knows from where to load
//        // user for matching credentials
//        // Use BCryptPasswordEncoder
//        auth.userDetailsService(jwtUserDetailsService);
//    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //auth.userDetailsService(jwtUserDetailsService);
    	
//    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//    	auth.authenticationProvider(provider);
    	
    	auth.userDetailsService(jwtUserDetailsService);
    	
    	//auth.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/swagger-ui.html","swagger-ui","/v3/api-docs/**","/v2/api-docs").permitAll()
            .antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
            .antMatchers("/api/public/**").permitAll()
            .antMatchers("/api/auth/consumer/**").hasAuthority("CONSUMER")
            .antMatchers("/api/auth/seller/**").hasAuthority("SELLER")
//            .antMatchers("/api/auth/**").authenticated()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling(e->e.accessDeniedHandler(accessDeniedHandler)
            		.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
	        .sessionManagement(session->session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            
//        
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    	
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(SWAGGER_AUTH_WHITELIST);
        super.configure(web);
    }

}
