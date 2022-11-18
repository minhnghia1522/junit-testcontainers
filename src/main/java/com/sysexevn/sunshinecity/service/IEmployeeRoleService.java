package com.sysexevn.sunshinecity.service;

import com.sysexevn.sunshinecity.dto.EmployeeRoleDto;

public interface IEmployeeRoleService {

	EmployeeRoleDto create(EmployeeRoleDto employeeRole);

	void create(Integer roleId, Integer employeeId);

//	List<EmployeeRoleDto> saveAll(List<EmployeeRoleDto> employeeRoles);
//	
//	EmployeeRoleDto update(EmployeeRoleDto employeeRole);
}
