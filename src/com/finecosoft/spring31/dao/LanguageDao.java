package com.finecosoft.spring31.dao;

import java.util.List;

import com.finecosoft.spring31.Language;

public interface LanguageDao {
	List<Integer> getLanguagesIds(int employeeId);
	Language getLanguage(int languageId);
}
