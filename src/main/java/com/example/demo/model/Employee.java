package com.example.demo.model;


import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import com.example.demo.dto.EmployeeDto;



@Entity
public class Employee {

	public Employee() {
		super();
	}

	public Employee(Integer employeeId, String fullName, String email, String position, Date birthday, String phone,
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

	@Id
	@Column(name = "employee_id")
	private Integer employeeId;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "email")
	private String email;
	@Column(name = "position")
	private String position;
	@Column(name = "birth_day")
	private Date birthday;
	@Column(name = "phone")
	private String phone;
	@Column(name = "department")
	private String department;

	public EmployeeDto toDto() {
		return new EmployeeDto(employeeId, fullName, email, position, birthday, phone, department);
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
