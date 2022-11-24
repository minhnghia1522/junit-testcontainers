package com.sysexevn.sunshinecity.service.impl;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.constants.enums.RoleEnum;
import com.sysexevn.sunshinecity.converter.EmployeeConverter;
import com.sysexevn.sunshinecity.dao.IEmployeeDao;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.dto.SignUpDto;
import com.sysexevn.sunshinecity.entity.Employee;
import com.sysexevn.sunshinecity.entity.Role;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.IEmployeeRoleService;
import com.sysexevn.sunshinecity.service.IEmployeeService;
import com.sysexevn.sunshinecity.service.IRoleService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	@Autowired
	private EmployeeConverter converter;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IEmployeeRoleService employeeRoleService;

	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee domain = converter.convert(employeeDto);
		domain.setPassword(encoder.encode(domain.getPassword()));
		return converter.convert(employeeDao.insert(domain).getEntity());
	}

	public List<EmployeeDto> saveAll(List<EmployeeDto> employees) {
		List<Employee> domain = converter.convertListToEntity(employees);
		return converter.convertListToDto(employeeDao.insertAll(domain).getEntities());
	}

	public EmployeeDto getById(Integer id) {
		Optional<Employee> employeeResult = employeeDao.findById(id);
		if (employeeResult.isEmpty()) {
			throw new NotFoundException();
		}
		return converter.convert(employeeResult.get());
	}

	public EmployeeDto getByUsername(String username) {
		Optional<Employee> employeeResult = employeeDao.findByUsername(username);
		if (employeeResult.isEmpty()) {
			throw new NotFoundException();
		}
		Employee employee = employeeResult.get();
		EmployeeDto dto = converter.convert(employee);
		return dto;
	}

	public List<EmployeeDto> getAll() {
		List<Employee> tt = employeeDao.findAllEmployee();
		List<EmployeeDto> listEmployeeDto = converter.convertListToDto(tt);
		return listEmployeeDto;
	}

	@Override
	public void signup(SignUpDto signUpDto) {
		// TODO check email user exists
		signUpDto.setPassword(encoder.encode(signUpDto.getPassword()));
		Employee employee = converter.convert(signUpDto);
		Result<Employee> result = employeeDao.insert(employee);

		// get Role
		Role role = roleService.getRoleByEnum(RoleEnum.USER);

		// create role for user
		employeeRoleService.create(role.getId(), result.getEntity().getId());
	}

	@Override
	public List<SimpleGrantedAuthority> authorities(EmployeeDto employee) {
		List<SimpleGrantedAuthority> authorities = employee.getRoles().stream().map(r -> new SimpleGrantedAuthority(r))
				.toList();
		return authorities;
	}

	@Override
	public EmployeeDto update(EmployeeDto employeeDto) {
		Employee employee = converter.convert(employeeDto);
		Result<Employee> result = employeeDao.update(employee);
		if (result.getCount() > 0) {
			return converter.convert(result.getEntity());
		}
		throw new NotFoundException();
	}

	@Override
	public EmployeeDto delete(EmployeeDto employeeDto) {
		Employee employee = converter.convert(employeeDto);

		Result<Employee> result = employeeDao.delete(employee);
		if (result.getCount() > 0) {
			return converter.convert(result.getEntity());
		}
		throw new NotFoundException();
	}
}
