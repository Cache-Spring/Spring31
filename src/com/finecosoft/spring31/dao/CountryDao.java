package com.finecosoft.spring31.dao;

import com.finecosoft.spring31.Country;

public interface CountryDao {
	int getCountryId(int employeeId);
	Country getCountry(int countryId);
}
