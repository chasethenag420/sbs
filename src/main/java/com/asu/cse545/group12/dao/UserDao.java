package com.asu.cse545.group12.dao;

import java.util.List;

import com.asu.cse545.group12.domain.Users;

public interface UserDao {
	public int insertRow(Users user);

	//public List<Users> getList();

	public Users getRowById(int id);

	public int updateRow(Users user);

	//public int deleteRow(int id);
	public Users getUserByUserName(String username);
	public Users getUserByUserId(int userId);
	public List<Users> getUsersByFirstName(String firstName);
	public List<Users> getUsersByLastName(String lastName);
	public List<Users> getUsersByPhoneNumber(String phoneNumber);
	public List<Users> getUsersByEmailId(String emailId);
}
