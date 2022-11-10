package com.example.demo.dto;

import java.util.Date;

public class EmployeeDto {
	public EmployeeDto() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeDto(Integer employeeId, String fullName, String email, String position, Date birthday, String phone,
			String department) {
		super();
		this.employeeId = employeeId;
		this.fullName = fullName;
		this.email = email;
		this.position = position;
		this.birthday = birthday;
		this.phone = phone;
		this.department = department;
	}

	public Integer employeeId;
	public String fullName;
	public String email;
	public String position;
	public Date birthday;
	public String phone;
	public String department;
}
