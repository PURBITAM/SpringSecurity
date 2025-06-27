package com.purbita.SpringSecurityV6Practice8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.purbita.SpringSecurityV6Practice8.entity.Roles;
import com.purbita.SpringSecurityV6Practice8.entity.Users;
import com.purbita.SpringSecurityV6Practice8.repository.RoleRepository;
import com.purbita.SpringSecurityV6Practice8.repository.UserRepository;



@Component
public class DataLoader implements CommandLineRunner {
	
	private final UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	public DataLoader(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<Roles> rl = new ArrayList<Roles>();
		List<Users> ul = new ArrayList<Users>();
		
		Roles r = new Roles("ADMIN");
		Roles s = new Roles("USER");
		
		Users u1 = new Users("rik", "12345",rl);
		Users u2 = new Users("arpan", "12345",rl);
		Users u3 = new Users("purbita", "12345",rl);
		
		userRepository.save(u1);
		userRepository.save(u2);
		userRepository.save(u3);
		
		ul.add(u1);
		ul.add(u2);
		r.setUsers(ul);
		roleRepository.save(r);
		
		ul = new ArrayList<Users>();
		ul.add(u3);
		ul.add(u2);
		s.setUsers(ul);
		roleRepository.save(s);
		
	}

}
