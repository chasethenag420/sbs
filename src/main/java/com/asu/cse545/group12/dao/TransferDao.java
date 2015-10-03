package com.asu.cse545.group12.dao;

import com.asu.cse545.group12.domain.Transfer;


public interface TransferDao {
	public int insertRow(Transfer transfer);

	//public List<Users> getList();

	public Transfer getRowById(int transferId);

	public int updateRow(Transfer transfer);

	//public int deleteRow(int id);

}
