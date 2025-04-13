package com.purbita.SpringSecurityV6T4Codebase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.purbita.SpringSecurityV6T4Codebase.models.Cart;
import com.purbita.SpringSecurityV6T4Codebase.models.CartProduct;
import com.purbita.SpringSecurityV6T4Codebase.models.Category;
import com.purbita.SpringSecurityV6T4Codebase.models.Product;
import com.purbita.SpringSecurityV6T4Codebase.models.Role;
import com.purbita.SpringSecurityV6T4Codebase.models.User;
import com.purbita.SpringSecurityV6T4Codebase.repo.CartProductRepo;
import com.purbita.SpringSecurityV6T4Codebase.repo.CartRepo;
import com.purbita.SpringSecurityV6T4Codebase.repo.CategoryRepo;
import com.purbita.SpringSecurityV6T4Codebase.repo.ProductRepo;
import com.purbita.SpringSecurityV6T4Codebase.repo.UserRepo;

@Component
public class DataLoader implements CommandLineRunner {
	
	private final UserRepo userRepository;
	private final CategoryRepo categoryRepo;
	private final CartRepo cartRepo;
	private final ProductRepo productRepo;
	private final CartProductRepo cartProductRepo;
	
	public DataLoader(UserRepo userRepository, CategoryRepo categoryRepo, CartRepo cartRepo, ProductRepo productRepo,CartProductRepo cartProductRepo) {
		super();
		this.userRepository = userRepository;
		this.categoryRepo = categoryRepo;
		this.cartRepo = cartRepo;
		this.productRepo = productRepo;
		this.cartProductRepo = cartProductRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		
		categoryRepo.save(new Category("Fashion"));
		categoryRepo.save(new Category("Electronics"));
		categoryRepo.save(new Category("Books"));
		categoryRepo.save(new Category("Groceries"));
		categoryRepo.save(new Category("Medicines"));

		Set<Role> rl = new HashSet<Role>();
		rl.add(Role.CONSUMER);
		userRepository.save(new User("jack","pass_word",rl));
		userRepository.save(new User("bob","pass_word",rl));
		rl = new HashSet<Role>();
		rl.add(Role.SELLER);
		userRepository.save(new User("apple","pass_word",rl));
		userRepository.save(new User("glaxo","pass_word",rl));
		
		cartRepo.save(new Cart(20.00,userRepository.findById(1).get()));
		cartRepo.save(new Cart(0.00,userRepository.findById(2).get()));
		
		productRepo.save(new Product(29190.00,"Apple iPad 10.2 8th Gen Wifi iOS Tablet",categoryRepo.findById(2).get(),userRepository.findById(3).get()));
		productRepo.save(new Product(10.00,"Crocin pain relief tablet",categoryRepo.findById(5).get(),userRepository.findById(4).get()));
		
		cartProductRepo.save(new CartProduct(cartRepo.findById(1).get(),productRepo.findById(2).get(),2));

	}

}
