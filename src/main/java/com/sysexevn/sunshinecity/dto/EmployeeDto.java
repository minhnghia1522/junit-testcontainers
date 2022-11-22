package com.sysexevn.sunshinecity.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {

	private Integer id;

	private String fullName;

	private String username;

	private String position;

	private Date birthday;

	private String phone;

	private String department;
	
	private String password;
	
	private List<String> roles;
}
