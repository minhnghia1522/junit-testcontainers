package com.sysexevn.sunshinecity.service;

import java.util.List;

import org.seasar.doma.jdbc.BatchResult;

import com.sysexevn.sunshinecity.dto.DepartmentDto;
import com.sysexevn.sunshinecity.entity.Department;

public interface IDepartmentService {

	List<DepartmentDto> findBySC961Id(Long sc961Id);
	
	BatchResult<Department> insertAll(List<Department> department);
}
