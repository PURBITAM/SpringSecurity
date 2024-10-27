package com.purbita.springSecurityT4Nov24.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.springSecurityT4Nov24.models.CartProduct;
import com.purbita.springSecurityT4Nov24.models.Product;

@Repository
public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {

}
