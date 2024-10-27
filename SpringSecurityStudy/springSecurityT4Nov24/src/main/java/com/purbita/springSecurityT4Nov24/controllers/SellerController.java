package com.purbita.springSecurityT4Nov24.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.purbita.springSecurityT4Nov24.models.Category;
import com.purbita.springSecurityT4Nov24.models.Product;
import com.purbita.springSecurityT4Nov24.models.User;
import com.purbita.springSecurityT4Nov24.repo.CategoryRepo;
import com.purbita.springSecurityT4Nov24.repo.ProductRepo;
import com.purbita.springSecurityT4Nov24.repo.UserRepo;

@RestController
@RequestMapping("/api/auth/seller")
public class SellerController {
	
	@Autowired
    private ProductRepo productRepository;

    @Autowired
    private CategoryRepo categoryRepository;

    @Autowired
    private UserRepo userRepository;

    // Retrieve all products for the seller
    @GetMapping("/product")
    public ResponseEntity<?> getSellerProducts(HttpServletRequest request) {
    	try 
    	{
    		User loggedInUser = userRepository.findByUsername(request.getRemoteUser());
    		List<Product> products =productRepository.findBySellerUserId(loggedInUser.getUserId());
    		return ResponseEntity.ok(products);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
        
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Integer productId, HttpServletRequest request) {
    	System.out.println("/product/{productId} : "+productId);
    	User loggedInUser = userRepository.findByUsername(request.getRemoteUser());
		List<Product> products = productRepository.findBySellerUserId(loggedInUser.getUserId());
		Product product = null;
		System.out.println("products.size() : "+ products.size());
		System.out.println("loggedInUser.getUserId() : "+ loggedInUser.getUserId());
		for(Product itr:products)
		{
			System.out.println("itr.getSeller().getUserId() : "+ itr.getSeller().getUserId());
			System.out.println("itr.getSeller().getUserId() : "+ itr.getProductId());
			System.out.println("itr.getSeller().getUserId() : "+ (itr.getProductId()==productId));
			System.out.println("itr.getSeller().getUserId() : "+ (itr.getSeller().getUserId()==loggedInUser.getUserId()));
			System.out.println("itr.getProductId()==productId : "+(itr.getProductId()==productId && itr.getSeller().getUserId()==loggedInUser.getUserId()));
			if(itr.getProductId().equals(productId) && itr.getSeller().getUserId()==loggedInUser.getUserId())
			{
				product = itr;
			}
		}
		if(product != null && product.getSeller().getUserId()==loggedInUser.getUserId())
		{
			return ResponseEntity.ok(product);
		}
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Add a new product
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product, HttpServletRequest request) {
    	
        System.out.println("product : "+product.toString());
        if (product.getProductId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }
        User loggedInUser = userRepository.findByUsername(request.getRemoteUser());
        product.setSeller(loggedInUser);
        productRepository.save(product);
        
        String scheme = request.getScheme();// Get the protocol (HTTP or HTTPS)
        String serverName = request.getServerName();// Get the server name (hostname or IP)
        int port = request.getRemotePort();// Get the port number
        String RequestURI = request.getRequestURI();
        List<Product> productReturn = productRepository.findBySellerUserId(loggedInUser.getUserId());
        String redirectUrl = String.format(scheme+"://"+serverName+":8080"+RequestURI+"/"+productReturn.get(productReturn.size()-1).getProductId());
        
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", redirectUrl).body(productReturn.get(0));
    }

    // Update an existing product
    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody Product updatedProduct,HttpServletRequest request) {

    	System.out.println("updateProduct");
        Optional<Product> productOpt = productRepository.findById(updatedProduct.getProductId());
        Product product = null;
        if(!productOpt.isEmpty())
        {
        	product = productOpt.get();
        	product.setCategory(updatedProduct.getCategory());
        	product.setProductName(updatedProduct.getProductName());
        	product.setPrice(updatedProduct.getPrice());
        	productRepository.save(product);
        }
        
        if(!(product == null))
        {
        	System.out.println("put product : "+updatedProduct.getProductId() +"--> "+product.toString());
        	productOpt = productRepository.findById(updatedProduct.getProductId());
        	System.out.println("productOpt : "+productOpt.get().toString());
        	return ResponseEntity.status(HttpStatus.OK).body(productOpt);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productOpt);
    }

    // Delete a product
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId, Authentication authentication) {
        User seller = (User) authentication.getPrincipal();

        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Product product = productOpt.get();

        // Check if the authenticated user is the seller of the product
        if (!product.getSeller().getUserId().equals(seller.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this product");
        }

        productRepository.delete(product);
        return ResponseEntity.ok("Product deleted successfully");
    }

}
