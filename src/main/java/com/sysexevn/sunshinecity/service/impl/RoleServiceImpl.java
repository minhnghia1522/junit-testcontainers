package com.sysexevn.sunshinecity.service.impl;

import java.util.List;

import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.dao.IRoleDao;
import com.sysexevn.sunshinecity.domain.Role;
import com.sysexevn.sunshinecity.dto.RoleDto;
import com.sysexevn.sunshinecity.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@Override
	public RoleDto create(Role role) {
		Result<Role> result = roleDao.insert(role);
		return result.getEntity().toDto();
	}

	@Override
	public List<RoleDto> createAll(List<Role> role) {
		BatchResult<Role> result = roleDao.insertAll(role);
		return result.getEntities().stream().map(x->x.toDto()).toList();
	}

	@Override
	public RoleDto update(Role role) {
		Result<Role> result = roleDao.update(role);
		return result.getEntity().toDto();
	}

}
