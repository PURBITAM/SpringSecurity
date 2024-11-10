package com.purbita.SpringSecurityPracticeJWTToken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
//@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class SpringSecurityPracticeJwtTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPracticeJwtTokenApplication.class, args);
	}

}
