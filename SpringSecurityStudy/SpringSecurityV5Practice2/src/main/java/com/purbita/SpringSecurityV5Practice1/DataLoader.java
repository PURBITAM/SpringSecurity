package com.purbita.SpringSecurityV5Practice1;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.purbita.SpringSecurityV5Practice1.entity.Role;
import com.purbita.SpringSecurityV5Practice1.entity.Users;
import com.purbita.SpringSecurityV5Practice1.repository.UserRepository;



@Component
public class DataLoader implements CommandLineRunner {
	
	private final UserRepository userRepository;
	
	public DataLoader(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//		
//		userRepository.save(new Users("purbita", bcrypt.encode("12345"), "USER"));
//		userRepository.save(new Users("rik", bcrypt.encode("12345"), "ADMIN"));
//		userRepository.save(new Users("arpan", bcrypt.encode("12345"), "USER,ADMIN"));
		
		Set<Role> rl = new HashSet<Role>();
		rl.add(Role.ADMIN);
		
		
		userRepository.save(new Users("rik", "12345", rl));
		userRepository.save(new Users("arpan", "12345", rl));
		rl = new HashSet<Role>();
		rl.add(Role.USER);
		userRepository.save(new Users("purbita", "12345",rl));
		userRepository.save(new Users("arpan", "12345", rl));
	}

}
