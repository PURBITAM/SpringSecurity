package com.purbita.springSecurityT4Nov24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringSecurityT4Nov24Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityT4Nov24Application.class, args);
	}

}
