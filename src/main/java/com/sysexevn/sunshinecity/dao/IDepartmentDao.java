package com.sysexevn.sunshinecity.dao;

import java.util.List;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.BatchResult;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.entity.Department;

@Dao
@ConfigAutowireable
@Transactional
public interface IDepartmentDao {

	@Select(fetchSize = 1)
	@Sql("select * from department where sc961_id = /*sc961id*/0")
	List<Department> findBySC961Id(Long sc961id);
	
	@BatchInsert
	BatchResult<Department> insertAll(List<Department> departments);
}
