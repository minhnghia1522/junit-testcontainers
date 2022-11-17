package com.sysexevn.sunshinecity.dao;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.domain.Employee;
import com.sysexevn.sunshinecity.domain.EmployeeRole_;
import com.sysexevn.sunshinecity.domain.Employee_;
import com.sysexevn.sunshinecity.domain.Role_;

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

	default Optional<Employee> findById(Integer id) {
		Employee_ employee = new Employee_();
		EmployeeRole_ employeeRole = new EmployeeRole_();
		Role_ role = new Role_();
		Entityql entityql = new Entityql(Config.get(this));
		List<Employee> list = entityql.from(employee)
				.innerJoin(employeeRole, on -> on.eq(employee.employeeId, employeeRole.employeeId))
				.innerJoin(role, on -> on.eq(employeeRole.roleId, role.roleId))
				.where(c -> c.eq(employee.employeeId, id)).associate(role, employeeRole, (a, b) -> {
					b.setRole(a.getRoleName());
				}).associate(employee, employeeRole, (a, b) -> {
					a.getEmployeeRole().add(b);
				}).fetch();
		if (list.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(list.get(0));
	}

	default Optional<Employee> findByEmail(String email) {
		Employee_ employee = new Employee_();
		EmployeeRole_ employeeRole = new EmployeeRole_();
		Role_ role = new Role_();
		Entityql entityql = new Entityql(Config.get(this));
		List<Employee> list = entityql.from(employee)
				.innerJoin(employeeRole, on -> on.eq(employee.employeeId, employeeRole.employeeId))
				.innerJoin(role, on -> on.eq(employeeRole.roleId, role.roleId)).where(c -> c.eq(employee.email, email))
				.associate(role, employeeRole, (a, b) -> {
					b.setRole(a.getRoleName());
				}).associate(employee, employeeRole, (a, b) -> {
					a.getEmployeeRole().add(b);
				}).fetch();
		if (list.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(list.get(0));
	}

	@Select
	List<Employee> findAllEmployee();
}
