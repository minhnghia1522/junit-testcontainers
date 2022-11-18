package com.sysexevn.sunshinecity.service;

import java.util.List;

import org.seasar.doma.jdbc.Result;

import com.sysexevn.sunshinecity.domain.Employee;
import com.sysexevn.sunshinecity.dto.EmployeeDto;

public interface IEmployeeService  {

	EmployeeDto createEmployee(EmployeeDto employee);

	List<Employee> saveAll(List<Employee> employees);

	EmployeeDto getById(Integer id);

	List<EmployeeDto> getAll();

	EmployeeDto getByEmail(String username);
}
