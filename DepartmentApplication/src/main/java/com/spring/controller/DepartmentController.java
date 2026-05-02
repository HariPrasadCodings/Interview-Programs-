package com.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Department;
import com.spring.service.DepartmentService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DepartmentController {

	private final @NonNull DepartmentService departmentService;

	// http://localhost:9080/createDepartment

	@PostMapping("/createDepartment")
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.CREATED);
	}

	// http://localhost:9080/getDepartmentByCode/JV001

	@GetMapping("/getDepartmentByCode/{departmentCode}")
	public ResponseEntity<Department> getDepartmentByCode(@PathVariable Long departmentCode) {
		return new ResponseEntity<>(departmentService.getDepartmentByCode(departmentCode), HttpStatus.OK);
	}

}
