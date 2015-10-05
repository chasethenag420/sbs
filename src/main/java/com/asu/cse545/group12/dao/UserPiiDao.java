package com.asu.cse545.group12.dao;

import com.asu.cse545.group12.domain.UserPII;

public interface UserPiiDao {
	
	public int insertRow(UserPII userpii);

	//public List<Users> getList();

	public UserPII getRowById(int id);

	public int updateRow(UserPII userpii);

	//public int deleteRow(int id);


}
