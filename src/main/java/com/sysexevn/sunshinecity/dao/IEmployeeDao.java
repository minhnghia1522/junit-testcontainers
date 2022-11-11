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
import com.sysexevn.sunshinecity.domain.Employee_;
import com.sysexevn.sunshinecity.domain.EmployeeRole_;
import com.sysexevn.sunshinecity.dto.EmployeeDto;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;

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

	default Employee findById(Integer id) {
		Employee_ employee = new Employee_();
		EmployeeRole_ employeeRole = new EmployeeRole_();
		Entityql entityql = new Entityql(Config.get(this));
		List<Employee> list = entityql.from(employee).innerJoin(employeeRole, on -> on.eq(employee.employeeId, employeeRole.employeeId))
				.where(c -> c.eq(employee.employeeId, id)).associate(employee, employeeRole, (a, b) -> {
					a.getEmployeeRole().add(b);
				}).fetch();
		return list.get(0);
	}

	@Select
	List<Employee> findAllEmployee();
}
