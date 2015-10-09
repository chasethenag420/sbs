package com.asu.cse545.group12.services;

import java.util.List;

import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;

public interface UserService {
	public int insertRow(Users user);
	public boolean isUserEnabled(Users user);

/*	public List<User> getList();

	public User getRowById(int id);

	public int updateRow(User user);

	public int deleteRow(int id);*/

}
