package com.sysexevn.sunshinecity.service.impl;

import java.util.List;

import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.converter.DepartmentConverter;
import com.sysexevn.sunshinecity.converter.SC961Converter;
import com.sysexevn.sunshinecity.dao.ISC961Dao;
import com.sysexevn.sunshinecity.dto.DepartmentDto;
import com.sysexevn.sunshinecity.dto.SC961Dto;
import com.sysexevn.sunshinecity.dto.SC961Filter;
import com.sysexevn.sunshinecity.entity.Department;
import com.sysexevn.sunshinecity.entity.SC961;
import com.sysexevn.sunshinecity.service.IDepartmentService;
import com.sysexevn.sunshinecity.service.ISC961Service;

@Service
public class ISC961ServiceImpl implements ISC961Service {

	@Autowired
	private ISC961Dao isc961Dao;

	@Autowired
	private SC961Converter converter;

	@Autowired
	IDepartmentService departmentService;

	@Autowired
	DepartmentConverter departmentConverter;

	@Override
	public List<SC961Dto> search(SC961Filter filter) {
		List<SC961> list = isc961Dao.findAll();
		return converter.convert(list);
	}

	@Override
	public SC961Dto findById(Long id) {
		SC961 sc961 = isc961Dao.findById(id);
		SC961Dto dto = converter.convert(sc961);
		List<DepartmentDto> departmentDtos = departmentService.findBySC961Id(sc961.getId());
		dto.setDepartmentDtos(departmentDtos);
		return dto;
	}

	@Override
	public void create(SC961Dto dto) {
		SC961 sc961 = converter.convert(dto);
		Result<SC961> saveResult = isc961Dao.insert(sc961);

		List<Department> departments = departmentConverter.convertListEntity(dto.getDepartmentDtos());
		departments.forEach(deparment -> {
			deparment.setId(null);
			deparment.setSc961Id(saveResult.getEntity().getId());
		});
		departmentService.insertAll(departments);
	}

	@Override
	public void update(SC961Dto dto) {
		SC961 sc961 = converter.convert(dto);
		
		Result<SC961> saveResult = isc961Dao.update(sc961);

		List<Department> departments = departmentConverter.convertListEntity(dto.getDepartmentDtos());
		departments.forEach(deparment -> {
			deparment.setId(null);
			deparment.setSc961Id(saveResult.getEntity().getId());
		});
		departmentService.insertAll(departments);
	}

}
