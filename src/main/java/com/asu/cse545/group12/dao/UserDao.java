package com.asu.cse545.group12.dao;

import java.util.List;

import com.asu.cse545.group12.domain.Users;

public interface UserDao {
	public int insertRow(Users user);

	//public List<Users> getList();

	public Users getRowById(int id);

	public int updateRow(Users user);

	//public int deleteRow(int id);
}
