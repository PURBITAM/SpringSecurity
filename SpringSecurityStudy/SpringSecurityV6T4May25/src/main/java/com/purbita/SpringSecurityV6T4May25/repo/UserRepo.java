package com.purbita.SpringSecurityV6T4May25.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.SpringSecurityV6T4May25.models.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
}
