package com.sysexevn.sunshinecity.dao;

import java.util.List;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.domain.Employee;

@Dao
@ConfigAutowireable
@Transactional
public interface IEmployeeDao {
	
	@Update
	int update(Employee employee);

	@Insert
	int insert(Employee employee);

	@BatchInsert
	int[] insertAll(List<Employee> employees);

	@Select
	Employee findById(Integer Id);

	@Select
	List<Employee> findAllEmployee();
}
