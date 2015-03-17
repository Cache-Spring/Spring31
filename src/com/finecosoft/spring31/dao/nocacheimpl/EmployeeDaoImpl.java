package com.finecosoft.spring31.dao.nocacheimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.finecosoft.spring31.Employee;
import com.finecosoft.spring31.dao.EmployeeDAO;

public class EmployeeDaoImpl implements EmployeeDAO {
	private JdbcTemplate jdbcTemplate;
	
	private static final String EMPLOYEE_ID_SELECT =
		"select emp_id from employee";
	private static final String EMPLOYEE_BY_EMPLOYEE_ID_SELECT =
		"select emp_id, emp_surname, emp_firstname from employee where emp_id=?";

	@Override
	public List<Integer> getEmployeesIds() {
		List<Integer> ids = jdbcTemplate.query(EMPLOYEE_ID_SELECT,
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Integer integer = rs.getInt(1);
						return integer;
					}
				});
		return ids;
	}

	@Override
	public Employee getEmployee(int employeeId) {
		List<Employee> employee = jdbcTemplate.query(EMPLOYEE_BY_EMPLOYEE_ID_SELECT,
				new Object[] { Long.valueOf(employeeId) },
				new RowMapper<Employee>() {
					@Override
					public Employee mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Employee employee = new Employee();
						employee.setId(rs.getLong(1));
						employee.setSurname(rs.getString(2));
						employee.setFirstname(rs.getString(3));
						return employee;
					}
				});

		return employee.size() > 0 ? employee.get(0) : null;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
