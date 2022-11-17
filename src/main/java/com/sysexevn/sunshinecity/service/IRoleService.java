package com.sysexevn.sunshinecity.service;

import java.util.List;

import com.sysexevn.sunshinecity.domain.Role;
import com.sysexevn.sunshinecity.dto.RoleDto;

public interface IRoleService {

	RoleDto create(Role role);

	List<RoleDto> createAll(List<Role> role);

	RoleDto update(Role role);
}
