package com.purbita.springSecurityT4Nov24.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.springSecurityT4Nov24.config.UserInfoDetails;
import com.purbita.springSecurityT4Nov24.models.Cart;
import com.purbita.springSecurityT4Nov24.models.CartProduct;
import com.purbita.springSecurityT4Nov24.models.Product;
import com.purbita.springSecurityT4Nov24.models.User;
import com.purbita.springSecurityT4Nov24.repo.CartProductRepo;
import com.purbita.springSecurityT4Nov24.repo.CartRepo;
import com.purbita.springSecurityT4Nov24.repo.ProductRepo;
import com.purbita.springSecurityT4Nov24.repo.UserRepo;
import com.purbita.springSecurityT4Nov24.service.ModelService;

@RestController
@RequestMapping("/api/auth/consumer")
public class ConsumerController {
	
	 	@Autowired
	 	CartRepo cartRepo;
	 	
	 	@Autowired
	 	CartProductRepo cartProductRepo;
	 	
	 	@Autowired
	 	UserRepo userRepository;
	 	
	 	@Autowired
	 	ProductRepo productRepo;
	 	
	    @GetMapping("/cart")
	    public ResponseEntity<?> getCart(HttpServletRequest request) { 
	    	User loggedInUser = userRepository.findByUsername(request.getRemoteUser());
	    	Cart cart = cartRepo.findByUser(loggedInUser);
//	    	System.out.println("/cart : "+cart.size());
	        return ResponseEntity.ok(cart);
	    }

	    @PostMapping("/cart")
	    public ResponseEntity<?> addProductToCart(@RequestBody Product product,HttpServletRequest request) {
	    	try {
	    		
	    	
	    	CartProduct cartProduct = new CartProduct();
	    	System.out.println("product : "+product.toString());
	    	Optional<Product> productexists = productRepo.findById(product.getProductId());
	    	System.out.println("productexists : "+productexists.toString());
	    	Product prod = productexists.get();
	    	System.out.println("prod : "+prod.toString());
	    	List<CartProduct> cartProducts = cartProductRepo.findAll();
	    	CartProduct cartProductExists = null;
	    	if(cartProducts != null)
	    	{
	    		for(CartProduct itr : cartProducts)
		    	{
		    		if(itr.getProduct().getProductId() == prod.getProductId())
		    		{
		    			cartProductExists = itr;
		    		}
		    	}
	    	}
	    	if(product != null && cartProductExists == null)
	    	{
	    		System.out.println("inside if");
	    		cartProduct.setProduct(product);
	    		User loggedInUser = userRepository.findByUsername(request.getRemoteUser());
	    		Cart cart = cartRepo.findByUser(loggedInUser);
	    		cartProduct.setCart(cart);
	    		cartProduct.setQuantity(cartProduct.getQuantity()+1);
	    		cartProductRepo.save(cartProduct);
	    		return ResponseEntity.ok(cartProduct);
	    	}
	    	System.out.println("outside if");
	    		cartProduct.setProduct(product);
	    		User loggedInUser = userRepository.findByUsername(request.getRemoteUser());
	    		Cart cart = cartRepo.findByUser(loggedInUser);
	    		cartProduct.setCart(cart);
	    		cartProduct.setQuantity(cartProduct.getQuantity()+1);
	    		cartProductRepo.save(cartProduct);
	    
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body(product);
	    	}
	    	catch (Exception e) {
	    		return ResponseEntity.status(HttpStatus.CONFLICT).body(product);
			}
	    }

	    @PutMapping("/cart")
	    public ResponseEntity<?> updateCart(@RequestBody CartProduct cartProduct,HttpServletRequest request) {
	        System.out.println("updateCart : "+cartProduct);
	    	Optional<Product> product = productRepo.findById(cartProduct.getProduct().getProductId());
	    	Product prod = product.get();
	    	List<CartProduct> cartProducts = cartProductRepo.findAll();
	    	CartProduct cartProductExists = null;
	    	User loggedInUser = userRepository.findByUsername(request.getRemoteUser());
	    	Cart cart = cartRepo.findByUser(loggedInUser);
	    	System.out.println("cartProducts != null : "+(cartProducts != null));
	    	System.out.println("cartProducts != null : "+(cartProducts.size()));
	    	if(cartProducts != null)
	    	{
	    		System.out.println("itr : ");
	    		for(CartProduct itr : cartProducts)
		    	{
	    			System.out.println("itr.getProduct().getProductId() : "+(itr.getProduct().getProductId()));
		    		System.out.println("itr.getProduct().getProductId() == cartProduct.getProduct().getProductId() : "+(itr.getProduct().getProductId() == cartProduct.getProduct().getProductId()));
		    		if(itr.getProduct().getProductId() == cartProduct.getProduct().getProductId() || itr.getCart().getCartId() == cart.getCartId())
		    		{
		    			cartProductExists = itr;
		    		}
		    	}
	    	}
	    	System.out.println("cartProductExists != null : "+(cartProductExists != null));
	    	System.out.println("cartProductExists != null : "+cartProductExists.getProduct().toString());
	    	if(cartProductExists != null)
	    	{
	    		System.out.println("cartProductExists before : "+cartProductExists.getQuantity());
	    		cartProductExists.setProduct(cartProduct.getProduct());
	    		cartProductExists.setQuantity(cartProduct.getQuantity());
	    		System.out.println("cartProductExists after : "+cartProductExists.getQuantity());
	    		cartProductRepo.save(cartProductExists);
	    		System.out.println("cartProductExists before : "+cartProductRepo.findById(cartProductExists.getCpId()).get().getQuantity());
	    		return ResponseEntity.ok(cartProductExists); 
	    	}
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body(product);
	    }

	    @DeleteMapping("/cart")
	    public ResponseEntity<?> removeProductFromCart(@RequestBody Product product,HttpServletRequest request) {
	    	System.out.println("removeProductFromCart product : "+product);
	    	Optional<Product> productFind = productRepo.findById(product.getProductId());
	    	Product prod = productFind.get();
	    	System.out.println("removeProductFromCart prod : "+prod);
	    	List<CartProduct> cartProducts = cartProductRepo.findAll();
	    	CartProduct cartProductExists = null;
	    	System.out.println("cartProducts != null && prod != null : "+(cartProducts != null && prod != null));
	    	System.out.println("cartProducts : "+cartProducts.size());
	    	if(cartProducts != null && prod != null){
	    		for(CartProduct itr : cartProducts){
	    			System.out.println("itr.getProduct().getProductId().equals(prod.getProductId()) : "+(itr.getProduct().getProductId().equals(prod.getProductId())));
	    	    	
		    		if(itr.getProduct().getProductId().equals(prod.getProductId())){
		    			cartProductExists = itr;
		    		}
		    	}
	    	}
	    	System.out.println("removeProductFromCart cartProductExists : "+(cartProductExists != null));
	    	System.out.println("removeProductFromCart cartProductExists : "+(cartProductExists.getCpId()));
	    	
	    	if(cartProductExists != null)
	    	{
	    		cartProductRepo.delete(cartProductExists);
	    	}
	    	
	    	if(prod != null)
	    	{
	    		productRepo.deleteById(prod.getProductId());
	    		System.out.println("productRepo : "+productRepo.findById(prod.getProductId()));
	    	}
	        return ResponseEntity.ok().build();
	    }
}
