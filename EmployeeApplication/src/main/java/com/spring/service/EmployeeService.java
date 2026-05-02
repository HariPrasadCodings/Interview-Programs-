package com.spring.service;

import java.util.List;

import com.spring.model.APIResponseDto;
import com.spring.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public APIResponseDto getEmployeeById(Long id);

	//public Employee updateEmployeeById(Employee employee, Long id);

	public void deleteEmployeeById(Long id);

}
