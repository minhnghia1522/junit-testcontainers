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
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.domain.Menu;

@Dao
@ConfigAutowireable
@Transactional
public interface IMenuDao {
	
	@Update
	int update(Menu menu);

	@Insert
	int insert(Menu menu);
	
	@Delete
    int delete(Menu menu);

	@BatchInsert
	int[] insertAll(List<Menu> menu);

	@Select
	Optional<Menu> findById(Integer Id);

	@Select
	List<Menu> findAllMenu();
	

}
