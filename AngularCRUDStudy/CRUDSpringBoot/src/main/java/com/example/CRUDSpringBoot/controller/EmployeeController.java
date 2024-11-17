package com.example.CRUDSpringBoot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDSpringBoot.entity.Employee;
import com.example.CRUDSpringBoot.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/allEmployees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employee/{id}")
	public List<Employee> getEmployeeById(@PathVariable Integer id){
		Optional<Employee> employee = employeeRepository.findById(id);
		List<Employee> employees = new ArrayList<>();
		employees.add(employee.get());
		return employees;
	}
	
	@PostMapping("/addEmployee")
	public void addEmployee(@RequestBody Employee employee){
		employeeRepository.save(employee);
	}
	
	@PutMapping("/updateEmployee")
	public void updateEmployee(@RequestBody Employee employee){
		employeeRepository.save(employee);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable Integer id){
		employeeRepository.deleteById(id);
	}
	
}
