package com.sysexevn.sunshinecity.service;

import java.util.List;

import com.sysexevn.sunshinecity.constants.enums.RoleEnum;
import com.sysexevn.sunshinecity.dto.RoleDto;
import com.sysexevn.sunshinecity.entity.Role;

public interface IRoleService {

	RoleDto create(Role role);

	List<RoleDto> createAll(List<Role> role);

	RoleDto update(Role role);

	Role getRoleByEnum(RoleEnum roleEnum);
}
