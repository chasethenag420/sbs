package com.asu.cse545.group12.dao;

import java.util.List;

import com.asu.cse545.group12.domain.AccessControl;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

public interface AccessControlDao {
	
	public int insertRow(AccessControl accessControl);
	public AccessControl getRowById(int AccountId);
	public AccessControl getAccessControlByUserId(int userId);
	public int updateRow(AccessControl accessControl);
}
