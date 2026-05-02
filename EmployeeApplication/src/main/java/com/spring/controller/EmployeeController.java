package com.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.APIResponseDto;
import com.spring.model.Employee;
import com.spring.service.EmployeeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EmployeeController {

	private final @NonNull EmployeeService employeeService;

	// http://localhost:8090/saveEmployee

	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	// http://localhost:8090/getAllEmployees
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	// http://localhost:8090/getEmployeeById/2

	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable Long id) {
		return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
	}

//	// http://localhost:8090/updateEmployee/2
//	@PutMapping("/updateEmployee/{id}")
//	public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee, @PathVariable Long id) {
//		return new ResponseEntity<>(employeeService.updateEmployeeById(employee, id), HttpStatus.OK);
//	}

	// http://localhost:8090/deleteEmployeeById/2

	@DeleteMapping("/deleteEmployeeById/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<>("Employee Deleted successfully!..", HttpStatus.OK);
	}
}
