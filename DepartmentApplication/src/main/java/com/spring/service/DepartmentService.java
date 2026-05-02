package com.spring.service;

import com.spring.model.Department;

public interface DepartmentService {

	public Department saveDepartment(Department department);

	public Department getDepartmentByCode(Long departmentCode);

}
