package com.rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rest.model.Employee;
import com.rest.repository.EmployeeRepository;
import com.rest.service.EmployeeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final @NonNull EmployeeRepository repository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Employee updateEmployee(Long id, Employee updateEmployee) {
		Employee existingEmployee = getEmployeeById(id);
		existingEmployee.setName(updateEmployee.getName());
		existingEmployee.setSalary(updateEmployee.getSalary());
		existingEmployee.setPhno(updateEmployee.getPhno());
		existingEmployee.setEmail(updateEmployee.getEmail());
		return repository.save(existingEmployee);
	}

	@Override
	public void deleteEmployeeById(Long id) {
       if(!repository.existsById(id)) {
    	   throw new RuntimeException("Cannot delete. Employee not found with id: " + id);
       }
		repository.deleteById(id);
	}

}
