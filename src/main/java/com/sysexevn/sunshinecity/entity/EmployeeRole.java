package com.sysexevn.sunshinecity.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(metamodel = @Metamodel)
@Table(name = "employee_role")
@Getter
@Setter
public class EmployeeRole {

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "employee_id")
	private Integer employeeId;

	public EmployeeRole(Integer roleId, Integer employeeId) {
		super();
		this.roleId = roleId;
		this.employeeId = employeeId;
	}

	public EmployeeRole() {

	}

}
