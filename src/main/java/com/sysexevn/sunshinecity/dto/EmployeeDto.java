package com.sysexevn.sunshinecity.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {
	private Integer employeeId;
	private String fullName;
	private String email;
	private String position;
	private Date birthday;
	private String phone;
	private String department;
}
