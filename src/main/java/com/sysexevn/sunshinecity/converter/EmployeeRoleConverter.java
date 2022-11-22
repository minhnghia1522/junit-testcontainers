package com.sysexevn.sunshinecity.converter;

import org.mapstruct.Mapper;

import com.sysexevn.sunshinecity.dto.EmployeeRoleDto;
import com.sysexevn.sunshinecity.entity.EmployeeRole;

@Mapper(componentModel = "spring")
public interface EmployeeRoleConverter {

	EmployeeRoleDto convert(EmployeeRole employeeRole);

	EmployeeRole convert(EmployeeRoleDto employeeRole);
}
