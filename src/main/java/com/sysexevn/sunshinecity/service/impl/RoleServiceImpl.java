package com.sysexevn.sunshinecity.service.impl;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.constants.enums.RoleEnum;
import com.sysexevn.sunshinecity.converter.RoleConverter;
import com.sysexevn.sunshinecity.dao.IRoleDao;
import com.sysexevn.sunshinecity.dto.RoleDto;
import com.sysexevn.sunshinecity.entity.Role;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private RoleConverter converter;

	@Override
	public RoleDto create(Role role) {
		Result<Role> result = roleDao.insert(role);
		return converter.convert(result.getEntity());
	}

	@Override
	public List<RoleDto> createAll(List<Role> role) {
		BatchResult<Role> result = roleDao.insertAll(role);
		return converter.convert(result.getEntities());
	}

	@Override
	public RoleDto update(Role role) {
		Result<Role> result = roleDao.update(role);
		return converter.convert(result.getEntity());
	}

	@Override
	public Role getRoleByEnum(RoleEnum roleEnum) {
		Optional<Role> optional = roleDao.findByRoleName(roleEnum.name());
		if (optional.isEmpty()) {
			throw new NotFoundException();
		}
		return optional.get();
	}

}
