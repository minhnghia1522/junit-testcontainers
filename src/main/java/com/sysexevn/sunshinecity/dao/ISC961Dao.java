package com.sysexevn.sunshinecity.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.entity.SC961;

@Dao
@ConfigAutowireable
@Transactional
public interface ISC961Dao {

	@Select
	@Sql("select * from sc961")
	List<SC961> findAll();
}
