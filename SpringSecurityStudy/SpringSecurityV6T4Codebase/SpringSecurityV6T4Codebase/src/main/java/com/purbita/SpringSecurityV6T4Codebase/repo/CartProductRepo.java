package com.purbita.SpringSecurityV6T4Codebase.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.SpringSecurityV6T4Codebase.models.CartProduct;

import jakarta.transaction.Transactional;


@Repository
public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {

	Optional<CartProduct> findByCartUserUserIdAndProductProductId(Integer userId, Integer productId);
	
	@Transactional
	void deleteByCartUserUserIdAndProductProductId(Integer userId, Integer productId);
}
