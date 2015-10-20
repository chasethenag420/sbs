package com.asu.cse545.group12.dao;


import com.asu.cse545.group12.domain.SecurityQuestions;
import com.asu.cse545.group12.domain.Users;

public interface SecurityQuestionsDao {

	public int insertRow(SecurityQuestions securityQuestions);
	public SecurityQuestions getRowById(int id);
	public int updateRow(SecurityQuestions securityQuestions);
	public SecurityQuestions getSecurityQuestionsByUserId(int userId);
	
	
}
