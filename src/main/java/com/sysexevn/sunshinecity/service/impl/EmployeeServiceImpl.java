package com.sysexevn.sunshinecity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.converter.EmployeeConverter;
import com.sysexevn.sunshinecity.dao.IEmployeeDao;
import com.sysexevn.sunshinecity.domain.Employee;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	@Autowired
	private EmployeeConverter converter;

	public EmployeeDto createEmployeee(EmployeeDto employeeDto) {
		Employee domain = converter.convert(employeeDto);
		EmployeeDto result = converter.convert(domain);
		return result;
	}

	public List<Employee> saveAll(List<Employee> employees) {
		return employeeDao.insertAll(employees).getEntities();
	}

	public EmployeeDto getById(Integer id) {
		Optional<Employee> employeeResult = employeeDao.findById(id);
		if (employeeResult.isEmpty()) {
			throw new NotFoundException();
		}
		return converter.convert(employeeResult.get());
	}

	public List<EmployeeDto> getAll() {
		List<EmployeeDto> listEmployeeDto = new ArrayList<>();
		employeeDao.findAllEmployee().forEach(employee -> listEmployeeDto.add(converter.convert(employee)));
		return listEmployeeDto;
	}
}
