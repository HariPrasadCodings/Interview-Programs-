package com.rest.service;

import java.util.List;

import com.rest.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(Long id);
	
	public Employee updateEmployee(Long id, Employee employee);
	
	public void deleteEmployeeById(Long id);

}
