package com.finecosoft.spring31.dao;

import java.util.List;

import com.finecosoft.spring31.Employee;

public interface EmployeeDAO {
	List<Integer> getEmployeesIds();
	Employee getEmployee(int employeeId);
}
