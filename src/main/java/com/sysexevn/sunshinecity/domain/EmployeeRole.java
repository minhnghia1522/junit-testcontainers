package com.sysexevn.sunshinecity.domain;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;
import org.springframework.beans.BeanUtils;

import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.dto.EmployeeRoleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(metamodel = @Metamodel, immutable = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_role")
public class EmployeeRole {

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "employee_id")
	private Integer employeeId;

	@Transient
	private String role;

	public EmployeeRoleDto toDto() {
		var result = new EmployeeRoleDto();
		BeanUtils.copyProperties(this, result);
		return result;
	}

	public EmployeeRole(Integer roleId, Integer employeeId) {
		super();
		this.roleId = roleId;
		this.employeeId = employeeId;
	}

}
