package com.employee.controller;

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

import com.employee.model.APIResponse;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final @NonNull EmployeeService employeeService;

	// POST: http://localhost:2025/api/v1/employees/create

	@PostMapping("/create")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	// GET: http://localhost:2025/api/v1/employees/getEmployeeById/2

//	@GetMapping("/getEmployeeById/{id}")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
//		return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
//	}
	
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<APIResponse> getEmployeeById(@PathVariable int id) {
		return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
	}

	// GET: http://localhost:2025/api/v1/employees/getEmployees

	@GetMapping("/getEmployees")
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
	}

	// PUT: http://localhost:2025/api/v1/employees/updateEmployee/2
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee, @PathVariable int id) {
		return new ResponseEntity<>(employeeService.updateEmployeeById(employee, id), HttpStatus.OK);
	}
	
	// DELETE:  http://localhost:2025/api/v1/employees/deleteEmployeeById/3

	@DeleteMapping("/deleteEmployeeById/{id}")
	public void deleteEmployeeById(@PathVariable int id) {
		employeeService.deleteEmployeeById(id);
		ResponseEntity.ok("Employee deleted successfully");
	}

}
