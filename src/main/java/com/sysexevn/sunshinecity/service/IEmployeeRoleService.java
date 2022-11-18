package com.sysexevn.sunshinecity.service;

import java.util.List;

import com.sysexevn.sunshinecity.dto.EmployeeRoleDto;

public interface IEmployeeRoleService {

	EmployeeRoleDto create(EmployeeRoleDto employeeRole);

	List<EmployeeRoleDto> saveAll(List<EmployeeRoleDto> employeeRoles);
	
	EmployeeRoleDto update(EmployeeRoleDto employeeRole);
}
