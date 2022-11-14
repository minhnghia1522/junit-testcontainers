package com.sysexevn.sunshinecity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.dao.IEmployeeDao;
import com.sysexevn.sunshinecity.domain.Employee;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	public int createEmployeee(EmployeeDto employeeDto) {
		Employee domain = new Employee();
		BeanUtils.copyProperties(employeeDto, domain);
		return employeeDao.insert(domain);
	}

	public int[] saveAll(List<Employee> employees) {
		return employeeDao.insertAll(employees);
	}

	public EmployeeDto getById(Integer id) {
		Optional<Employee> employeeResult = employeeDao.findById(id);
		if (employeeResult.isEmpty()) {
			throw new RuntimeException(" not found");
		}
		return employeeResult.get().toDto();
	}

	public EmployeeDto getByEmail(String email) {
		Optional<Employee> employeeResult = employeeDao.findByEmail(email);
		if (employeeResult.isEmpty()) {
			throw new RuntimeException(" not found");
		}
		return employeeResult.get().toDto();
	}

	public List<EmployeeDto> getAll() {
		List<EmployeeDto> listEmployeeDto = new ArrayList<>();
		employeeDao.findAllEmployee().forEach(employee -> listEmployeeDto.add(employee.toDto()));
		return listEmployeeDto;
	}
}
