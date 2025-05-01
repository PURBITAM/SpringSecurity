package com.purbita.SpringSecurityV6T4May25.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.purbita.SpringSecurityV6T4May25.models.Category;
import com.purbita.SpringSecurityV6T4May25.models.Product;
import com.purbita.SpringSecurityV6T4May25.models.User;
import com.purbita.SpringSecurityV6T4May25.repo.CategoryRepo;
import com.purbita.SpringSecurityV6T4May25.repo.ProductRepo;
import com.purbita.SpringSecurityV6T4May25.repo.UserRepo;
import com.purbita.SpringSecurityV6T4May25.service.UserAuthService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth/seller")
public class SellerController {
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	UserRepo UserRepo;
	
	@Autowired
	CategoryRepo categoryRepo;

	@PostMapping("/product")
    public ResponseEntity<Object> postProduct(@RequestBody Product product, HttpServletRequest request) {
		Optional<User> user = UserRepo.findByUsername(request.getRemoteUser());
		System.out.println("product : "+product);
		Optional<Category> category = categoryRepo.findByCategoryName(product.getCategory().getCategoryName());
		Product saveProduct = new Product(product.getPrice(), product.getProductName(), category.get(),user.get());
		productRepo.save(saveProduct);

		String http = request.getScheme();
		Integer port = request.getRemotePort();
		String server = request.getServerName();
		String RequestURI = request.getRequestURI();
		
		List<Product> prodFind = productRepo.findBySellerUserId(user.get().getUserId());
		
		String redirectedUrl = http+"://"+server+":80"+port+""+RequestURI+"/"+prodFind.get(prodFind.size()-1).getProductId();
    	return ResponseEntity.status(HttpStatus.CREATED).header("Location", redirectedUrl).body(saveProduct);
    }
	
    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts(HttpServletRequest request) {
    	Optional<User> user = UserRepo.findByUsername(request.getRemoteUser());
    	List<Product> products = productRepo.findBySellerUserId(user.get().getUserId());
    	if(!products.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.OK).body(products);
    	}
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Integer productId,HttpServletRequest request) {
    	Optional<Product> products = productRepo.findById(productId);
    	Optional<User> user = UserRepo.findByUsername(request.getRemoteUser());
    	if(!products.isEmpty() && products.get().getSeller().getUserId()==user.get().getUserId()) {
    		return ResponseEntity.status(HttpStatus.OK).body(products);
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }

    @PutMapping("/product")
    public ResponseEntity<?> putProduct(@RequestBody Product product, HttpServletRequest request) {
    	System.out.println("product put : "+product);
    	Optional<Product> products = productRepo.findById(product.getProductId());
    	Optional<User> user = UserRepo.findByUsername(request.getRemoteUser());
    	if(!products.isEmpty() && products.get().getSeller().getUserId()==user.get().getUserId()) {
    		if(product.getProductName()!=null) {
    			products.get().setProductName(product.getProductName());
    		}
    		if(product.getPrice()!=null) {
    			products.get().setPrice(product.getPrice());
    		}
    		if(product.getCategory().getCategoryName()!=null) {
    			Optional<Category> c = categoryRepo.findById(products.get().getCategory().getCategoryId());
    			c.get().setCategoryName(product.getCategory().getCategoryName());
    			products.get().setCategory(c.get());
    		}
    		productRepo.save(products.get());
    		return ResponseEntity.status(HttpStatus.OK).body(productRepo.findById(products.get().getProductId()).get());
    	}
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId, HttpServletRequest request) {
    	Optional<Product> p = productRepo.findById(productId);
    	Optional<User> user = UserRepo.findByUsername(request.getRemoteUser());
    	productRepo.delete(p.get());
    	return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
