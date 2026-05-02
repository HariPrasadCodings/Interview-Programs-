package com.department.controller;

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

import com.department.model.Department;
import com.department.service.DepartmentService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

	private final @NonNull DepartmentService departmentService;

	// POST: http://localhost:2026/api/v1/departments/create

	@PostMapping("/create")
	public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
		return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.CREATED);
	}

	// GET: http://localhost:2026/api/v1/departments/getDepartmentByCode/BA006

	@GetMapping("/getDepartmentByCode/{code}")
	public ResponseEntity<Department> findByDepartmentCode(@PathVariable String code) {
		return new ResponseEntity<>(departmentService.findByDepartmentCode(code), HttpStatus.OK);
	}

	// GET: http://localhost:2026/api/v1/departments/getAllDepartments

	@GetMapping("/getAllDepartments")
	public ResponseEntity<List<Department>> getAllDepartments() {
		return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
	}

	// PUT: http://localhost:2026/api/v1/departments/updateDepartmentById/2

	@PutMapping("/updateDepartmentById/{departmentCode}")
	public ResponseEntity<Department> updateDepartmentById(@RequestBody Department department,
			@PathVariable String departmentCode) {
		return new ResponseEntity<>(departmentService.updateDepartmentByCode(department, departmentCode),
				HttpStatus.OK);
	}

	// DELETE: http://localhost:2026/api/v1/departments/deleteDepartmentById/2

	@DeleteMapping("/deleteDepartmentById/{id}")
	public ResponseEntity<String> deleteDepartmentById(@PathVariable int id) {
		departmentService.deleteDepartmentById(id);
		return ResponseEntity.ok("Department deleted successfully!..");
	}

}
