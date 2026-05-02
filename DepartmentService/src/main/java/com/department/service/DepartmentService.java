package com.department.service;

import java.util.List;

import com.department.model.Department;

public interface DepartmentService {

	public Department saveDepartment(Department department);

	public Department findByDepartmentCode(String departmentCode);

	public List<Department> getAllDepartments();

	public Department updateDepartmentByCode(Department department, String departmentCode);

	public String deleteDepartmentById(int id);

}
