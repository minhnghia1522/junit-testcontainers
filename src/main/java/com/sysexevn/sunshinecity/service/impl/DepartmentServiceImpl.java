package com.sysexevn.sunshinecity.service.impl;

import java.util.List;

import org.seasar.doma.jdbc.BatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.converter.DepartmentConverter;
import com.sysexevn.sunshinecity.dao.IDepartmentDao;
import com.sysexevn.sunshinecity.dto.DepartmentDto;
import com.sysexevn.sunshinecity.entity.Department;
import com.sysexevn.sunshinecity.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	IDepartmentDao dao;

	@Autowired
	DepartmentConverter converter;

	@Override
	public List<DepartmentDto> findBySC961Id(Long sc961Id) {
		List<Department> departments = dao.findBySC961Id(sc961Id);
		return converter.convertListDto(departments);
	}
	
	@Override
	public BatchResult<Department> insertAll(List<Department> department) {
		return dao.insertAll(department);
	}

}
