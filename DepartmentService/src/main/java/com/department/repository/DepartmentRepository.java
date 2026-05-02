package com.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.department.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
	public Department findByDepartmentCode(String departmentCode);

}
