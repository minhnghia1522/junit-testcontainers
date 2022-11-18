package com.sysexevn.sunshinecity.dao;

import java.util.List;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.entity.EmployeeRole;

@Dao
@ConfigAutowireable
@Transactional
public interface IEmployeeRoleDao {

	@Update
	int update(EmployeeRole employeeRole);

	@Insert
	int insert(EmployeeRole employeeRole);

	@BatchInsert
	int[] insertAll(List<EmployeeRole> employeeRoles);

	@Select
	List<EmployeeRole> findByEmployeeId(Integer employeeId);
}