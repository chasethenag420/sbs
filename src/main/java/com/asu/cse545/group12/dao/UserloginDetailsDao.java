package com.asu.cse545.group12.dao;

public interface UserloginDetailsDao {
	
	public void updateFailedAttempts(String username);
	public void resetFailedAttempts(String username);
	public int getUserAttempts(String username);

}

