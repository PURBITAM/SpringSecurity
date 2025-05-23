package com.purbita.SpringSecurityV6T4Codebase.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.SpringSecurityV6T4Codebase.models.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	Optional<Cart> findByUserUsername(String username);

}
