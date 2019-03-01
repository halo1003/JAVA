package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeService empService;

	// Get all EMPLOYEE in database
	@RequestMapping(value = "/employee", method = RequestMethod.GET, produces = "application/json")
	public List<Employee> getListEmployee() {
		return empService.getListEmployee();
	}

	// Find EMPLOYEE by ID
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findEmployeeByID(@PathVariable("id") String employee_id) {
		Object result;
		HttpStatus status;

		Optional<Employee> employee = empService.findEmployeeByID(employee_id);

		if (employee.isPresent()) {
			result = employee.get();
			status = HttpStatus.OK;
		} else {
			result = "find employee fail!!!";
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<Object>(result, status);
	}
}
