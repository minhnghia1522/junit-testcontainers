package com.sysexevn.sunshinecity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeRoleDto {
	private Integer employeeId;
	private Integer roleId;
}
