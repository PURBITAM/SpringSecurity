package com.purbita.springSecurityT4Nov24.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purbita.springSecurityT4Nov24.models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
