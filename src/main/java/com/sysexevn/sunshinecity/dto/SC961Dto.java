package com.sysexevn.sunshinecity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SC961Dto {

	private Long id;
	private String affiliationCompany;
	private String roll;
	private String employeeNumber;
	private String dept;
	private String userCode;
	private String containRetiree;
	private String userNameKana;
	
	private List<DepartmentDto> departmentDtos;
	
}
