package com.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.rest.model.Employee;
import com.rest.service.EmployeeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final @NonNull EmployeeService employeeService;

	// http://localhost:2025/api/v1/employee/create

	@PostMapping("/create")
	public ResponseEntity<Employee> saveEmployee(
			@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(
				employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	// http://localhost:2025/api/v1/employee/getAllEmployees

	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(),
				HttpStatus.OK);
	}

	// http://localhost:2025/api/v1/employee/getEmployeeById/2

	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),
				HttpStatus.OK);
	}

	// http://localhost:2025/api/v1/employee/update/2

	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
			@RequestBody Employee employee) {
		return new ResponseEntity<>(
				employeeService.updateEmployee(id, employee), HttpStatus.OK);
	}

	// http://localhost:2025/api/v1/employee/delete/2

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return ResponseEntity
				.ok("Employee with ID " + id + " deleted successfully.");
	}
}
