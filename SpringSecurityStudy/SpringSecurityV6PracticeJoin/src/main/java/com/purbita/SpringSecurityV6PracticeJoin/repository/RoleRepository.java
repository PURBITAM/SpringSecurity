package com.purbita.SpringSecurityV6PracticeJoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.SpringSecurityV6PracticeJoin.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
