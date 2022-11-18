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
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.entity.Employee;
import com.sysexevn.sunshinecity.entity.EmployeeRole_;
import com.sysexevn.sunshinecity.entity.Employee_;
import com.sysexevn.sunshinecity.entity.Role_;

@Dao
@ConfigAutowireable
@Transactional
public interface IEmployeeDao {

	@Update
	Result<Employee> update(Employee employee);

	@Insert
	Result<Employee> insert(Employee employee);

	@BatchInsert
	BatchResult<Employee> insertAll(List<Employee> employees);

	@Select(fetchSize = 1)
	@Sql("select * from employee where id = /*id*/0")
	Optional<Employee> findById(Integer id);

	default Optional<Employee> findByUsername(String username) {
		Employee_ employee = new Employee_();
		EmployeeRole_ employeeRole = new EmployeeRole_();
		Role_ role = new Role_();
		Entityql entityql = new Entityql(Config.get(this));
		List<Employee> list = entityql.from(employee)//
				.innerJoin(employeeRole, on -> on.eq(employee.id, employeeRole.employeeId))//
				.innerJoin(role, on -> on.eq(employeeRole.roleId, role.id))//
				.where(c -> c.eq(employee.username, username))//
				.associate(employee, employeeRole, (a, b) -> {
					a.getEmployeeRole().add(b);
				}).associate(employee, role, (e, r) -> {
					e.getRoles().add(r.getName());
				}).fetch();
		if (list.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(list.get(0));
	}

	@Select
	List<Employee> findAllEmployee();
}
