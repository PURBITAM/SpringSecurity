package com.purbita.springSecurityT4Nov24.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purbita.springSecurityT4Nov24.models.Cart;
import com.purbita.springSecurityT4Nov24.models.Product;
import com.purbita.springSecurityT4Nov24.models.User;
import com.purbita.springSecurityT4Nov24.repo.CartRepo;
import com.purbita.springSecurityT4Nov24.repo.ProductRepo;
import com.purbita.springSecurityT4Nov24.repo.UserRepo;

@Service
public class ModelService {

	@Autowired
    private ProductRepo productRepo;
	
	@Autowired
    private CartRepo cartRepo;
	
	@Autowired
    private UserRepo userRepo;
	
	public Optional<Cart> cartRepoFindById(Integer userId){
		Optional<Cart> cart = cartRepo.findById(userId);
		return cart;
	}
	
	public List<Product> productRepoFindByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(String keyword){
	List<Product> products = productRepo.findByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(keyword, keyword);
	return products;
	}
	
	public List<User> userfind() {
        List<User> users = userRepo.findAll();
        return users;
	}
    
	
	
	
}
