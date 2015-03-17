package com.finecosoft.spring31.dao.cacheipml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.finecosoft.spring31.Country;
import com.finecosoft.spring31.dao.CountryDao;

public class CountryDaoImpl implements CountryDao {
	private JdbcTemplate jdbcTemplate;

	private static final String COUNTRY_ID_BY_EMPLOYEE_ID_SELECT =
		"select cn_id from employee where emp_id=?";
	private static final String COUNTRY_BY_COUNTRY_ID_SELECT =
		"select cn_id, cn_code, cn_name from country where cn_id=?";

	@Override
	public int getCountryId(int employeeId) {
		List<Integer> ids = jdbcTemplate.query(COUNTRY_ID_BY_EMPLOYEE_ID_SELECT,
				new Object[] { Long.valueOf(employeeId) },
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Integer id = rs.getInt(1);
						return id;
					}
				});

		return ids.size() > 0 ? ids.get(0) : null;
	}

	@Override
	@Cacheable("countries")
	public Country getCountry(int countryId) {
		List<Country> countries = jdbcTemplate.query(COUNTRY_BY_COUNTRY_ID_SELECT,
				new Object[] { Long.valueOf(countryId) },
				new RowMapper<Country>() {
					@Override
					public Country mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Country country = new Country();
						country.setId(rs.getLong(1));
						country.setCode(rs.getString(2));
						country.setName(rs.getString(3));
						return country;
					}
				});

		return countries.size() > 0 ? countries.get(0) : null;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
