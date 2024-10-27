package com.purbita.springSecurityT4Nov24.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.springSecurityT4Nov24.models.Cart;
import com.purbita.springSecurityT4Nov24.models.User;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	Cart findByUser(User user);

}
