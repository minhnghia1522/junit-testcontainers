package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import com.example.demo.repository.IEmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private IEmployeeRepository repository;

	public int createEmployeee(Employee employee) {
		return repository.insert(employee);
	}

	public int[] saveAll(List<Employee> employees) {
		return repository.insertAll(employees);
	}

	public EmployeeDto getById(Integer id) {
		Employee employeeResult = repository.findById(id);
		return employeeResult.toDto();
	}

	public List<EmployeeDto> getAll() {
		List<EmployeeDto> listEmployeeDto = new ArrayList<>();
		repository.findAllEmployee().forEach(employee -> listEmployeeDto.add(employee.toDto()));
		return listEmployeeDto;
	}
}
