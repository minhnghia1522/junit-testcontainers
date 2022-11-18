package com.sysexevn.sunshinecity.dao;

import java.util.List;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.domain.EmployeeRole;

@Dao
@ConfigAutowireable
@Transactional
public interface IEmployeeRoleDao {

//	@Update
//	Result<EmployeeRole> update(EmployeeRole employeeRole);

	@Insert
	Result<EmployeeRole> insert(EmployeeRole employeeRole);

	@BatchInsert
	BatchResult<EmployeeRole> insertAll(List<EmployeeRole> employeeRoles);

	@Select
	List<EmployeeRole> findByEmployeeId(Integer employeeId);
}