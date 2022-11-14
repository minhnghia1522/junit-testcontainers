package com.sysexevn.sunshinecity.domain;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.springframework.beans.BeanUtils;

import com.sysexevn.sunshinecity.dto.EmployeeDto;

import lombok.Data;

@Entity(metamodel = @Metamodel)
@Data
public class Role {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_name")
	private String roleName;


	public Role() {
		super();
	}

	public EmployeeDto toDto() {
		var result = new EmployeeDto();
		BeanUtils.copyProperties(this, result);
		return result;
	}

}
