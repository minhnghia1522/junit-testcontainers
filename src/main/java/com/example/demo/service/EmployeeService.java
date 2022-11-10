package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import com.example.demo.repository.IEmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private IEmployeeRepository repository;

	public EmployeeDto createEmployeee(Employee employee) {
		return repository.save(employee).toDto();
	}

	public List<EmployeeDto> saveAll(List<Employee> employees) {
		List<EmployeeDto> listEmployeeDto = new ArrayList<>();
		repository.saveAll(employees).forEach(employee -> listEmployeeDto.add(employee.toDto()));
		return listEmployeeDto;
	}

	public EmployeeDto getById(Integer id) {
		Employee employeeResult = repository.findById(id).orElseThrow();
		return employeeResult.toDto();
	}

	public List<EmployeeDto> getAll() {
		List<EmployeeDto> listEmployeeDto = new ArrayList<>();
		repository.findAll().forEach(employee -> listEmployeeDto.add(employee.toDto()));
		return listEmployeeDto;
	}
}
