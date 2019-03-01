package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@Column(name = "employee_id")
	private String employee_id;
	
	@Column(name = "salary")
    private int salary;
	
	@Column(name = "manager_id")
    private String manager_id;

	
	
	public Employee() {
	}
    
	public Employee(String employee_id, int salary, String manager_id) {
		this.employee_id = employee_id;
		this.salary = salary;
		this.manager_id = manager_id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
}
