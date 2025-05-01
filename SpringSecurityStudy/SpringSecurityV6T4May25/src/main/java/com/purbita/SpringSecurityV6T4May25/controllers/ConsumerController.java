package com.purbita.SpringSecurityV6T4May25.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.SpringSecurityV6T4May25.models.Cart;
import com.purbita.SpringSecurityV6T4May25.models.CartProduct;
import com.purbita.SpringSecurityV6T4May25.models.Product;
import com.purbita.SpringSecurityV6T4May25.models.User;
import com.purbita.SpringSecurityV6T4May25.repo.CartProductRepo;
import com.purbita.SpringSecurityV6T4May25.repo.CartRepo;
import com.purbita.SpringSecurityV6T4May25.repo.CategoryRepo;
import com.purbita.SpringSecurityV6T4May25.repo.ProductRepo;
import com.purbita.SpringSecurityV6T4May25.repo.UserRepo;
import com.purbita.SpringSecurityV6T4May25.service.UserAuthService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/auth/consumer")
public class ConsumerController {
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	ProductRepo productRepo;

	@Autowired
	UserRepo UserRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	UserAuthService userAuthService;
	
	@Autowired
	CartProductRepo cartProductRepo;
	 	
	    @GetMapping("/cart")
	    public ResponseEntity<Object> getCart(HttpServletRequest request) { 
	    	System.out.println("request.getRemoteUser() : "+request.getRemoteUser());
	    	Optional<Cart> cart = null;
	    	Optional<User> user = UserRepo.findByUsername(request.getRemoteUser());
	    	UserDetails userDetails = userAuthService.loadUserByUsername(request.getRemoteUser());
	    	cart = cartRepo.findByUserUsername(userDetails.getUsername());
	    	
	    	if(!cart.isEmpty()) {
	    		if(cart.get().getCartProducts().size()>1)
	    			System.out.println("cart 123 : "+user.get().getUserId()+"=>"+cart.get().getCartProducts().get(1).getQuantity());
	    		return ResponseEntity.status(HttpStatus.OK).body(cart.get());
	    	}
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cart.get());
	    }

	    @PostMapping("/cart")
	    public ResponseEntity<Object> postCart(@RequestBody Product product, HttpServletRequest request) {
	    	Optional<Product> products = productRepo.findById(product.getProductId());
	    	Optional<User> user = UserRepo.findByUsername(request.getRemoteUser());
	    	Optional<Cart> cart = cartRepo.findByUserUsername(user.get().getUsername());
	    	Optional<CartProduct> cartProd = cartProductRepo.findByCartUserUserIdAndProductProductId(user.get().getUserId(), product.getProductId());
	    	
	    	if(!cart.isEmpty()) {
	    		for(CartProduct cp2 : cart.get().getCartProducts()) {
	    			System.out.println("put cart postCart : "+cp2.getCpId()+"----"+cp2.getQuantity());
	    		}
	    	}
	    	System.out.println("cartProd.get().getProduct().getProductId()==product.getProductId() : "+product.getProductId());
	    	if(!cartProd.isEmpty() && cartProd.get().getProduct().getProductId()==product.getProductId()) {
	    		cartProd.get().setQuantity(cartProd.get().getQuantity()+1);
	    		cartProductRepo.save(cartProd.get());
	    		return ResponseEntity.status(HttpStatus.CONFLICT).body(cartProductRepo.findById(cartProd.get().getCpId()).get());
	    	}
	    	
	    	CartProduct cp = new CartProduct(cart.get(), products.get(), 1);
	    	cartProductRepo.save(cp);
	    	
	    	List<CartProduct> cplist = cart.get().getCartProducts();
	    	cplist.add(cp);
	    	cart.get().setCartProducts(cplist);
	    	cartRepo.save(cart.get());
	    	
	    	if(!cart.isEmpty()) {
	    		for(CartProduct cp1 : cart.get().getCartProducts()) {
	    			System.out.println("put cart postCart : "+cp1.getCpId()+"----"+cp1.getQuantity());
	    		}
	    	}
	    	return ResponseEntity.status(HttpStatus.OK).body(cp);
	    }

	    @PutMapping("/cart")
	    public ResponseEntity<Object> putCart(@RequestBody CartProduct cartProduct, HttpServletRequest request) {
	    	System.out.println("putCart cartProduct : "+cartProduct);
	    	Optional<User> user = UserRepo.findByUsername(request.getRemoteUser());
	    	System.out.println("putCart : "+user.get());
	    	
	    	Optional<Cart> cart = null;
	    	UserDetails userDetails = userAuthService.loadUserByUsername(request.getRemoteUser());
	    	cart = cartRepo.findByUserUsername(userDetails.getUsername());
	    	
	    	if(!cart.isEmpty()) {
	    		for(CartProduct cp : cart.get().getCartProducts()) {
	    			System.out.println("put cart start : "+cp.getCpId()+"----"+cp.getQuantity());
	    		}
	    	}
	    	
	    	Optional<CartProduct> cartProd = cartProductRepo.findByCartUserUserIdAndProductProductId(user.get().getUserId(),cartProduct.getProduct().getProductId());
	    	System.out.println("putCart : "+cartProd.get().getCpId()+"=>"+cartProd.get().getQuantity());
	    	if(!cartProd.isEmpty()) {
	    		cartProd.get().setQuantity(cartProduct.getQuantity());
	    		cartProd.get().setCart(cart.get());
	    		cartProductRepo.save(cartProd.get());
	    	}
	    	
	    	Optional<CartProduct> cartProd1 = cartProductRepo.findByCartUserUserIdAndProductProductId(user.get().getUserId(),cartProduct.getProduct().getProductId());
	    	System.out.println("putCart 1 : "+user.get().getUserId()+"=>"+cartProd1.get().getCpId()+"=>"
	    	+cartProd1.get().getQuantity()+"=>"+cartProd1.get().getCart().getCartId());
	    	
	    	cart = cartRepo.findByUserUsername(userDetails.getUsername());

	    	if(!cart.isEmpty()) {
	    		for(CartProduct cp : cart.get().getCartProducts()) {
	    			System.out.println("put cart end : "+cp.getCpId()+"----"+cp.getQuantity());
	    		}
	    		
	    	}
	    	return ResponseEntity.status(HttpStatus.OK).body(cartProd1.get());
	    }

	    @DeleteMapping("/cart")
	    public ResponseEntity<Object> deleteCart(HttpServletRequest request) {
	    	Optional<User> user = UserRepo.findByUsername(request.getRemoteUser());
	    	Optional<Cart> cart = cartRepo.findByUserUsername(user.get().getUsername());
	    	List<CartProduct> cp = cart.get().getCartProducts();
	    	for(CartProduct c : cp) {
	    		cartProductRepo.delete(c);
	    	}
	    	if(!cart.isEmpty()) {
	    		cart.get().setCartProducts(null);
	    		cartRepo.save(cart.get());
	    	}
	    	System.out.println("delete : "+cart.get());
	    	return ResponseEntity.status(HttpStatus.OK).body("");
	    }
}
