package com.sysexevn.sunshinecity.dto;

import java.util.Date;
import java.util.List;

import com.sysexevn.sunshinecity.domain.EmployeeRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {

	private Integer employeeId;

	private String fullName;

	private String email;

	private String position;

	private Date birthday;

	private String phone;

	private String department;

	private List<EmployeeRole> employeeRole;
}
