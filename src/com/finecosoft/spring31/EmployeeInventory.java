package com.finecosoft.spring31;

import java.util.ArrayList;
import java.util.List;

import com.finecosoft.spring31.dao.CountryDao;
import com.finecosoft.spring31.dao.EmployeeDAO;
import com.finecosoft.spring31.dao.LanguageDao;

public class EmployeeInventory {
	private EmployeeDAO employeeDao;
	private LanguageDao languageDao;
	private CountryDao countryDao;
	
	public List<Integer> getEmployeesId() {
		return employeeDao.getEmployeesIds();
	}
	
	public Employee getEmployee(int employeeId) {
		Employee employee = employeeDao.getEmployee(employeeId);
		int countryId = countryDao.getCountryId(employeeId);
		Country country = countryDao.getCountry(countryId);
		employee.setCountry(country);
		
		List<Language> languages = new ArrayList<Language>();
		List<Integer> ids = languageDao.getLanguagesIds(employeeId);
		for (Integer id : ids) {
			Language language = languageDao.getLanguage(id);
			languages.add(language);
		}
		employee.setLanguages(languages);
		
		return employee;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		List<Integer> ids = employeeDao.getEmployeesIds();
		for (Integer id : ids) {
			Employee employee = getEmployee(id);
			employees.add(employee);
		}
		
		return employees;
	}

	public void setLanguageDao(LanguageDao languageDao) {
		this.languageDao = languageDao;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}
}
