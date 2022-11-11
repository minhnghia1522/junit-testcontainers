package com.sysexevn.sunshinecity.domain;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.springframework.beans.BeanUtils;

import com.sysexevn.sunshinecity.dto.EmployeeDto;

import lombok.Data;

@Entity
@Data
public class Employee {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Employee() {
		super();
	}

	public EmployeeDto toDto() {
		var result = new EmployeeDto();
		BeanUtils.copyProperties(this, result);
		return result;
	}

}
