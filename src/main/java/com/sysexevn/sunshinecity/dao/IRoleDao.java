package com.sysexevn.sunshinecity.dao;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.entity.Role;

@Dao
@ConfigAutowireable
@Transactional
public interface IRoleDao {

	@Update
	Result<Role> update(Role role);

	@Insert
	Result<Role> insert(Role role);

	@BatchInsert
	BatchResult<Role> insertAll(List<Role> roles);

	@Select
	List<Role> findByRoleId(Integer roleId);

	@Select(fetchSize = 1)
	@Sql("select * from role where name = /* roleName*/0")
	Optional<Role> findByRoleName(String roleName);
}
