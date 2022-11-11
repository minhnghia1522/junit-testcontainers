package com.sysexevn.sunshinecity.service;

import java.util.List;

import com.sysexevn.sunshinecity.domain.Employee;
import com.sysexevn.sunshinecity.dto.EmployeeDto;

public interface IEmployeeService {

	int createEmployeee(EmployeeDto employee);

	int[] saveAll(List<Employee> employees);

	EmployeeDto getById(Integer id);

	List<EmployeeDto> getAll();
}
