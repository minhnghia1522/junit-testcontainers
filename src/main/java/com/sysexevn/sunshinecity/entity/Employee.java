package com.sysexevn.sunshinecity.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(metamodel = @Metamodel, immutable = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "username")
	private String username;

	@Column(name = "position")
	private String position;

	@Column(name = "birth_day")
	private Date birthday;

	@Column(name = "phone")
	private String phone;

	@Column(name = "department")
	private String department;

	@Column(name = "pass_word")
	private String password;

	@Transient
	private final List<EmployeeRole> employeeRole = new ArrayList<>();

}
