package com.employee.service;

import java.util.List;

import com.employee.model.APIResponse;
import com.employee.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);

	public APIResponse getEmployeeById(int id);

	public List<Employee> getEmployees();
	
	public Employee updateEmployeeById(Employee employee, int id);
	
	public void deleteEmployeeById(int id);

}
