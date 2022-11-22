package com.sysexevn.sunshinecity.dao;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.entity.Menu;

@Dao
@ConfigAutowireable
@Transactional
public interface IMenuDao {

	@Update
	Result<Menu> update(Menu menu);

	@Insert
	Result<Menu> insert(Menu menu);

	@Delete(sqlFile = true)
	int delete(Integer Id);

	@Select
	Optional<Menu> findById(Integer Id);

	@Select
	List<Menu> findAllMenu();
	
	@BatchInsert
	BatchResult<Menu> insertAll(List<Menu> menu);

}
