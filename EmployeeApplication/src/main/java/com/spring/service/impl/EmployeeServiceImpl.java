package com.spring.service.impl;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.model.APIResponseDto;
import com.spring.model.Department;
import com.spring.model.Employee;
import com.spring.repository.EmployeeRepository;
import com.spring.service.EmployeeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final @NonNull EmployeeRepository employeeRepository;
	//private final @NonNull RestTemplate restTemplate;
	private final @NonNull WebClient webClient;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public APIResponseDto getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id).get();
//		ResponseEntity<Department> department = restTemplate.getForEntity(
//				"http://localhost:9080/getDepartmentByCode/" + employee.getDepartmentCode(), Department.class);
//		Department body = department.getBody();
		
		Department department = webClient.get().uri("http://localhost:9080/getDepartmentByCode/"+employee.getDepartmentCode())
		.retrieve()
		.bodyToMono(Department.class)
		.block();

		APIResponseDto responseDto = new APIResponseDto();
		responseDto.setEmployee(employee);
		responseDto.setDepartment(department);

		return responseDto;
	}

//	@Override
//	public Employee updateEmployeeById(Employee employee, Long id) {
//
//		Employee existingEmployee = getEmployeeById(id);
//
//		existingEmployee.setName(employee.getName());
//		existingEmployee.setSalary(employee.getSalary());
//		existingEmployee.setPhno(employee.getPhno());
//		existingEmployee.setEmail(employee.getEmail());
//		Employee updatedEmployee = employeeRepository.save(existingEmployee);
//
//		return updatedEmployee;
//	}

	@Override
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
	}

}
