package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;
	
	public List<Employee> getListEmployee(){
		return empRepo.findAll();
	}
	
	public Optional<Employee> findEmployeeByID(String employee_id) {
		return empRepo.findById(employee_id);
	}
}
