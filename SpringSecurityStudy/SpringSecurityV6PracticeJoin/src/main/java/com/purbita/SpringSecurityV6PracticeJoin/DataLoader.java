package com.purbita.SpringSecurityV6PracticeJoin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.purbita.SpringSecurityV6PracticeJoin.config.CustomUserDetails;
import com.purbita.SpringSecurityV6PracticeJoin.entity.Cart;
import com.purbita.SpringSecurityV6PracticeJoin.entity.Product;
import com.purbita.SpringSecurityV6PracticeJoin.entity.Roles;
import com.purbita.SpringSecurityV6PracticeJoin.entity.Scheme;
import com.purbita.SpringSecurityV6PracticeJoin.entity.Users;
import com.purbita.SpringSecurityV6PracticeJoin.repository.CartRepository;
import com.purbita.SpringSecurityV6PracticeJoin.repository.ProductRepository;
import com.purbita.SpringSecurityV6PracticeJoin.repository.RoleRepository;
import com.purbita.SpringSecurityV6PracticeJoin.repository.SchemeRepository;
import com.purbita.SpringSecurityV6PracticeJoin.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SchemeRepository schemeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		List<Users> ul = new ArrayList<Users>();

		Users user1 = new Users("purbita", "12345");
		Users user2 = new Users("rik", "12345");
		Users user3 = new Users("arpan", "12345");
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		Roles role1 = new Roles("USER");
		Roles role2 = new Roles("ADMIN");
		
		ul.add(user1);
		ul.add(user3);
		role1.setUsers(ul);
		ul = new ArrayList<Users>();
		ul.add(user2);
		ul.add(user3);
		role2.setUsers(ul);
	
		roleRepository.save(role1);
		roleRepository.save(role2);
		
		Cart cart1 = new Cart();
		Cart cart2 = new Cart();
		Cart cart3 = new Cart();
		
		Scheme scheme1 = new Scheme("GOLD");
		Scheme scheme2 = new Scheme("SILVER");
		
		schemeRepository.save(scheme1);
		schemeRepository.save(scheme2); 
		
		Product product1 = new Product("Lays");
		Product product2 = new Product("Oreo");
		Product product3 = new Product("Gems");
		
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		
		List<Product> pl = new ArrayList<Product>();
		pl.add(product1);
		pl.add(product2);

		cart1.setUsers(user1);
		cart1.setScheme(scheme1);
		cart1.setProduct(pl);
		cartRepository.save(cart1);
		
		pl = new ArrayList<Product>();
		pl.add(product2);
		pl.add(product3);
		cart2.setUsers(user2);
		cart2.setScheme(scheme1);
		cart2.setProduct(pl);
		cartRepository.save(cart2);
		
		pl = new ArrayList<Product>();
		pl.add(product1);
		pl.add(product3);		
		cart3.setUsers(user3);
		cart3.setScheme(scheme2);
		cart3.setProduct(pl);
		cartRepository.save(cart3);
		

	}

}
