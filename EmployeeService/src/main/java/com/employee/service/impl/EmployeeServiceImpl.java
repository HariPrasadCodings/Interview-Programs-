package com.employee.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employee.model.APIResponse;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final @NonNull EmployeeRepository repository;

	//private final @NonNull RestTemplate restTemplate;
	
	private final @NonNull WebClient webClient;

	@Override
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public APIResponse getEmployeeById(int id) {

		Employee employee = repository.findById(id).get();

//		ResponseEntity<Department> department = restTemplate.getForEntity(
//				"http://localhost:2026/api/v1/departments/getDepartmentByCode/" + employee.getDepartmentCode(),
//				Department.class);
		
		Department department = webClient.get().uri("http://localhost:2026/api/v1/departments/getDepartmentByCode/"+employee.getDepartmentCode())
		.retrieve().bodyToMono(Department.class).block();

		APIResponse apiResponse = new APIResponse();

		apiResponse.setEmployee(employee);
		apiResponse.setDepartment(department);

		return apiResponse;
	}

	@Override
	public List<Employee> getEmployees() {
		return repository.findAll();
	}

//	@Override
//	public Employee updateEmployeeById(Employee employee, int id) {
//		Employee existingEmployee = getEmployeeById(id);
//
//		existingEmployee.setName(employee.getName());
//		existingEmployee.setSalary(employee.getSalary());
//		existingEmployee.setPhno(employee.getPhno());
//		existingEmployee.setEmail(employee.getEmail());
//
//		return repository.save(existingEmployee);
//	}

	@Override
	public void deleteEmployeeById(int id) {
		repository.deleteById(id);
	}

	@Override
	public Employee updateEmployeeById(Employee employee, int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
