package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

	private Long departmentId;
	private Long departmentCode;
	private String departmentName;
	private String departmentDescription;
	private int departmentCount;
}
