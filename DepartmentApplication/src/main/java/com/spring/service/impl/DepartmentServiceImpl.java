package com.spring.service.impl;

import org.springframework.stereotype.Service;

import com.spring.model.Department;
import com.spring.repository.DepartmentRepository;
import com.spring.service.DepartmentService;

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

	@Override
	public Department getDepartmentByCode(Long departmentCode) {
		return departmentRepository.findByDepartmentCode(departmentCode);
	}

}
