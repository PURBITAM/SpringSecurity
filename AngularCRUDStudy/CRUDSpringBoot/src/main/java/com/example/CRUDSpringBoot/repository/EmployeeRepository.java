package com.example.CRUDSpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.CRUDSpringBoot.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByEmpEmail(String empEmail);
	List<Employee> findByEmpName(String empName);
}
