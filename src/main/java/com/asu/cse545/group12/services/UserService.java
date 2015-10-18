package com.asu.cse545.group12.services;

import java.util.List;

import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;

public interface UserService {
	public int insertRow(Users user, UserPII userpii);
	public int insertRow(Users user);
	public boolean isUserEnabled(Users user);
	public Users getUserByUserName(String username);
	public List<Users> getUsersByFirstName(String firstName);
	public List<Users> getUsersByLastName(String lastName);
	public List<Users> getUsersByPhoneNumber(String phoneNumber);
	public List<Users> getUsersByEmailId(String emailId);
	public Users getUsersByAccountNumber(int accountNumber);
	public int updateRow(Users user);
	public int deActivateUserByUserId(int userId);
	public int deActivateUserByUserName(String userName);
	public int insertRowForEmployee(Users user, UserPII userpii);
	public int updateRowForOTP(Users user);

/*	public List<User> getList();

	public User getRowById(int id);

	

	public int deleteRow(int id);*/

}
