package com.purbita.SpringSecurityPracticeRoleAccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.SpringSecurityPracticeRoleAccess.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
