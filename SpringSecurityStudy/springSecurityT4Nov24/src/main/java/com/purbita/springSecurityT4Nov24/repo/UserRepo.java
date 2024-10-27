package com.purbita.springSecurityT4Nov24.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.springSecurityT4Nov24.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	User findByUsername(String username);
//	Optional<User> findByUsername(String username);
}
