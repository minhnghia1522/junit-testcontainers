package com.sysexevn.sunshinecity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.converter.EmployeeRoleConverter;
import com.sysexevn.sunshinecity.dao.IEmployeeRoleDao;
import com.sysexevn.sunshinecity.dto.EmployeeRoleDto;
import com.sysexevn.sunshinecity.entity.EmployeeRole;
import com.sysexevn.sunshinecity.service.IEmployeeRoleService;

@Service
public class EmployeeRoleServiceImpl implements IEmployeeRoleService {

	@Autowired
	private IEmployeeRoleDao employeeRoleDao;

	@Autowired
	private EmployeeRoleConverter converter;

	@Override
	public EmployeeRoleDto create(EmployeeRoleDto employeeRoleDto) {
		EmployeeRole entity = converter.convert(employeeRoleDto);
		employeeRoleDao.insert(entity);
		return employeeRoleDto;
	}

	@Override
	public void create(Integer roleId, Integer employeeId) {
		EmployeeRole entity = new EmployeeRole();
		entity.setEmployeeId(employeeId);
		entity.setRoleId(roleId);
		employeeRoleDao.insert(entity);
	}

//	@Override
//	public List<EmployeeRoleDto> saveAll(List<EmployeeRoleDto> employeeRoleDto) {
//		List<EmployeeRole> domain = employeeRoleDto.stream()
//				.map(x -> new EmployeeRole(x.getRoleId(), x.getEmployeeId())).toList();
//		BeanUtils.copyProperties(employeeRoleDto, domain);
//		BatchResult<EmployeeRole> result = employeeRoleDao.insertAll(domain);
//		return result.getEntities().stream().map(x -> x.toDto()).toList();
//	}

//	@Override
//	public EmployeeRoleDto update(EmployeeRoleDto employeeRoleDto) {
//		EmployeeRole domain = new EmployeeRole();
//		BeanUtils.copyProperties(employeeRoleDto, domain);
//		Result<EmployeeRole> result = employeeRoleDao.update(domain);
//		return result.getEntity().toDto();
//	}

}
