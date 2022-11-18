package com.sysexevn.sunshinecity.domain;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.springframework.beans.BeanUtils;

import com.sysexevn.sunshinecity.dto.RoleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(metamodel = @Metamodel, immutable = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	@Id
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_name")
	private String roleName;

	public RoleDto toDto() {
		var result = new RoleDto();
		BeanUtils.copyProperties(this, result);
		return result;
	}
}
