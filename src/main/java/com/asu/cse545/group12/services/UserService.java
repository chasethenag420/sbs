package com.asu.cse545.group12.services;

import java.util.List;

import com.asu.cse545.group12.domain.User;

public interface UserService {
	public int insertRow(User user);

	public List<User> getList();

	public User getRowById(int id);

	public int updateRow(User user);

	public int deleteRow(int id);

}
