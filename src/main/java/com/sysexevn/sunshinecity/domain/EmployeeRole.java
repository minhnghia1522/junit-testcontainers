package com.sysexevn.sunshinecity.domain;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;
import org.springframework.beans.BeanUtils;

import com.sysexevn.sunshinecity.dto.EmployeeDto;

import lombok.Data;

@Entity(metamodel = @Metamodel)
@Data
@Table(name = "employee_role")
public class EmployeeRole {

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "employee_id")
	private Integer employeeId;

	@Transient
	private String role;

	public EmployeeRole() {
		super();
	}

	public EmployeeDto toDto() {
		var result = new EmployeeDto();
		BeanUtils.copyProperties(this, result);
		return result;
	}

}
