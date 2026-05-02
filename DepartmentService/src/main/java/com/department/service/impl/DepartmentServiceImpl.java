package com.department.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.department.model.Department;
import com.department.repository.DepartmentRepository;
import com.department.service.DepartmentService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private final @NonNull DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department findByDepartmentCode(String departmentCode) {
		return departmentRepository.findByDepartmentCode(departmentCode);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Department updateDepartmentByCode(Department department, String departmentCode) {

		Department existingDepartment = findByDepartmentCode(departmentCode);
		existingDepartment.setDepartmentName(department.getDepartmentName());
		existingDepartment.setDepartmentDescription(department.getDepartmentDescription());
		existingDepartment.setDepartmentStrength(department.getDepartmentStrength());
		existingDepartment.setDepartmentCode(department.getDepartmentCode());

		return departmentRepository.save(existingDepartment);

	}

	@Override
	public String deleteDepartmentById(int id) {
		departmentRepository.deleteById(id);
		return "Department deleted successfully!..";
	}

}
