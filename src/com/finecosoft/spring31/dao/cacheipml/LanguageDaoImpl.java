package com.finecosoft.spring31.dao.cacheipml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.finecosoft.spring31.Language;
import com.finecosoft.spring31.dao.LanguageDao;

public class LanguageDaoImpl implements LanguageDao {
	private JdbcTemplate jdbcTemplate;
	
	private static final String LANGUAGE_ID_BY_EMPLOYEE_ID_SELECT =
		"select lan_id from employee_speaks_language where emp_id=?";
	private static final String LANGUAGE_BY_LANGUAGE_ID_SELECT =
		"select lan_id, lan_code, lan_name from spoken_language where lan_id=?";

	@Override
	public List<Integer> getLanguagesIds(int employeeId) {
		List<Integer> ids = jdbcTemplate.query(LANGUAGE_ID_BY_EMPLOYEE_ID_SELECT,
				new Object[] { Long.valueOf(employeeId) },
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
	@Cacheable("languages")
	public Language getLanguage(int languageId) {
		List<Language> languages = jdbcTemplate.query(LANGUAGE_BY_LANGUAGE_ID_SELECT,
				new Object[] { Long.valueOf(languageId) },
				new RowMapper<Language>() {
					@Override
					public Language mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Language language = new Language();
						language.setId(rs.getLong(1));
						language.setCode(rs.getString(2));
						language.setName(rs.getString(3));
						return language;
					}
				});

		return languages.size() > 0 ? languages.get(0) : null;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
