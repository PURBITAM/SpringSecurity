package com.purbita.SpringSecurityV6Practice8.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.purbita.SpringSecurityV6Practice8.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
