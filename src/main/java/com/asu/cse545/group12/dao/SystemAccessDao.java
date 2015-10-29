package com.asu.cse545.group12.dao;

import java.util.List;

import com.asu.cse545.group12.domain.SystemAccess;

public interface SystemAccessDao {	
	public SystemAccess getRowById(int id);
	public int insertRow(SystemAccess systemAccess);
	public int updateRow(SystemAccess systemAccess);
	public List<SystemAccess> getAllSystemAccess();

}
