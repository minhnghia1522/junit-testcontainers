package com.sysexevn.sunshinecity.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.entity.SC961;

@Dao
@ConfigAutowireable
@Transactional
public interface ISC961Dao {

	@Select
	@Sql("select * from sc961")
	List<SC961> findAll();
	
	@Select
	@Sql("select * from sc961 where id = /*id*/0")
	SC961 findById(Long id);
	
	@Insert
	Result<SC961> insert(SC961 sc961);
	
	@Update
	Result<SC961> update(SC961 sc961);
}
