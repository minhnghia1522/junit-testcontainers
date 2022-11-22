package com.sysexevn.sunshinecity.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.dto.SignUpDto;
import com.sysexevn.sunshinecity.entity.Employee;

public interface IEmployeeService {

	EmployeeDto createEmployee(EmployeeDto employee);

	List<EmployeeDto> saveAll(List<EmployeeDto> employees);

	EmployeeDto getById(Integer id);

	List<EmployeeDto> getAll();
	
	EmployeeDto update(EmployeeDto employee);
	
	EmployeeDto delete(EmployeeDto employee);

	EmployeeDto getByUsername(String username);

	void signup(SignUpDto signUpDto);
	
	List<SimpleGrantedAuthority> authorities(EmployeeDto employee);
}
