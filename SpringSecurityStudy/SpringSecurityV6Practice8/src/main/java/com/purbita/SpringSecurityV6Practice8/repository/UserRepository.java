package com.purbita.SpringSecurityV6Practice8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.SpringSecurityV6Practice8.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Users findByUsername(String username);

}
